package doodle.color;

import doodle.settings.Setting;
import doodle.settings.Settings;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class DoodleColorRegistry {
    private static List<DoodleColor> colors;

    static {
        DoodleColorRegistry.loadSettings();
    }

    public static void loadSettings() {
        DoodleColorRegistry.colors = new ArrayList<>();

        for (Setting setting : Settings.instance().settings()) {
            if (setting.name().startsWith("color.")) {
                DoodleColorRegistry.colors.add(new DoodleColor(setting.value()));
            }
        }
    }

    public static Optional<DoodleColor> getByLabel(String label) {
        return filterColors(color -> color.getLabel().equals(label));
    }

    private static Optional<DoodleColor> filterColors(Predicate<DoodleColor> test) {
        Optional<DoodleColor> result = Optional.empty();

        for (DoodleColor color : DoodleColorRegistry.colors) {
            if (test.test(color)) {
                result = Optional.of(color);
                break;
            }
        }

        return result;
    }

    public static List<DoodleColor> allColors() {
        return Collections.unmodifiableList(DoodleColorRegistry.colors);
    }

    public static DoodleColor defaultColor() {
        return DoodleColorRegistry.colors.get(0);
    }

}
