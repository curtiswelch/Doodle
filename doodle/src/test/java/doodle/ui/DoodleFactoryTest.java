package doodle.ui;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class DoodleFactoryTest {

    @Test
    public void testCreateDoodles() {
        DoodleFactory factory = DoodleFactory.INSTANCE;

        Doodle doodle = factory.create();

        assertNotNull(doodle);

        factory.switchType(DoodleFactory.DoodleType.ELLIPSE);

        doodle = factory.create();

        assertNotNull(doodle);

        factory.switchType(DoodleFactory.DoodleType.ROUNDED_BOX);

        doodle = factory.create();

        assertNotNull(doodle);
    }

}
