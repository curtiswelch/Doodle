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
        DoodleCollection.clearDoodles();

        DoodleCollection.addNewDoodle(20, 20);
        DoodleCollection.saveNewDoodle(100, 100);

        DoodleCollection.addNewDoodle(200, 200);
        DoodleCollection.saveNewDoodle(100, 100);

        DoodleCollection.addNewDoodle(500, 500);
        DoodleCollection.saveNewDoodle(1000, 1000);
    }

    @Test
    public void canAddADoodle() {
        DoodleCollection.addNewDoodle(10, 10);

        DoodleCollection.saveNewDoodle(20, 20);

        List<Doodle> doodles = DoodleCollection.doodles();

        assertEquals(4, doodles.size());
    }

    @Test
    public void onlyAddsDoodlesWithAMinimumSize() {
        DoodleCollection.addNewDoodle(0, 0);
        DoodleCollection.saveNewDoodle(1, 1);

        List<Doodle> doodles = DoodleCollection.doodles();

        assertEquals(3, doodles.size());
    }

    @Test
    public void canClearDoodles() {
        DoodleCollection.clearDoodles();

        List<Doodle> doodles = DoodleCollection.doodles();

        assertEquals(0, doodles.size());
    }

    @Test
    public void canRemoveDoodleByCoordinates() {
        DoodleCollection.removeDoodleAt(50, 50);

        List<Doodle> doodles = DoodleCollection.doodles();

        assertEquals(2, doodles.size());
    }

    @Test
    public void canUndoDoodleCreation() {
        DoodleCollection.undo();

        List<Doodle> doodles = DoodleCollection.doodles();

        assertEquals(2, doodles.size());
    }

    @Test
    public void canUpdateNewDoodle() {
        DoodleCollection.clearDoodles();

        DoodleCollection.addNewDoodle(0, 0);
        DoodleCollection.updateNewDoodle(10, 10);

        List<Doodle> doodles = DoodleCollection.doodles();

        boolean isRightSize = doodles.get(0).hitTest(5, 5);

        assertTrue(isRightSize);
    }
}
