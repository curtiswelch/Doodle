package doodle.color;

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

        Settings.instance().settings().forEach(setting -> {
            if (setting.name().startsWith("color.")) {
                DoodleColorRegistry.colors.add(new DoodleColor(setting.value()));
            }
        });
    }

    public static Optional<DoodleColor> getByLabel(String label) {
        return filterColors(color -> color.getLabel().equals(label));
    }

    private static Optional<DoodleColor> filterColors(Predicate<DoodleColor> test) {
        return DoodleColorRegistry.colors.stream()
                .filter(test)
                .findFirst();
    }

    public static List<DoodleColor> allColors() {
        return Collections.unmodifiableList(DoodleColorRegistry.colors);
    }

    public static DoodleColor defaultColor() {
        return DoodleColorRegistry.colors.get(0);
    }

}
