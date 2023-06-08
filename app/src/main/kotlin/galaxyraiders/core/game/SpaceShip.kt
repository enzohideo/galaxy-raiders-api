package galaxyraiders.core.game

import galaxyraiders.Config
import galaxyraiders.core.physics.Point2D
import galaxyraiders.core.physics.Vector2D

object SpaceShipConfig {
  private val config = Config(prefix = "GR__CORE__GAME__SPACE_SHIP__")

  val boost = config.get<Double>("BOOST")
  val threshold = config.get<Long>("DIRECTION_THRESHOLD")
}

enum class Direction { UP, DOWN, RIGHT, LEFT }

object SpaceShipDirection {
  var registered: Boolean = false

  private val timestamp: MutableMap<Direction, Long> = mutableMapOf(
    Direction.UP to 0,
    Direction.DOWN to 0,
    Direction.RIGHT to 0,
    Direction.LEFT to 0
  )

  private val direction: Map<Direction, Vector2D> = mapOf(
    Direction.UP to Vector2D(0.0, 1.0),
    Direction.DOWN to Vector2D(0.0, -1.0),
    Direction.RIGHT to Vector2D(1.0, 0.0),
    Direction.LEFT to Vector2D(-1.0, 0.0)
  )

  fun register(direction: Direction) {
    val now = System.currentTimeMillis()
    this.timestamp[direction] = now
    this.registered = true
  }

  fun evaluate(threshold: Long): Vector2D {
    val now = System.currentTimeMillis()
    var direction = Vector2D(0.0, 0.0)
    this.timestamp.forEach { (type, time) ->
      if (now - time < threshold) {
        when (type) {
          Direction.UP -> direction += Vector2D(0.0, 1.0)
          Direction.DOWN -> direction += Vector2D(0.0, -1.0)
          Direction.RIGHT -> direction += Vector2D(1.0, 0.0)
          Direction.LEFT -> direction += Vector2D(-1.0, 0.0)
        }
      }
    }
    return direction.unit
  }
}

class SpaceShip(
  initialPosition: Point2D,
  initialVelocity: Vector2D,
  radius: Double,
  mass: Double
) :
  SpaceObject("SpaceShip", '@', initialPosition, initialVelocity, radius, mass) {
  override var velocity = initialVelocity
    get() {
      if (SpaceShipDirection.registered) {
        val direction: Vector2D = SpaceShipDirection.evaluate(SpaceShipConfig.threshold)
        return direction * SpaceShipConfig.boost
      }
      return field
    }

  fun boostUp() {
    SpaceShipDirection.register(Direction.UP)
    /* this.direction = (this.direction + Vector2D(0.0, 2.0)).unit */
    /* this.velocity += Vector2D(dx = 0.0, dy = SpaceShipConfig.boost) */
  }

  fun boostDown() {
    SpaceShipDirection.register(Direction.DOWN)
    /* this.direction = (this.direction + Vector2D(0.0, -2.0)).unit */
    /* this.velocity += Vector2D(dx = 0.0, dy = -SpaceShipConfig.boost) */
  }

  fun boostLeft() {
    SpaceShipDirection.register(Direction.LEFT)
    /* this.direction = (this.direction + Vector2D(-2.0, 0.0)).unit */
    /* this.velocity += Vector2D(dx = -SpaceShipConfig.boost, dy = 0.0) */
  }

  fun boostRight() {
    SpaceShipDirection.register(Direction.RIGHT)
    /* this.direction = (this.direction + Vector2D(2.0, 0.0)).unit */
    /* this.velocity += Vector2D(dx = SpaceShipConfig.boost, dy = 0.0) */
  }

  fun move(
    boundaryX: ClosedFloatingPointRange<Double>,
    boundaryY: ClosedFloatingPointRange<Double>,
  ) {
    this.move()

    if (insideBoundaries(boundaryX, boundaryY)) return

    this.correctPosition(boundaryX, boundaryY)
    this.zeroOutSpeed()
  }

  private fun insideBoundaries(
    boundaryX: ClosedFloatingPointRange<Double>,
    boundaryY: ClosedFloatingPointRange<Double>,
  ): Boolean {
    return boundaryX.contains(this.center.x) && boundaryY.contains(this.center.y)
  }

  private fun correctPosition(
    boundaryX: ClosedFloatingPointRange<Double>,
    boundaryY: ClosedFloatingPointRange<Double>,
  ) {
    this.center = Point2D(
      x = this.center.x.coerceIn(boundaryX),
      y = this.center.y.coerceIn(boundaryY),
    )
  }

  private fun zeroOutSpeed() {
    this.velocity = Vector2D(dx = 0.0, dy = 0.0)
  }
}
