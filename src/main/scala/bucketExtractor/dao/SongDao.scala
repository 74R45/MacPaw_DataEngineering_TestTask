package bucketExtractor.dao

import bucketExtractor.model.Song
import bucketExtractor.db.MysqlDataSource

/**
 * Data Access layer for table "songs"
 */
object SongDao {
  /**
   * Inserts a list of apps in form of a batch
   * @param songs songs to insert
   */
  def insertAll(songs: Iterable[Song]): Unit = {
    // Open connection and prepare a statement
    val conn = MysqlDataSource.getConnection
    val stmt = conn.prepareStatement(
      "INSERT INTO songs(artist_name, title, year, `release`) VALUE (?, ?, ?, ?)")

    // Add a batch for each song
    songs.foreach(song => {
      stmt.setString(1, song.artist_name)
      stmt.setString(2, song.title)
      stmt.setInt(3, song.year)
      stmt.setString(4, song.release)
      stmt.addBatch()
    })

    // Execute the batch
    stmt.executeBatch()
    conn.commit()
    stmt.close()
    conn.close()
  }
}
