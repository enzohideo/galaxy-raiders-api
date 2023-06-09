package galaxyraiders.core.physics
import kotlin.math.sqrt

data class Point2D(val x: Double, val y: Double) {
  operator fun plus(p: Point2D): Point2D {
    return Point2D(x + p.x, y + p.y)
  }
  operator fun plus(v: Vector2D): Point2D {
    return Point2D(x + v.dx, y + v.dy)
  }

  override fun toString(): String {
    return "Point2D(x=$x, y=$y)"
  }

  fun toVector(): Vector2D {
    return Vector2D(this.x, this.y)
  }

  fun impactVector(p: Point2D): Vector2D {
    return Vector2D(Math.abs(p.x - this.x), Math.abs(p.y - this.y))
  }

  fun impactDirection(p: Point2D): Vector2D {
    return Vector2D(p.x - this.x, p.y - this.y)
  }

  fun contactVector(p: Point2D): Vector2D {
    return Vector2D(Math.abs(p.y - this.y), -Math.abs(p.x - this.x)).unit
  }

  fun contactDirection(p: Point2D): Vector2D {
    return Vector2D(p.y - this.y, -(p.x - this.x)).unit
  }

  fun distance(p: Point2D): Double {
    return sqrt((this.x - p.x) * (this.x - p.x) + (this.y - p.y) * (this.y - p.y))
  }
}
