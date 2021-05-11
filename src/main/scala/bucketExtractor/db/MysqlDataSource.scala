package bucketExtractor.db

import com.zaxxer.hikari.{HikariConfig, HikariDataSource}

import java.sql.{Connection, SQLSyntaxErrorException, SQLTransientConnectionException}
import scala.annotation.tailrec

/**
 * Data Source layer for MySQL database
 */
object MysqlDataSource {
  final val timeout = 30000L
  private val config = new HikariConfig()
  config.setJdbcUrl("jdbc:mysql://db:3306/macpaw_test_task")
  config.setUsername("root")
  config.setPassword("root_password")
  config.setAutoCommit(false)
  config.setInitializationFailTimeout(0L)
  config.setConnectionTimeout(timeout)
  private val ds = new HikariDataSource(config)

  @tailrec
  def getConnection: Connection = try {
    ds.getConnection
  } catch {
    case e: SQLTransientConnectionException =>
      println(s"WARNING: ${e.getMessage}")
      getConnection
  }
}
