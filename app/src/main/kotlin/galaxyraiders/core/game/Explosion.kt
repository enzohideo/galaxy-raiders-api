package galaxyraiders.core.game

import galaxyraiders.core.physics.Point2D
import galaxyraiders.core.physics.Vector2D

class Explosion(
  initialPosition: Point2D,
  radius: Double,
) :
  SpaceObject("Explosion", '+', initialPosition, Vector2D(0.0, 0.0), radius, 0.0) {
  private var triggerTime: Long = 0
  var duration: Long = 0
    private set

  val wasTriggered: Boolean
    get() = this.triggerTime > 0

  fun trigger(duration: Long) {
    this.triggerTime = System.currentTimeMillis()
    this.duration = duration
  }

  fun hasEnded(): Boolean {
    val now = System.currentTimeMillis()
    return this.wasTriggered && (now - this.triggerTime) > this.duration
  }
}
