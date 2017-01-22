package doodle;

import java.awt.Color;
import java.util.Optional;
import java.util.function.Predicate;

public enum DoodleColor {
    BLUE(Strings.BLUE, new Color(43, 106, 255)),
    RED(Strings.RED, new Color(235, 89, 96)),
    GREEN(Strings.GREEN, new Color(42, 0xce, 0x2a)),
    PURPLE(Strings.PURPLE, new Color(192, 0, 194)),
    YELLOW(Strings.YELLOW, new Color(255, 255, 0)),
    ORANGE(Strings.ORANGE, new Color(255, 194, 68)),
    WHITE(Strings.WHITE, Color.WHITE),
    BLACK(Strings.BLACK, Color.BLACK);

    private String label;
    private Color color;

    DoodleColor(String label, Color color) {
        this.label = label;
        this.color = color;
    }

    public String getLabel() {
        return this.label;
    }

    public Color getColor() {
        return this.color;
    }

    public static Optional<DoodleColor> getByLabel(String label) {
        return filterColors(color -> color.getLabel().equals(label));
    }

    private static Optional<DoodleColor> filterColors(Predicate<DoodleColor> test) {
        Optional<DoodleColor> result = Optional.empty();

        for (DoodleColor color : DoodleColor.values()) {
            if (test.test(color)) {
                result = Optional.of(color);
                break;
            }
        }

        return result;
    }
}

