package doodle.color;

import java.awt.Color;

public class DoodleColor {
    private String label;
    private int keyCode;
    private Color color;

    public DoodleColor(String label, int keyCode, java.awt.Color color) {
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

}

