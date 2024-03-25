package galaxyraiders.adapters.score

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import galaxyraiders.core.score.Score
import java.io.File
import java.io.IOException

class FileManager(
    val path: String
) {
  fun read(): List<Score> {
    try {
      val mapper = jacksonObjectMapper()
      return mapper.readValue<List<Score>>(
        File(this.path)
      )
    } catch (_: IOException) {
      return emptyList()
    }
  }

  fun write(scores: List<Score>) {
    try {
      val mapper = jacksonObjectMapper().writerWithDefaultPrettyPrinter()
      mapper.writeValue(File(this.path), scores)
    } catch (_: IOException) {
      println("Failed to submit score.")
    }
  }
}
