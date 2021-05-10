package bucketExtractor

import java.sql.{Date, Timestamp}
import io.circe.Decoder

import java.time.Instant

/**
 * Model layer for representing the data
 */
object model {
  trait Item

  case class Song(artist_name: String, title: String, year: Int, release: String) extends Item {
    var ingestion_time: Timestamp = Timestamp.from(Instant.now())
  }

  case class Movie(original_title: String, original_language: String,
                   budget: Int, is_adult: Boolean, release_date: Date) extends Item {
    var original_title_normalized: String = ""
  }

  case class App(name: String, genre: String, rating: Float,
                 version: String, size_bytes: Int) extends Item {
    var is_awesome: Boolean = false
  }
}

/**
 * An object with implicit decoders that are used while processing JSON
 */
object decoders {
  /**
   * Custom implicit JSON Decoder to handle java.sql.Date
   */
  implicit val decodeDate: Decoder[Date] = Decoder.decodeString map Date.valueOf
}
