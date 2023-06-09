package galaxyraiders.core.game

import galaxyraiders.core.physics.Point2D
import galaxyraiders.core.physics.Vector2D
import galaxyraiders.core.score.Score
import galaxyraiders.core.score.ScoreCalculator
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

@DisplayName("Given a Score Calculator")
class ScoreCalculatorTest {
  private val scoreCalculator = ScoreCalculator()
  private val asteroid1 = Asteroid(
    initialPosition = Point2D(1.0, 1.0),
    initialVelocity = Vector2D(1.0, 0.0),
    radius = 2.0,
    mass = 5.0
  )
  private val asteroid2 = Asteroid(
    initialPosition = Point2D(1.0, 1.0),
    initialVelocity = Vector2D(1.0, 0.0),
    radius = 9.0,
    mass = 2.0
  )

  @Test
  fun `it starts with no points `() {
    assertEquals(0, scoreCalculator.points)
  }

  @Test
  fun `it starts with no destroyed asteroids `() {
    assertEquals(0, scoreCalculator.destroyedAsteroids)
  }

  @Test
  fun `it can generate a Score `() {
    val score: Score = scoreCalculator.generateScore()

    assertNotNull(score)
  }

  @Test
  fun `it can evaluate an asteroid `() {
    scoreCalculator.evaluate(asteroid1)
    val score: Score = scoreCalculator.generateScore()

    assertAll(
      "ScoreCalculator should generate a score with positive amount of points and 1 asteroid destroyed",
      { assertTrue(score.points > 0) },
      { assertEquals(1, score.destroyedAsteroids) },
    )
  }

  @Test
  fun `it can evaluate multiple asteroids `() {
    scoreCalculator.evaluate(asteroid1)
    scoreCalculator.evaluate(asteroid2)
    val score: Score = scoreCalculator.generateScore()

    assertAll(
      "ScoreCalculator should generate a score with positive amount of points and 2 asteroids destroyed",
      { assertTrue(score.points > 0) },
      { assertEquals(2, score.destroyedAsteroids) },
    )
  }

  @Test
  fun `it can be cleared `() {
    scoreCalculator.evaluate(asteroid1)
    scoreCalculator.clear()
    val score: Score = scoreCalculator.generateScore()

    assertAll(
      "ScoreCalculator should clear its points and destroyed Asteroids count",
      { assertEquals(0, score.points) },
      { assertEquals(0, score.destroyedAsteroids) },
    )
  }
}
