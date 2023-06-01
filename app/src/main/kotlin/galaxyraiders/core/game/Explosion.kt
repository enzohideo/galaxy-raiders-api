package galaxyraiders.core.game

import galaxyraiders.core.physics.Object2D
import galaxyraiders.core.physics.Point2D
import galaxyraiders.core.physics.Vector2D

class Explosion(val type: String,
  val symbol: Char,
  initialPosition: Point2D,
  initialVelocity: Vector2D,
  radius: Double,
  mass: Double
  is_triggered: Boolean
  ) : SpaceObject(
  val type,
  val symbol,
  initialPosition,
  initialVelocity,
  radius,
  mass
) {
	
	

}