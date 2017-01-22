package doodle.ui.text;

import java.util.Locale;
import java.util.ResourceBundle;

public class Strings {
    public static final String START_DOODLE_KEY = "doodle.start";
    public static final String STOP_DOODLE_KEY = "doodle.stop";
    public static final String EXIT_KEY = "doodle.exit";
    public static final String SETTINGS_KEY = "doodle.settings";
    public static final String TRANSLUCENT_WINDOW_ERROR = "doodle.error.translucent.window";

    public static String getText(String key) {
        return ResourceBundle.getBundle("strings", Locale.getDefault()).getString(key);
    }
}
