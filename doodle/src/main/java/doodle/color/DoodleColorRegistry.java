package doodle.color;

import doodle.event.ColorChanged;
import doodle.event.EventBus;
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

    DoodleColorRegistry() {
        loadSettings();
        this.currentColor = this.colors.get(0);
    }

    public DoodleColor currentColor() {
        return this.currentColor;
    }

    public void currentColor(DoodleColor color) {
        this.currentColor = color;
        EventBus.post(new ColorChanged(color));
    }

    public Optional<DoodleColor> getByLabel(String label) {
        return filterColors(color -> color.getLabel().equals(label));
    }

    public List<DoodleColor> allColors() {
        return Collections.unmodifiableList(this.colors);
    }

    private void loadSettings() {
        this.colors = new ArrayList<>();

        Settings.instance().settings().forEach(setting -> {
            if (setting.name().startsWith("color.")) {
                this.colors.add(new DoodleColor(setting.value()));
            }
        });
    }

    private Optional<DoodleColor> filterColors(Predicate<DoodleColor> test) {
        return this.colors.stream()
                .filter(test)
                .findFirst();
    }

}
