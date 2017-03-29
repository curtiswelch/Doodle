package doodle.event;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class DoodlesChangedTest {
    @Test
    public void handlesColorChangedEvent() {
        DoodlesChangedHandler doodlesChangedHandler = new DoodlesChangedHandler();

        EventBus.subscribe(doodlesChangedHandler);

        EventBus.post(new DoodlesChanged());

        assertTrue(doodlesChangedHandler.called);
    }

    class DoodlesChangedHandler {
        boolean called = false;

        @Subscribe
        public void onColorChanged(DoodlesChanged event) {
            this.called = true;
        }
    }
}
