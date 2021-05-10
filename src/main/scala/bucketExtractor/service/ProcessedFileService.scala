package bucketExtractor.service

import bucketExtractor.dao.ProcessedFileDao

import java.sql.SQLException

/**
 * Service layer for tracking processed files
 */
object ProcessedFileService {
  /**
   * If a file with this name has not been processed, insert it into the DB.
   * @param filename name of the file
   * @return true if the file has been inserted, otherwise false.
   */
  def insertIfNotExists(filename: String): Boolean = {
    try {
      if (!ProcessedFileDao.exists(filename)) {
        ProcessedFileDao.insert(filename)
        true
      } else false
    } catch {
      case e: SQLException =>
        e.printStackTrace()
        false
    }
  }
}
