package bucketExtractor.dao

import bucketExtractor.model.App
import bucketExtractor.db.MysqlDataSource

/**
 * Data Access layer for table "apps"
 */
object AppDao {
  /**
   * Inserts a list of apps in form of a batch
   * @param apps apps to insert
   */
  def insertAll(apps: Iterable[App]): Unit = {
    // Open connection and prepare a statement
    val conn = MysqlDataSource.getConnection
    val stmt = conn.prepareStatement(
      """INSERT INTO apps(name, genre, rating, version, size_bytes, is_awesome)
        |VALUE (?, ?, ?, ?, ?, ?)""".stripMargin)

    // Add a batch for each app
    apps.foreach(app => {
      stmt.setString(1, app.name)
      stmt.setString(2, app.genre)
      stmt.setFloat(3, app.rating)
      stmt.setString(4, app.version)
      stmt.setInt(5, app.size_bytes)
      stmt.setBoolean(6, app.is_awesome)
      stmt.addBatch()
    })

    // Execute the batch
    stmt.executeBatch()
    conn.commit()
    stmt.close()
    conn.close()
  }
}
