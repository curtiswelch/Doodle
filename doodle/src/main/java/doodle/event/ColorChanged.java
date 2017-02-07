package doodle.event;

import doodle.color.DoodleColor;

public class ColorChanged {
    private DoodleColor newColor;

    public ColorChanged(DoodleColor newColor) {
        this.newColor = newColor;
    }

    public DoodleColor newColor() {
        return this.newColor;
    }
}
