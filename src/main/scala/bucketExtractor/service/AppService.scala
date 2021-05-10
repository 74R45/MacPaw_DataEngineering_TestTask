package bucketExtractor.service

import bucketExtractor.model.App
import bucketExtractor.dao.AppDao

import java.sql.SQLException

/**
 * Service layer for processing apps
 */
object AppService {
  /**
   * Inserts a list of apps into the DB. Generates "is_awesome" field
   * using function isAwesome(app).
   * @param apps apps to insert
   */
  def insertAll(apps: Iterable[App]): Unit = {
    apps.foreach(app => app.is_awesome = isAwesome(app))
    try {
      AppDao.insertAll(apps)
    } catch {
      case e: SQLException => e.printStackTrace()
    }
  }

  /**
   * Determines whether an app is awesome. Currently, the criteria
   * is the following: if the first number of app version is more than 1,
   * then the app is considered awesome.
   * @param app the app to be judged
   * @return true or false, indicating whether the app is awesome
   */
  private def isAwesome(app: App): Boolean =
    ('0' +: app.version.takeWhile(_.toString matches "[0-9]")).toInt > 1
}
