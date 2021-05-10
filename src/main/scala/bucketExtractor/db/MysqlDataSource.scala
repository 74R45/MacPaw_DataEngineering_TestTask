package bucketExtractor.db

import com.zaxxer.hikari.{HikariConfig, HikariDataSource}

import java.sql.Connection

/**
 * Data Source layer for MySQL database
 */
object MysqlDataSource {
  private val config = new HikariConfig()
  config.setJdbcUrl("jdbc:mysql://db:3306/macpaw_test_task")
  config.setUsername("root")
  config.setPassword("root_password")
  config.setAutoCommit(false)
  private val ds = new HikariDataSource(config)

  def getConnection: Connection = ds.getConnection
}
