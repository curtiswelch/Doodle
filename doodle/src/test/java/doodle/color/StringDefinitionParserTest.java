package doodle.color;

import org.junit.BeforeClass;
import org.junit.Test;

import java.awt.*;
import java.awt.event.KeyEvent;

import static org.junit.Assert.assertEquals;

public class StringDefinitionParserTest {
    static StringDefinitionParser parser;

    @BeforeClass
    public static void beforeClass() {
        parser = new StringDefinitionParser();
    }

    @Test
    public void canBeInstantiatedByAStringDefinition() {
        DoodleColor color = parser.parse("Blue, B, 0, 0, 255");

        assertEquals("Blue", color.getLabel());
        assertEquals(KeyEvent.VK_B, color.getKeyCode());
        assertEquals(new Color(0, 0, 255), color.getColor());
    }

    @Test(expected = IllegalArgumentException.class)
    public void doesNotAllowBadStringDefinitions() {
        parser.parse("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void doesNotAllowBadKeyCodesInStringDefinition() {
        parser.parse("ARF, TOOT, 1, 2, 3");
    }

}
