package doodle.color;

import org.junit.BeforeClass;
import org.junit.Test;

import java.awt.Color;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class DoodleColorRegistryTest {

    @BeforeClass
    public static void setUserHomeToNothing() {
        System.setProperty("user.home", "");
    }

    @Test
    public void loadsColorsFromSettings() {
        assertTrue(DoodleColorRegistry.allColors().size() > 0);
    }

    @Test
    public void canFindByLabel() {
        Optional<DoodleColor> color = DoodleColorRegistry.getByLabel("Red");

        assertTrue(color.isPresent());
    }

    @Test
    public void hasDefaultColor() {
        assertNotNull(DoodleColorRegistry.currentColor());
    }

    @Test
    public void canSetDefaultColor() {
        DoodleColor doodleColor = new DoodleColor("Poo", 1, Color.WHITE);

        DoodleColorRegistry.currentColor(doodleColor);

        assertEquals(doodleColor, DoodleColorRegistry.currentColor());
    }

    @Test
    public void canAdjustOpacity() {
        int opacity = DoodleColorRegistry.opacity();

        DoodleColorRegistry.adjustOpacity(-1);

        assertEquals(opacity + 10, DoodleColorRegistry.opacity());
    }
}
