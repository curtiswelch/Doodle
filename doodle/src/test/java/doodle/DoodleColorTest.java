package doodle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;

public class DoodleColorTest {

    @Test
    public void testGetByLabel() {
        Optional<DoodleColor> color = DoodleColor.getByLabel("Blue");

        assertTrue(color.isPresent());
        assertEquals(DoodleColor.BLUE, color.get());
    }

}
