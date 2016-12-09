package doodle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class DoodleTest {

	@Test
	public void testCreateDoodle() {
		Doodle d1 = DoodleFactory.instance().create();

		Doodle d2 = DoodleFactory.instance().create();

		assertEquals(d1.getId() + 1, d2.getId());
	}

	@Test
	public void testDoodleBigEnough() {
		Doodle d = DoodleFactory.instance().create();

		d.setStartingPoint(0, 0);
		d.setEndingPoint(5, 5);

		assertFalse(d.isMinimumSize());

		d.setStartingPoint(6, 6);
		d.setEndingPoint(0, 0);

		assertTrue(d.isMinimumSize());

		d.setStartingPoint(6, 5);
		d.setEndingPoint(0, 0);

		assertFalse(d.isMinimumSize());

	}

	@Test
	public void testDoodleHitTest() {
		Doodle d = DoodleFactory.instance().create();

		d.setStartingPoint(10, 10);
		d.setEndingPoint(20, 20);

		assertTrue(d.hitTest(15, 15));
		assertFalse(d.hitTest(5, 5));
		assertFalse(d.hitTest(25, 25));
	}
}
