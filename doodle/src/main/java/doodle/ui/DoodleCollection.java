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

    public static List<Doodle> doodles() {
        List<Doodle> allDoodles = new ArrayList<>(INSTANCE.doodles.size() + 1);

        allDoodles.addAll(INSTANCE.doodles);

        if (INSTANCE.newDoodle != null) {
            allDoodles.add(INSTANCE.newDoodle);
        }

        return allDoodles;
    }

    public static void clearDoodles() {
        INSTANCE.doodles = new ArrayList<>();
        INSTANCE.newDoodle = null;

        postDoodlesChanged();
    }

    public static void addNewDoodle(int x, int y) {
        INSTANCE.newDoodle = DoodleFactory.create();
        INSTANCE.newDoodle.setStartingPoint(x, y);

        INSTANCE.newDoodle.setColor(DoodleColorRegistry.currentColor().getColor());

        postDoodlesChanged();
    }

    public static void updateNewDoodle(int x, int y) {
        INSTANCE.newDoodle.setEndingPoint(x, y);

        postDoodlesChanged();
    }

    public static void saveNewDoodle(int finalX, int finalY) {
        INSTANCE.newDoodle.setEndingPoint(finalX, finalY);

        if (INSTANCE.newDoodle.isMinimumSize()) {
            INSTANCE.doodles.add(INSTANCE.newDoodle);
        }

        INSTANCE.newDoodle = null;

        postDoodlesChanged();
    }

    public static void removeDoodleAt(int x, int y) {
        Optional<Doodle> remove = INSTANCE.doodles.stream().
                filter(doodle -> doodle.hitTest(x, y)).
                max(Comparator.comparingInt(Doodle::getId));

        remove.ifPresent(DoodleCollection::removeDoodle);
    }

    public static void undo() {
        if (!INSTANCE.doodles.isEmpty()) {
            int lastDoodleIndex = INSTANCE.doodles.size() - 1;
            Doodle lastDoodle = INSTANCE.doodles.get(lastDoodleIndex);

            removeDoodle(lastDoodle);
        }
    }

    private static void removeDoodle(Doodle doodle) {
        INSTANCE.doodles.remove(doodle);

        postDoodlesChanged();
    }

    private static void postDoodlesChanged() {
        EventBus.post(new DoodlesChanged());
    }
}
