package doodle.color;

import doodle.event.ColorChanged;
import doodle.event.EventBus;
import doodle.event.OpacityChanged;
import doodle.settings.Settings;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public enum DoodleColorRegistry {
    INSTANCE;

    private List<DoodleColor> colors;

    private DoodleColor currentColor;

    private double opacity = 50.0;

    DoodleColorRegistry() {
        loadSettings();
        this.currentColor = this.colors.get(0);
    }

    public static DoodleColor currentColor() {
        return INSTANCE.currentColor;
    }

    public static void currentColor(DoodleColor color) {
        INSTANCE.currentColor = color;
        EventBus.post(new ColorChanged(color));
    }

    public static void adjustOpacity(double distance) {
        INSTANCE.opacity = Math.max(1.0, Math.min(INSTANCE.opacity - distance * 10.0, 255.0));

        EventBus.post(new OpacityChanged());
    }

    public static int opacity() {
        return ((int) INSTANCE.opacity) & 255;
    }

    public static Optional<DoodleColor> getByLabel(String label) {
        return INSTANCE.filterColors(color -> color.getLabel().equals(label));
    }

    public static List<DoodleColor> allColors() {
        return Collections.unmodifiableList(INSTANCE.colors);
    }

    private void loadSettings() {
        this.colors = new ArrayList<>();

        StringDefinitionParser parser = new StringDefinitionParser();

        Settings.settings().forEach(setting -> {
            if (setting.name().startsWith("color.")) {
                this.colors.add(parser.parse(setting.value()));
            }
        });
    }

    private Optional<DoodleColor> filterColors(Predicate<DoodleColor> test) {
        return this.colors.stream()
                .filter(test)
                .findFirst();
    }

}
