package doodle;

import java.awt.Color;

public enum DoodleColor {
    BLUE(Strings.BLUE, 66, new Color(43, 106, 255)),
    RED(Strings.RED, 82, new Color(235, 89, 96)),
    GREEN(Strings.GREEN, 71, new Color(51, 164, 5)),
    PURPLE(Strings.PURPLE, 80, new Color(192, 0, 194)),
    YELLOW(Strings.YELLOW, 89, new Color(255, 250, 141)),
    ORANGE(Strings.ORANGE, 79, new Color(255, 194, 68)),
    WHITE(Strings.WHITE, 87, Color.WHITE),
    BLACK(Strings.BLACK, 75, Color.BLACK);

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

    public static DoodleColor getByKeyCode(int keyCode) {
    	for(DoodleColor c : DoodleColor.values()) {
    		if(c.getKeyCode() == keyCode) {
    			return c;
    		}
    	}

    	return null;
    }

    public static DoodleColor getByLabel(String label) {
        for(DoodleColor c : DoodleColor.values()) {
        	if(c.getLabel().equals(label)) {
        		return c;
        	}
        }

        return null;
    }
}

