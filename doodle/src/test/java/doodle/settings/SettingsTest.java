package doodle.settings;

import org.junit.Test;

import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SettingsTest {

    @Test
    public void shouldLoadUserPreferences() {
        String originalUserHome = System.getProperty("user.home");

        URL url = ClassLoader.getSystemResource("userhome");
        System.setProperty("user.home", url.getPath());

        Settings.reload();

        assertNotNull(Settings.settings());

        assertNotNull(Settings.settings().get(0));

        assertEquals("color.bland", Settings.settings().get(0).name());

        System.setProperty("user.home", originalUserHome);
    }

    @Test
    public void shouldFallBackToDefaultPreferences() {
        Settings.reload();

        assertNotNull(Settings.settings());

        assertNotNull(Settings.settings().get(0));

        assertEquals("color.blue", Settings.settings().get(0).name());
    }
}
