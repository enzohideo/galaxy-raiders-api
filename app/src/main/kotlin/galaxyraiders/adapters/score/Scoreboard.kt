package galaxyraiders.adapters.score

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import galaxyraiders.Config
import galaxyraiders.core.score.Score
import galaxyraiders.ports.ScoreArchiver
import java.io.File
import java.io.IOException

object ScoreboardConfig {
  private val config = Config(prefix = "GR__ADAPTERS__SCOREBOARD__")

  val path = config.get<String>("PATH")
}

open class Scoreboard : ScoreArchiver {
  protected var scores: List<Score> = emptyList()
  protected open val path: String = ScoreboardConfig.path

  override fun load() {
    try {
      val mapper = jacksonObjectMapper()
      this.scores = mapper.readValue<List<Score>>(
        File(this.path)
      )
    } catch (_: IOException) {
      this.scores = emptyList()
    }
  }

  protected fun save(scores: List<Score>) {
    try {
      val mapper = jacksonObjectMapper().writerWithDefaultPrettyPrinter()
      mapper.writeValue(File(this.path), scores)
    } catch (_: IOException) {
      println("Failed to submit score.")
    }
  }

  override fun submit(score: Score) {
    val scoreboard = this.scores + score
    this.save(scoreboard)
  }
}
