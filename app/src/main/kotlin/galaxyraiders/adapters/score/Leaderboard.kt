package galaxyraiders.adapters.score

import galaxyraiders.Config
import galaxyraiders.core.score.Score

object LeaderboardConfig {
  private val config = Config(prefix = "GR__ADAPTERS__LEADERBOARD__")

  val path = config.get<String>("PATH")
  val size = config.get<Int>("SIZE")
}

class Leaderboard : Scoreboard() {
  override val path = LeaderboardConfig.path

  override fun submit(score: Score) {
    var leaderboard = this.scores + score
    leaderboard = leaderboard.sortedByDescending { it.points }
    leaderboard = leaderboard.take(LeaderboardConfig.size)

    this.write(leaderboard)
  }
}
