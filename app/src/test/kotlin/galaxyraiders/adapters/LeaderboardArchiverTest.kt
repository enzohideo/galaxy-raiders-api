package galaxyraiders.adapters.score

import galaxyraiders.core.game.Asteroid
import galaxyraiders.core.physics.Point2D
import galaxyraiders.core.physics.Vector2D
import galaxyraiders.core.score.ScoreCalculator
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Given a leaderboard history")
class LeaderboardArchiverTest {
  /* private val leaderboardArchiver = LeaderboardArchiver() */
  /* private val scoreboardArchiver = ScoreboardArchiver() */
  private val scoreCalculator = ScoreCalculator()
  private val asteroid = Asteroid(
    initialPosition = Point2D(1.0, 1.0),
    initialVelocity = Vector2D(1.0, 0.0),
    radius = 1.0,
    mass = 6.0
  )

  @Test
  fun `it saves to the right place`() {
    /* leaderboardArchiver.load() */
    /* scoreboardArchiver.load() */

    scoreCalculator.evaluate(asteroid)
    scoreCalculator.evaluate(asteroid)
    scoreCalculator.evaluate(asteroid)
    scoreCalculator.evaluate(asteroid)
    scoreCalculator.evaluate(asteroid)
    scoreCalculator.evaluate(asteroid)

    var score = scoreCalculator.generateScore()
  }
}
