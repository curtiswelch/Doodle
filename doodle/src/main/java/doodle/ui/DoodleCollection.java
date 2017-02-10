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

    private static List<Doodle> doodles = new ArrayList<>();
    private static Doodle newDoodle;

    public static List<Doodle> doodles() {
        List<Doodle> allDoodles = new ArrayList<>(doodles.size() + 1);

        allDoodles.addAll(doodles);

        if (newDoodle != null) {
            allDoodles.add(newDoodle);
        }

        return allDoodles;
    }

    public static void clearDoodles() {
        doodles = new ArrayList<>();
        newDoodle = null;

        postDoodlesChanged();
    }

    public static void addNewDoodle(int x, int y) {
        newDoodle = DoodleFactory.create();
        newDoodle.setStartingPoint(x, y);

        newDoodle.setColor(DoodleColorRegistry.currentColor().getColor());

        postDoodlesChanged();
    }

    public static void updateNewDoodle(int x, int y) {
        newDoodle.setEndingPoint(x, y);

        postDoodlesChanged();
    }

    public static void saveNewDoodle(int finalX, int finalY) {
        newDoodle.setEndingPoint(finalX, finalY);

        if (newDoodle.isMinimumSize()) {
            doodles.add(newDoodle);
        }

        newDoodle = null;

        postDoodlesChanged();
    }

    public static void removeDoodleAt(int x, int y) {
        Optional<Doodle> remove = doodles.stream().
                filter(doodle -> doodle.hitTest(x, y)).
                max(Comparator.comparingInt(Doodle::getId));

        remove.ifPresent(DoodleCollection::removeDoodle);
    }

    public static void undo() {
        if (!doodles.isEmpty()) {
            int lastDoodleIndex = doodles.size() - 1;
            Doodle lastDoodle = doodles.get(lastDoodleIndex);

            removeDoodle(lastDoodle);
        }
    }

    private static void removeDoodle(Doodle doodle) {
        doodles.remove(doodle);

        postDoodlesChanged();
    }

    private static void postDoodlesChanged() {
        EventBus.post(new DoodlesChanged());
    }
}
