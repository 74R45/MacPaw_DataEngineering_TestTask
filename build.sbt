name := "MacPawTestSolution"

version := "0.1"

scalaVersion := "2.13.5"

libraryDependencies ++= Seq(
  "mysql" % "mysql-connector-java" % "8.0.24",
  "org.flywaydb" % "flyway-maven-plugin" % "7.8.2",
  "com.zaxxer" % "HikariCP" % "4.0.3",
  "io.circe" %% "circe-core" % "0.13.0",
  "io.circe" %% "circe-generic" % "0.13.0",
  "io.circe" %% "circe-parser" % "0.13.0"
)

enablePlugins(JavaAppPackaging)
enablePlugins(DockerPlugin)