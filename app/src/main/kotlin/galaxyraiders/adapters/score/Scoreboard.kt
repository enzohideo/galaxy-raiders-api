package galaxyraiders.adapters.score

import galaxyraiders.Config
import galaxyraiders.core.score.Score
import galaxyraiders.ports.ScoreArchiver

object ScoreboardConfig {
  private val config = Config(prefix = "GR__ADAPTERS__SCOREBOARD__")

  val path = config.get<String>("PATH")
}

open class Scoreboard : FileManager(), ScoreArchiver {
  override val path = ScoreboardConfig.path
  var scores: List<Score> = emptyList()

  override fun load() {
    this.scores = this.read()
  }

  override fun submit(score: Score) {
    val scoreboard = this.scores + score
    this.write(scoreboard)
  }
}
