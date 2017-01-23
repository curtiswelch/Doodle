package doodle.ui;

import static org.junit.Assert.assertNotNull;

import doodle.ui.Doodle;
import doodle.ui.DoodleFactory;
import org.junit.Test;

public class DoodleFactoryTest {

    @Test
    public void testCreateDoodles() {
        DoodleFactory factory = DoodleFactory.instance();

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
