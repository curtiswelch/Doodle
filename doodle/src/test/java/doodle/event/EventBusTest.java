package doodle.event;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EventBusTest {
    @Test
    public void respondsToCorrectEvent() {
        TestHandler handler = new TestHandler();

        EventBus.subscribe(handler);

        EventBus.post(new Boolean(true));

        assertEquals(true, handler.value());
    }

    @Test
    public void doesNotRespondToWrongEvent() {
        TestHandler handler = new TestHandler();

        EventBus.subscribe(handler);

        EventBus.post(new Integer(1));

        assertEquals(false, handler.value());
    }

    @Test(expected = IllegalArgumentException.class)
    public void hatesHandlersWithoutParameters() {
        BadTestHandler badTestHandler = new BadTestHandler();

        EventBus.subscribe(badTestHandler);
    }

    @Test(expected = NullPointerException.class)
    public void hatesNullHandlers() {
        EventBus.subscribe(null);
    }

    @Test
    public void worksWithPrivateMethods() {
        PrivateMethodHandler handler = new PrivateMethodHandler();

        EventBus.subscribe(handler);

        EventBus.post(new Boolean(true));

        assertEquals(true, handler.value());
    }

    abstract class CheckedHandler {
        boolean value = false;

        boolean value() {
            return this.value;
        }
    }

    class TestHandler extends CheckedHandler {
        @Subscribe
        public void handle(Boolean value) {
            this.value = value;
        }
    }

    class BadTestHandler {
        @Subscribe
        public void handle() {
        }
    }

    class PrivateMethodHandler extends CheckedHandler {
        @Subscribe
        private void handle(Boolean value) {
            this.value = value;
        }
    }
}
