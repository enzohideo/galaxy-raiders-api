package galaxyraiders.core.game

import galaxyraiders.core.physics.Point2D
import galaxyraiders.core.physics.Vector2D
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@DisplayName("Given an missile")
class MissileTest {
  private val missile = Missile(
    initialPosition = Point2D(1.0, 1.0),
    initialVelocity = Vector2D(1.0, 0.0),
    radius = 1.0,
    mass = 1.0
  )

  private val asteroid = Asteroid(
    initialPosition = Point2D(3.0, 4.0),
    initialVelocity = Vector2D(5.0, 0.0),
    radius = 3.0,
    mass = 7.0
  )

  @Test
  fun `it has a type Missile `() {
    assertEquals("Missile", missile.type)
  }

  @Test
  fun `it has a symbol hat `() {
    assertEquals('^', missile.symbol)
  }

  @Test
  fun `it shows the type Missile when converted to String `() {
    assertTrue(missile.toString().contains("Missile"))
  }

  @Test
  fun `it can hit an Asteroid`() {
    val explosion: Explosion = missile.hit(asteroid)
    assertAll(
      "It should deactivate itself and create an Explosion at the Asteroid's position",
      { assertEquals(asteroid.center, explosion.center) },
      { assertFalse(missile.isActive) },
    )
  }
}
