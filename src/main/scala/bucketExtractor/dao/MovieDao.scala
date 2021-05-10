package bucketExtractor.dao

import bucketExtractor.model.Movie
import bucketExtractor.db.MysqlDataSource

/**
 * Data Access layer for table "movies"
 */
object MovieDao {
  /**
   * Inserts a list of movies in form of a batch
   * @param movies movies to insert
   */
  def insertAll(movies: Iterable[Movie]): Unit = {
    // Open connection and prepare a statement
    val conn = MysqlDataSource.getConnection
    val stmt = conn.prepareStatement(
      """INSERT INTO movies(original_title, original_language, budget,
        |  is_adult, release_date, original_title_normalized)
        |VALUE (?, ?, ?, ?, ?, ?)""".stripMargin)

    // Add a batch for each movie
    movies.foreach(movie => {
      stmt.setString(1, movie.original_title)
      stmt.setString(2, movie.original_language)
      stmt.setInt(3, movie.budget)
      stmt.setBoolean(4, movie.is_adult)
      stmt.setDate(5, movie.release_date)
      stmt.setString(6, movie.original_title_normalized)
      stmt.addBatch()
    })

    // Execute the batch
    stmt.executeBatch()
    conn.commit()
    stmt.close()
    conn.close()
  }
}
