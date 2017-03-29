package doodle.event;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class OpacityChangedTest {
    @Test
    public void handlesOpacityChangedEvent() {
        OpacityChangedHandler opacityChangedHandler = new OpacityChangedHandler();

        EventBus.subscribe(opacityChangedHandler);

        EventBus.post(new OpacityChanged());

        assertTrue(opacityChangedHandler.called);
    }

    class OpacityChangedHandler {
        boolean called = false;

        @Subscribe
        public void onOpacityChanged(OpacityChanged event) {
            this.called = true;
        }
    }
}
