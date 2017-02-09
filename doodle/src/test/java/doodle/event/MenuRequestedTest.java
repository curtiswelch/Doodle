package doodle.event;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MenuRequestedTest {

    @Test
    public void handlesMenuRequestedEvent() {
        MenuRequestedHandler menuRequestedHandler = new MenuRequestedHandler();

        EventBus.subscribe(menuRequestedHandler);

        EventBus.post(new MenuRequested(20, 17));

        assertEquals(20, menuRequestedHandler.x);
        assertEquals(17, menuRequestedHandler.y);
    }

    class MenuRequestedHandler {
        int x;
        int y;

        @Subscribe
        public void handleMenuRequested(MenuRequested event) {
            this.x = event.x();
            this.y = event.y();
        }
    }
}
