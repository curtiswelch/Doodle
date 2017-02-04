package doodle.color;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class DoodleColorRegistryTest {

    @BeforeClass
    public static void setUserHomeToNothing() {
        System.setProperty("user.home", "");
    }

    @Test
    public void loadsColorsFromSettings() {
        assertTrue(DoodleColorRegistry.INSTANCE.allColors().size() > 0);
    }

    @Test
    public void canFindByLabel() {
        Optional<DoodleColor> color = DoodleColorRegistry.INSTANCE.getByLabel("Red");

        assertTrue(color.isPresent());
    }

    @Test
    public void hasDefaultColor() {
        assertNotNull(DoodleColorRegistry.INSTANCE.defaultColor());
    }
}
