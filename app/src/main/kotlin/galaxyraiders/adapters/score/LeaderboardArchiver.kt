package galaxyraiders.adapters.score

import galaxyraiders.Config
import galaxyraiders.core.score.Score

object LeaderboardConfig {
  private val config = Config(prefix = "GR__ADAPTERS__LEADERBOARD_ARCHIVER__")

  val path = config.get<String>("PATH")
  val size = config.get<Int>("SIZE")
}

class LeaderboardArchiver : ScoreboardArchiver() {
  protected override val path: String = LeaderboardConfig.path

  override fun submit(score: Score) {
    var leaderboard = (this.scoreboard + score)
    leaderboard = leaderboard.sortedByDescending { it.points }
    leaderboard = leaderboard.take(LeaderboardConfig.size)

    this.save(leaderboard)
  }
}
