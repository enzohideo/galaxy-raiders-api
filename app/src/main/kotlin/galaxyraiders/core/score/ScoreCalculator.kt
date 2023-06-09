package galaxyraiders.core.score

import galaxyraiders.Config
import galaxyraiders.core.game.Asteroid

object ScoreCalculatorConfig {
  private val config = Config(prefix = "GR__CORE__SCORE__SCORE_CALCULATOR__")

  val radiusCoefficient = config.get<Double>("RADIUS_COEFFICIENT")
  val massCoefficient = config.get<Double>("MASS_COEFFICIENT")
  val constantCoefficient = config.get<Double>("CONSTANT_COEFFICIENT")
}

class ScoreCalculator {
  var points: Int = 0
    private set
  var destroyedAsteroids: Int = 0
    private set

  fun evaluate(asteroid: Asteroid) {
    val radius = asteroid.radius
    val mass = asteroid.mass
    var points: Double = 0.0

    points += radius * ScoreCalculatorConfig.radiusCoefficient
    points += mass * ScoreCalculatorConfig.massCoefficient
    points += ScoreCalculatorConfig.constantCoefficient

    this.points += points.toInt()
    this.destroyedAsteroids++
  }

  fun generateScore(): Score {
    return Score(this.points, this.destroyedAsteroids)
  }

  fun clear() {
    this.points = 0
    this.destroyedAsteroids = 0
  }
}
