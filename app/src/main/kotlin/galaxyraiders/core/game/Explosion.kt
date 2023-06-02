package galaxyraiders.core.game

import galaxyraiders.core.physics.Object2D
import galaxyraiders.core.physics.Point2D
import galaxyraiders.core.physics.Vector2D

class Explosion(initialPosition: Point2D, initialVelocity : Vector2D, radius: Double, mass: Double) : 
  SpaceObject("Explosion", '+', initialPosition, initialVelocity, radius, mass) {
    
    var trigger : Boolean = false
    
    fun set_trigerred(bool: Boolean) : Unit {
      this.trigger = bool
      return Unit
    }

    fun is_trigerred() : Boolean {
      return this.trigger
    }

}