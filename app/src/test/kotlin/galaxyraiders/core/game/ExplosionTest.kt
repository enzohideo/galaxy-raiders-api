package galaxyraiders.core.game

import galaxyraiders.core.physics.Point2D
import galaxyraiders.core.physics.Vector2D
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

@DisplayName("Given an explosion")
class ExplosionTest {
	private val exp = Explosion(
		initialPosition = Point2D(1.0, 1.0), 
		initialVelocity = Vector2D(1.0, 0.0), 
		radius = 1.0,
		mass = 0.0
	)

	@Test
	fun `it has its parameters initialized correctly `() {
    assertAll(
      "SpaceObject should initialize all its parameters correctly",
      { assertNotNull(exp) },
      { assertEquals("Explosion", exp.type) },
      { assertEquals('+', exp.symbol) },
      { assertEquals(Point2D(1.0, 1.0), exp.center) },
      { assertEquals(Vector2D(1.0, 0.0), exp.velocity) },
      { assertEquals(1.0, exp.radius) },
      { assertEquals(0.0, exp.mass) },
    )
  }

  @Test
  fun `it verifies set_trigerred()`() {
  	exp.set_trigerred(false)
    assertEquals(false, exp.trigger)
  }

  @Test
  fun `it verifies is_trigerred()`(){
  	assertEquals(false, exp.is_trigerred())
  }



}