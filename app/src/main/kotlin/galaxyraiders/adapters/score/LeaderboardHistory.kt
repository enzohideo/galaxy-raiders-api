package galaxyraiders.adapters

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import galaxyraiders.Config
import galaxyraiders.core.score.Score
import java.io.File
import java.io.IOException

object LeaderboardConfig {
  private val config = Config(prefix = "GR__ADAPTERS__LEADERBOARD_HISTORY__")

  val path = config.get<String>("PATH")
  val size = config.get<Int>("SIZE")
}

class LeaderboardHistory : ScoreboardHistory() {
  override fun save(score: Score) {
    val leaderboard = this.load(LeaderboardConfig.path)
    leaderboard.add(score)
    leaderboard.sortBy { it.points }
    val trimmedLeaderboard = leaderboard.take(LeaderboardConfig.size)

    try {
      val mapper = jacksonObjectMapper().writerWithDefaultPrettyPrinter()
      mapper.writeValue(File(LeaderboardConfig.path), trimmedLeaderboard)
    } catch (_: IOException) {
      println("Failed to save score to Leaderboard.")
    }
  }
}
