package doodle;

import doodle.ui.Doodle;
import doodle.ui.DoodleCollection;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DoodleCollectionTest {

    @Before
    public void setUpDoodles() {
        DoodleCollection.INSTANCE.clearDoodles();

        DoodleCollection.INSTANCE.addNewDoodle(20, 20);
        DoodleCollection.INSTANCE.saveNewDoodle(100, 100);

        DoodleCollection.INSTANCE.addNewDoodle(200, 200);
        DoodleCollection.INSTANCE.saveNewDoodle(100, 100);

        DoodleCollection.INSTANCE.addNewDoodle(500, 500);
        DoodleCollection.INSTANCE.saveNewDoodle(1000, 1000);
    }

    @Test
    public void canAddADoodle() {
        DoodleCollection.INSTANCE.addNewDoodle(10, 10);

        DoodleCollection.INSTANCE.saveNewDoodle(20, 20);

        List<Doodle> doodles = DoodleCollection.INSTANCE.doodles();

        assertEquals(4, doodles.size());
    }

    @Test
    public void onlyAddsDoodlesWithAMinimumSize() {
        DoodleCollection.INSTANCE.addNewDoodle(0, 0);
        DoodleCollection.INSTANCE.saveNewDoodle(1, 1);

        List<Doodle> doodles = DoodleCollection.INSTANCE.doodles();

        assertEquals(3, doodles.size());
    }

    @Test
    public void canClearDoodles() {
        DoodleCollection.INSTANCE.clearDoodles();

        List<Doodle> doodles = DoodleCollection.INSTANCE.doodles();

        assertEquals(0, doodles.size());
    }

    @Test
    public void canRemoveDoodleByCoordinates() {
        DoodleCollection.INSTANCE.removeDoodleAt(50, 50);

        List<Doodle> doodles = DoodleCollection.INSTANCE.doodles();

        assertEquals(2, doodles.size());
    }

    @Test
    public void canUndoDoodleCreation() {
        DoodleCollection.INSTANCE.undo();

        List<Doodle> doodles = DoodleCollection.INSTANCE.doodles();

        assertEquals(2, doodles.size());
    }

    @Test
    public void canUpdateNewDoodle() {
        DoodleCollection.INSTANCE.clearDoodles();

        DoodleCollection.INSTANCE.addNewDoodle(0, 0);
        DoodleCollection.INSTANCE.updateNewDoodle(10, 10);

        List<Doodle> doodles = DoodleCollection.INSTANCE.doodles();

        boolean isRightSize = doodles.get(0).hitTest(5, 5);

        assertTrue(isRightSize);
    }
}
