package galaxyraiders.core.game

import galaxyraiders.core.physics.Point2D
import galaxyraiders.core.physics.Vector2D
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotNull

@DisplayName("Given an explosion")
class ExplosionTest {
  private val explosion = Explosion(
    initialPosition = Point2D(1.0, 1.0),
    initialVelocity = Vector2D(1.0, 0.0),
    radius = 1.0,
    mass = 0.0
  )

  @Test
  fun `it has its parameters initialized correctly `() {
    assertAll(
      "SpaceObject should initialize all its parameters correctly",
      { assertNotNull(explosion) },
      { assertEquals("Explosion", explosion.type) },
      { assertEquals('+', explosion.symbol) },
      { assertEquals(Point2D(1.0, 1.0), explosion.center) },
      { assertEquals(Vector2D(1.0, 0.0), explosion.velocity) },
      { assertEquals(1.0, explosion.radius) },
      { assertEquals(0.0, explosion.mass) },
    )
  }

  @Test
  fun `it verifies setTriggered()`() {
    explosion.setTriggered(false)
    assertFalse(explosion.trigger)
  }

  @Test
  fun `it verifies isTriggered()`() {
    assertFalse(explosion.isTriggered())
  }
}
