package doodle.color;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.lang.reflect.Field;

class StringDefinitionParser {
    private static final int NAME = 0;
    private static final int KEY = 1;
    private static final int RED = 2;
    private static final int GREEN = 3;
    private static final int BLUE = 4;

    DoodleColor parse(String definition) {
        String[] parts = splitDefinitionParts(definition);

        return new DoodleColor(parts[NAME], getKeyCodeFromParts(parts), getColorFromParts(parts));
    }

    private String[] splitDefinitionParts(String definition) {
        String[] parts = definition.split(",");

        if (parts.length != 5) {
            throw new IllegalArgumentException("Invalid DoodleColor String definition: '" + definition + "'");
        }

        return parts;
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
