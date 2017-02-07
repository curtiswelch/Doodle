package doodle.ui;

import doodle.color.DoodleColorRegistry;
import doodle.event.DoodlesCleared;
import doodle.event.EventBus;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public enum DoodleCollection {
    INSTANCE;

    private List<Doodle> doodles = new ArrayList<>();

    public List<Doodle> doodles() {
        return this.doodles;
    }

    public void clearDoodles() {
        this.doodles = new ArrayList<>();
        EventBus.post(new DoodlesCleared());
    }

    public void addDoodle(Doodle doodle) {
        doodle.setColor(DoodleColorRegistry.INSTANCE.currentColor().getColor());
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
