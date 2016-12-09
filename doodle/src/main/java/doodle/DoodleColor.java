package doodle;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.Optional;
import java.util.function.Predicate;

public enum DoodleColor {
    BLUE(Strings.BLUE, KeyEvent.VK_B, new Color(43, 106, 255)),
    RED(Strings.RED, KeyEvent.VK_R, new Color(235, 89, 96)),
    GREEN(Strings.GREEN, KeyEvent.VK_G, new Color(42, 0xce, 0x2a)),
    PURPLE(Strings.PURPLE, KeyEvent.VK_P, new Color(192, 0, 194)),
    YELLOW(Strings.YELLOW, KeyEvent.VK_Y, new Color(255, 255, 0)),
    ORANGE(Strings.ORANGE, KeyEvent.VK_O, new Color(255, 194, 68)),
    WHITE(Strings.WHITE, KeyEvent.VK_W, Color.WHITE),
    BLACK(Strings.BLACK, KeyEvent.VK_K, Color.BLACK);

    private String label;
    private int keyCode;
    private Color color;

    private DoodleColor(String label, int keyCode, Color color) {
        this.label = label;
        this.keyCode = keyCode;
        this.color = color;
    }

    public String getLabel() {
        return this.label;
    }

    public int getKeyCode() {
        return this.keyCode;
    }

    public Color getColor() {
        return this.color;
    }

    public static Optional<DoodleColor> getByKeyCode(int keyCode) {
    	return filterColors(color -> color.getKeyCode() == keyCode);
    }

    public static Optional<DoodleColor> getByLabel(String label) {
    	return filterColors(color -> color.getLabel().equals(label));
    }

    private static Optional<DoodleColor> filterColors(Predicate<DoodleColor> test) {
    	Optional<DoodleColor> result = Optional.empty();

    	for(DoodleColor color : DoodleColor.values()) {
    		if(test.test(color)) {
    			result = Optional.of(color);
    			break;
    		}
    	}

    	return result;
    }
}

