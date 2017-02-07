package doodle.ui;

import doodle.color.DoodleColorRegistry;
import doodle.event.DoodlesChanged;
import doodle.event.EventBus;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public enum DoodleCollection {
    INSTANCE;

    private List<Doodle> doodles = new ArrayList<>();
    private Doodle newDoodle;

    public List<Doodle> doodles() {
        List<Doodle> allDoodles = new ArrayList<>(this.doodles.size() + 1);

        allDoodles.addAll(this.doodles);

        if (this.newDoodle != null) {
            allDoodles.add(this.newDoodle);
        }

        return allDoodles;
    }

    public void clearDoodles() {
        this.doodles = new ArrayList<>();
        this.newDoodle = null;

        postDoodlesChanged();
    }

    public void addNewDoodle(int x, int y) {
        this.newDoodle = DoodleFactory.INSTANCE.create();
        this.newDoodle.setStartingPoint(x, y);

        this.newDoodle.setColor(DoodleColorRegistry.INSTANCE.currentColor().getColor());

        postDoodlesChanged();
    }

    public void updateNewDoodle(int x, int y) {
        this.newDoodle.setEndingPoint(x, y);

        postDoodlesChanged();
    }

    public void saveNewDoodle(int finalX, int finalY) {
        this.newDoodle.setEndingPoint(finalX, finalY);

        if (this.newDoodle.isMinimumSize()) {
            this.doodles.add(this.newDoodle);
        }

        this.newDoodle = null;

        postDoodlesChanged();
    }

    private void removeDoodle(Doodle doodle) {
        this.doodles.remove(doodle);

        postDoodlesChanged();
    }

    public void removeDoodleAt(int x, int y) {
        Optional<Doodle> remove = this.doodles.stream().
                filter(doodle -> doodle.hitTest(x, y)).
                max(Comparator.comparingInt(Doodle::getId));

        remove.ifPresent(this::removeDoodle);
    }

    public void undo() {
        if (!this.doodles.isEmpty()) {
            this.doodles.remove(this.doodles.size() - 1);
        }

        postDoodlesChanged();
    }

    private void postDoodlesChanged() {
        EventBus.post(new DoodlesChanged());
    }
}
