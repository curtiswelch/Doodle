package doodle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.*;
import java.awt.image.BufferedImage;

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

    @Test
    public void testRender() {
        BufferedImage bi = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);

        Doodle d = DoodleFactory.instance().create();

        d.setColor(new Color(0, 0, 255));
        d.setStartingPoint(0, 0);
        d.setEndingPoint(50, 50);
        d.draw(bi.createGraphics());

        int[] pixel = bi.getData().getPixel(10, 10, (int[]) null);

        assertEquals(255, pixel[2]);
    }
}
