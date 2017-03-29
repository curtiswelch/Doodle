package doodle.event;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class HideViewRequestedTest {
    @Test
    public void handlesHideViewRequestedChangedEvent() {
        HideViewRequestedHandler hideViewRequestedHandler = new HideViewRequestedHandler();

        EventBus.subscribe(hideViewRequestedHandler);

        EventBus.post(new HideViewRequested());

        assertTrue(hideViewRequestedHandler.called);
    }

    class HideViewRequestedHandler {
        boolean called = false;

        @Subscribe
        public void onHideViewRequested(HideViewRequested event) {
            this.called = true;
        }
    }
}
