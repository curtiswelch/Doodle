package doodle.ui;

import doodle.color.DoodleColor;
import doodle.color.DoodleColorRegistry;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public enum DoodleCollection {
    INSTANCE;

    private List<Doodle> doodles = new ArrayList<>();
    private DoodleColor doodleColor = DoodleColorRegistry.INSTANCE.defaultColor();

    public List<Doodle> doodles() {
        return this.doodles;
    }

    public void setDoodleColor(DoodleColor color) {
        this.doodleColor = color;
    }

    public void clearDoodles() {
        this.doodles = new ArrayList<>();
    }

    public void addDoodle(Doodle doodle) {
        doodle.setColor(new Color(this.doodleColor.getColor().getRGB()));
        this.doodles.add(doodle);
    }

    public void removeDoodle(Doodle doodle) {
        this.doodles.remove(doodle);
    }

    public void removeDoodleAt(int x, int y) {
        Optional<Doodle> remove = this.doodles.stream().
                filter(doodle -> doodle.hitTest(x, y)).
                max(Comparator.comparingInt(Doodle::getId));

        remove.ifPresent(doodle -> {
            this.doodles.remove(doodle);
        });
    }

    public void undo() {
        if (!this.doodles.isEmpty()) {
            this.doodles.remove(this.doodles.size() - 1);
        }
    }
}
