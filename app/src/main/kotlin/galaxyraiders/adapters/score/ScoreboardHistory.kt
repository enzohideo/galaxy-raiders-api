package galaxyraiders.adapters

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import galaxyraiders.Config
import galaxyraiders.core.score.Score
import galaxyraiders.ports.ScoreHistory
import java.io.File
import java.io.IOException

object ScoreboardConfig {
  private val config = Config(prefix = "GR__ADAPTERS__SCOREBOARD_HISTORY__")

  val path = config.get<String>("PATH")
}

open class ScoreboardHistory : ScoreHistory {
  protected fun load(path: String): MutableList<Score> {
    try {
      val mapper = jacksonObjectMapper()
      val scoreboard = mapper.readValue<MutableList<Score>>(File(path))

      return scoreboard
    } catch (_: IOException) {
      return mutableListOf<Score>()
    }
  }

  override fun save(score: Score) {
    var leaderboard = this.load(ScoreboardConfig.path)
    leaderboard.add(score)

    try {
      val mapper = jacksonObjectMapper().writerWithDefaultPrettyPrinter()
      mapper.writeValue(File(ScoreboardConfig.path), leaderboard)
    } catch (_: IOException) {
      println("Failed to save score to Scoreboard.")
    }
  }
}
