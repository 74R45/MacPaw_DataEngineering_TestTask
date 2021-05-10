package bucketExtractor

import bucketExtractor.model._
import bucketExtractor.decoders._
import bucketExtractor.service.{AppService, MovieService, ProcessedFileService, SongService}
import io.circe._
import io.circe.parser._
import io.circe.generic.auto._

import java.io.{BufferedReader, InputStreamReader}
import java.net.URL
import java.util.stream.Collectors
import scala.collection.mutable

object Main {
  private final val AwsBucketUrl = "https://data-engineering-interns.macpaw.io"
  private final val FilesListKey = "files_list.data"

  /**
   * Main function, adds all unprocessed useful data from the cloud
   * into the local DB.
   */
  def main(args: Array[String]): Unit = {
    val fileKeys = getFileKeys

    fileKeys.foreach(filename =>
      if (ProcessedFileService.insertIfNotExists(filename)) {
        val json = getFileJson(filename)
        processJson(json)
      }
    )
  }

  /**
   * Retrieves a list of names of files in the AWS bucket.
   * @return Vector of filenames
   */
  private def getFileKeys: Vector[String] = {
    val reader = new BufferedReader(new InputStreamReader(
      new URL(s"$AwsBucketUrl/$FilesListKey").openStream()))

    val res = LazyList.continually(reader.readLine).takeWhile(_ != null).toVector
    reader.close()
    res
  }

  /**
   * Retrieves a file from the AWS bucket by it's name.
   * @param filename name of the file
   * @return contents of the file
   */
  private def getFileJson(filename: String): String = {
    val reader = new BufferedReader(new InputStreamReader(
      new URL(s"$AwsBucketUrl/$filename").openStream()))

    val json = reader.lines().collect(Collectors.joining(System.lineSeparator()))
    reader.close()
    json
  }

  /**
   * Transforms the JSON into a collection of Items,
   * passes those items to their corresponding services.
   * @param json JSON to process
   */
  private def processJson(json: String): Unit = {
    val parsed = parse(json).getOrElse(Json.Null)
    parsed.asArray match {
      case Some(array) =>
        println(s"${System.currentTimeMillis()}: start")
        // Traverse the JSON, mapping each object into an Item of its type
        val results = array.map(json => {
          val c = json.hcursor
          c.get[String]("type") match {
            case Right(t) => t match {
              case "song" => c.downField("data").as[Song]
              case "movie" => c.downField("data").as[Movie]
              case "app" => c.downField("data").as[App]
              case other => Left(DecodingFailure(s"Wrong type: $other", c.history))
            }
            case Left(err) => Left(err)
          }
        })
        // Filter Items that where successfully parsed
        val items: Vector[Item] = results
          .filter(_.isRight)
          .map { case Right(item) => item }

        // Split the collection into 3 collections containing different Item types
        val songs = mutable.ArrayBuffer[Song]()
        val movies = mutable.ArrayBuffer[Movie]()
        val apps = mutable.ArrayBuffer[App]()
        items foreach {
          case s: Song => songs += s
          case m: Movie => movies += m
          case a: App => apps += a
        }
        println(s"${System.currentTimeMillis()}: parsed")

        // Insert the Items into the DB
        SongService.insertAll(songs)
        MovieService.insertAll(movies)
        AppService.insertAll(apps)
        println(s"${System.currentTimeMillis()}: inserted")

      case None => println("Parse error: not an array")
    }
  }
}
