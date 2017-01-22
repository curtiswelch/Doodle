package doodle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class DoodleColors {
    private static final Log log = LogFactory.getLog(DoodleColor.class);

    private static List<DoodleColor> colors;

    private static int NAME = 0;
    private static int KEY = 1;
    private static int RED = 2;
    private static int GREEN = 3;
    private static int BLUE = 4;

    static {
        DoodleColors.colors = new ArrayList<>();

        for(Settings.Setting setting : Settings.instance().settings()) {
            if(setting.name().startsWith("color.")) {
                processColor(setting.value());
            }
        }
    }

    private static void processColor(String definition) {
        String[] parts = definition.split(",");

        if (parts.length == 5) {
            int r = Integer.decode(parts[RED].trim());
            int g = Integer.decode(parts[GREEN].trim());
            int b = Integer.decode(parts[BLUE].trim());
            int keyCode = 0;

            try {
                Field field = KeyEvent.class.getField("VK_" + parts[KEY].trim().toUpperCase());
                keyCode = field.getInt(null);
            } catch (NoSuchFieldException | IllegalAccessException e) {
            }

            DoodleColors.colors.add(new DoodleColor(parts[NAME].trim(), keyCode, new Color(r, g, b)));
        }
    }


    public static Optional<DoodleColor> getByLabel(String label) {
        return filterColors(color -> color.getLabel().equals(label));
    }

    private static Optional<DoodleColor> filterColors(Predicate<DoodleColor> test) {
        Optional<DoodleColor> result = Optional.empty();

        for (DoodleColor color : DoodleColors.colors) {
            if (test.test(color)) {
                result = Optional.of(color);
                break;
            }
        }

        return result;
    }

    public static List<DoodleColor> allColors() {
        return Collections.unmodifiableList(DoodleColors.colors);
    }

    public static DoodleColor defaultColor() {
        return DoodleColors.colors.get(0);
    }

}
