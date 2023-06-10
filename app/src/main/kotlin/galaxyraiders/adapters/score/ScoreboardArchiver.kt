package galaxyraiders.adapters.score

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import galaxyraiders.Config
import galaxyraiders.core.score.Score
import galaxyraiders.ports.ScoreArchiver
import java.io.File
import java.io.IOException

object ScoreboardConfig {
  private val config = Config(prefix = "GR__ADAPTERS__SCOREBOARD_ARCHIVER__")

  val path = config.get<String>("PATH")
}

open class ScoreboardArchiver : ScoreArchiver {
  protected var scoreboard: List<Score> = emptyList()
  protected open val path: String = ScoreboardConfig.path

  override fun load() {
    try {
      val mapper = jacksonObjectMapper()
      this.scoreboard = mapper.readValue<List<Score>>(
        File(this.path)
      )
    } catch (_: IOException) {
      this.scoreboard = emptyList()
    }
  }

  protected fun save(scoreboard: List<Score>) {
    try {
      val mapper = jacksonObjectMapper().writerWithDefaultPrettyPrinter()
      mapper.writeValue(File(this.path), scoreboard)
    } catch (_: IOException) {
      println("Failed to submit score.")
    }
  }

  override fun submit(score: Score) {
    val scoreboard = this.scoreboard + score
    this.save(scoreboard)
  }
}
