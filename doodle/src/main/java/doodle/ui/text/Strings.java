package doodle.ui.text;

import java.util.Locale;
import java.util.ResourceBundle;

public enum Strings {
    START_DOODLE("doodle.start"),
    STOP_DOODLE("doodle.stop"),
    EXIT("doodle.exit"),
    SETTINGS("doodle.settings"),
    TRANSLUCENT_WINDOW_ERROR("doodle.error.translucent.window");

    private String key;

    Strings(String key) {
        this.key = key;
    }

    public String value() {
        return ResourceBundle.getBundle("strings", Locale.getDefault()).getString(this.key);
    }
}
