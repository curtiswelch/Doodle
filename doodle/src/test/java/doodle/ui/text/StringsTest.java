package doodle.ui.text;

import doodle.ui.text.Strings;
import org.junit.Test;

import java.util.Locale;

import static org.junit.Assert.assertEquals;

public class StringsTest {
    @Test
    public void supportsEnglishLocale() {
        Locale.setDefault(Locale.ENGLISH);

        assertEquals("Start Doodle!", Strings.START_DOODLE.value());
    }

    @Test
    public void supportsOtherLocales() {
        Locale.setDefault(new Locale("zz"));

        assertEquals("Bork!", Strings.START_DOODLE.value());
    }
}
