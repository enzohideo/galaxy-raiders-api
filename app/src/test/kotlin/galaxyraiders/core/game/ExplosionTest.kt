package galaxyraiders.core.game

import galaxyraiders.core.physics.Point2D
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

@DisplayName("Given an explosion")
class ExplosionTest {
  private val explosion = Explosion(
    initialPosition = Point2D(1.0, 1.0),
    radius = 1.0,
  )

  @Test
  fun `it has its parameters initialized correctly `() {
    assertAll(
      "Explosion should initialize all its parameters correctly",
      { assertNotNull(explosion) },
      { assertEquals("Explosion", explosion.type) },
      { assertEquals('+', explosion.symbol) },
      { assertEquals(Point2D(1.0, 1.0), explosion.center) },
      { assertEquals(1.0, explosion.radius) },
    )
  }

  @Test
  fun `it shows its type when converted to string `() {
    assertTrue(explosion.toString().contains(explosion.type))
  }

  @Test
  fun `it starts untriggered `() {
    assertFalse(explosion.wasTriggered)
  }

  @Test
  fun `it starts active `() {
    assertTrue(explosion.isActive)
  }

  @Test
  fun `it can be triggered with a specific duration time`() {
    val duration: Long = 1000
    explosion.trigger(duration)

    assertAll(
      "Explosion should trigger and set the specified duration",
      { assertEquals(duration, explosion.duration) },
      { assertTrue(explosion.wasTriggered) }
    )
  }

  @Test
  fun `it ends after the specified duration `() {
    val duration: Long = 500
    explosion.trigger(duration)

    Thread.sleep(duration + 1)
    assertTrue(explosion.hasEnded())
  }
}
