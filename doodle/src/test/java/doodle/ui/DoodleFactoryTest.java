package doodle.ui;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class DoodleFactoryTest {

    @Test
    public void testCreateDoodles() {
        Doodle doodle = DoodleFactory.create();

        assertNotNull(doodle);

        DoodleFactory.switchType(DoodleFactory.DoodleType.ELLIPSE);

        doodle = DoodleFactory.create();

        assertNotNull(doodle);

        DoodleFactory.switchType(DoodleFactory.DoodleType.ROUNDED_BOX);

        doodle = DoodleFactory.create();

        assertNotNull(doodle);
    }

}
