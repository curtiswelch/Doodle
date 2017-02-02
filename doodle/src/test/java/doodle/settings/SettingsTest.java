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

        Settings.instance().reload();

        assertNotNull(Settings.instance().settings());

        assertNotNull(Settings.instance().settings().get(0));

        assertEquals("color.bland", Settings.instance().settings().get(0).name());

        System.setProperty("user.home", originalUserHome);
    }

    @Test
    public void shouldFallBackToDefaultPreferences() {
        Settings.instance().reload();

        assertNotNull(Settings.instance().settings());

        assertNotNull(Settings.instance().settings().get(0));

        assertEquals("color.blue", Settings.instance().settings().get(0).name());
    }
}
