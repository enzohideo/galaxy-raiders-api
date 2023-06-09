package galaxyraiders.core.game

import galaxyraiders.core.score.Score
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@DisplayName("Given a Score")
class ScoreTest {
  private val score = Score(8000, 24)

  @Test
  fun `it has its parameters initialized correctly `() {
    assertAll(
      "Score should initialize all initial parameters correctly",
      { assertNotNull(score) },
      { assertEquals(8000, score.points) },
      { assertEquals(24, score.destroyedAsteroids) },
    )
  }
}
