package galaxyraiders.core.score

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

data class Score(
  val points: Int,
  val destroyedAsteroids: Int
) {
  private val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss xxx")
  val date: String = ZonedDateTime.now().format(this.formatter)
}
