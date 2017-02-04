package doodle.color;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.lang.reflect.Field;

public class DoodleColor {
    private static final int NAME = 0;
    private static final int KEY = 1;
    private static final int RED = 2;
    private static final int GREEN = 3;
    private static final int BLUE = 4;

    private String label;
    private int keyCode;
    private Color color;

    public DoodleColor(String label, int keyCode, Color color) {
        this.label = label;
        this.keyCode = keyCode;
        this.color = color;
    }

    public DoodleColor(String definition) {
        String[] definitionParts = definition.split(",");

        if (definitionParts.length != 5) {
            throw new IllegalArgumentException("Invalid DoodleColor String definition: '" + definition + "'");
        }

        this.label = definitionParts[NAME];
        this.color = getColorFromParts(definitionParts);
        this.keyCode = getKeyCodeFromParts(definitionParts);
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

    private Color getColorFromParts(String[] definitionParts) {
        return new Color(
                Integer.decode(definitionParts[RED].trim()),
                Integer.decode(definitionParts[GREEN].trim()),
                Integer.decode(definitionParts[BLUE].trim())
        );
    }

    private int getKeyCodeFromParts(String[] definitionParts) throws IllegalArgumentException {
        String keyValue = definitionParts[KEY];

        try {
            Field field = KeyEvent.class.getField("VK_" + keyValue.trim().toUpperCase());
            return field.getInt(null);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid DoodleColor KeyCode definition: '" + keyValue + "'");
        }
    }

}

