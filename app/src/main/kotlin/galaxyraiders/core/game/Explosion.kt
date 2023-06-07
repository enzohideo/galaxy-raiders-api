package galaxyraiders.core.game

import galaxyraiders.core.physics.Point2D
import galaxyraiders.core.physics.Vector2D

class Explosion(
  initialPosition: Point2D,
  radius: Double,
) :
  SpaceObject("Explosion", '+', initialPosition, Vector2D(0.0, 0.0), radius, 0.0) {
  private var startTime: Long = 0
  private var duration: Long = 0

  fun start(duration: Long) {
    this.startTime = System.currentTimeMillis()
    this.duration = duration
  }

  fun hasStarted(): Boolean {
    return this.startTime > 0
  }

  fun hasEnded(): Boolean {
    val now = System.currentTimeMillis()
    return this.hasStarted() && (now - this.startTime) > this.duration
  }
}
