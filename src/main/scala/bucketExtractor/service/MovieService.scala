package bucketExtractor.service

import bucketExtractor.model.Movie
import bucketExtractor.dao.MovieDao

import java.sql.SQLException

/**
 * Service layer for processing movies
 */
object MovieService {
  /**
   * Inserts a list of movies into the DB. Generates "original_title_normalized"
   * field using function normalizeTitle(movie).
   * @param movies movies to insert
   */
  def insertAll(movies: Iterable[Movie]): Unit = {
    movies.foreach(movie => movie.original_title_normalized = normalizeTitle(movie))
    try {
      MovieDao.insertAll(movies)
    } catch {
      case e: SQLException => e.printStackTrace()
    }
  }

  /**
   * Normalizes a movie title, such that any non-letter and non-number characters
   * are removed and spaces are replaced with an underscore
   * (e.g. Star Wars: The Force Awakens -> star_wars_the_force_awakens).
   * @param movie movie, title of which needs to be normalized
   * @return the normalized title
   */
  private def normalizeTitle(movie: Movie): String = {
    movie.original_title
      .toLowerCase
      .replaceAll(" ", "_")
      .filter(c => c.toString matches "[\\p{L}0-9_]")
  }
}
