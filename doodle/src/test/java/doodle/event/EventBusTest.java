package doodle.event;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EventBusTest {
    private EventBus eventBus;

    @Before
    public void newEventBus() {
        eventBus = new EventBus();
    }

    @Test
    public void respondsToCorrectEvent() {
        TestHandler handler = new TestHandler();

        eventBus.subscribe(handler);

        eventBus.post(new Boolean(true));

        assertEquals(true, handler.value());
    }

    @Test
    public void doesNotRespondToWrongEvent() {
        TestHandler handler = new TestHandler();

        eventBus.subscribe(handler);

        eventBus.post(new Integer(1));

        assertEquals(false, handler.value());
    }

    @Test(expected = IllegalArgumentException.class)
    public void hatesHandlersWithoutParameters() {
        BadTestHandler badTestHandler = new BadTestHandler();

        eventBus.subscribe(badTestHandler);
    }

    @Test(expected = NullPointerException.class)
    public void hatesNullHandlers() {
        eventBus.subscribe(null);
    }

    class TestHandler {
        private boolean value = false;

        @Subscribe
        public void handle(Boolean value) {
            this.value = value;
        }

        public boolean value() {
            return this.value;
        }
    }

    class BadTestHandler {
        @Subscribe
        public void handle() {
        }
    }
}
