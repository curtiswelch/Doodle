package doodle.event;

import doodle.color.DoodleColor;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertEquals;

public class ColorChangedTest {

    @Test
    public void handlesColorChangedEvent() {
        ColorChangedHandler colorChangedHandler = new ColorChangedHandler();

        EventBus.subscribe(colorChangedHandler);

        EventBus.post(new ColorChanged(new DoodleColor("White", 1, Color.WHITE)));

        assertEquals(Color.WHITE, colorChangedHandler.color);
    }

    class ColorChangedHandler {
        Color color = Color.BLUE;

        @Subscribe
        public void onColorChanged(ColorChanged event) {
            this.color = event.newColor().getColor();
        }
    }
}
