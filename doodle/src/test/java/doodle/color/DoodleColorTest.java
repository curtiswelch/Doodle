package doodle.color;

import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.awt.event.KeyEvent;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DoodleColorTest {

    private DoodleColor color;

    @Before
    public void before() {
        color = new DoodleColor("Blue", KeyEvent.VK_B, new Color(0, 0, 255));
    }

    @Test
    public void canBeInstantiated() {
        assertNotNull(color);
    }

    @Test
    public void hasALabel() {
        assertEquals("Blue", color.getLabel());
    }

    @Test
    public void hasAKeyCode() {
        assertEquals(KeyEvent.VK_B, color.getKeyCode());
    }

    @Test
    public void hasAColor() {
        assertEquals(new Color(0, 0, 255), color.getColor());
    }

    @Test
    public void canBeInstantiatedByAStringDefinition() {
        DoodleColor color = new DoodleColor("Blue, B, 0, 0, 255");

        assertEquals("Blue", color.getLabel());
        assertEquals(KeyEvent.VK_B, color.getKeyCode());
        assertEquals(new Color(0, 0, 255), color.getColor());
    }

    @Test(expected = IllegalArgumentException.class)
    public void doesNotAllowBadStringDefinitions() {
        new DoodleColor("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void doesNotAllowBadKeyCodesInStringDefinition() {
        new DoodleColor("ARF, TOOT, 1, 2, 3");
    }
}
