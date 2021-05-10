package bucketExtractor.service

import bucketExtractor.model.Song
import bucketExtractor.dao.SongDao

import java.sql.SQLException

/**
 * Service layer for processing songs
 */
object SongService {
  /**
   * Inserts a list of songs into the DB. Field "ingestion_time"
   * is autogenerated by the DB, so no need to generate it here.
   * @param songs songs to insert
   */
  def insertAll(songs: Iterable[Song]): Unit = {
    try {
      SongDao.insertAll(songs)
    } catch {
      case e: SQLException => e.printStackTrace()
    }
  }
}