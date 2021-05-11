package bucketExtractor.dao

import bucketExtractor.db.MysqlDataSource
import bucketExtractor.db.MysqlDataSource.timeout

import java.sql.SQLSyntaxErrorException
import scala.annotation.tailrec

/**
 * Data Access layer for table "processed_files"
 */
object ProcessedFileDao {
  /**
   * Checks if a file with such name has been processed.
   *
   * @param filename name of the file
   * @return true or false, indicating whether this file has been processed
   */
  @tailrec
  def exists(filename: String): Boolean = {
    // Create connection and statement
    val conn = MysqlDataSource.getConnection
    val stmt = conn.prepareStatement("SELECT id FROM processed_files WHERE name = ?")
    stmt.setString(1, filename)

    try {
      val rs = stmt.executeQuery()
      val res = rs.next() // true if the ResultSet contains at least one element

      stmt.close()
      conn.close()
      res
    } catch {
      // If DB migrations haven't been applied yet
      case e: SQLSyntaxErrorException =>
        println(s"WARNING: ${e.getMessage}, retrying in ${timeout}ms.")
        stmt.close()
        conn.close()

        Thread.sleep(timeout)
        exists(filename)
    }
  }

  /**
   * Inserts this filename into the list of processed files.
   * @param filename name of the file
   */
  def insert(filename: String): Unit = {
    // Create connection and statement
    val conn = MysqlDataSource.getConnection
    val stmt = conn.prepareStatement("INSERT INTO processed_files(name) VALUE (?)")
    stmt.setString(1, filename)
    stmt.executeUpdate()

    conn.commit()
    stmt.close()
    conn.close()
  }
}
