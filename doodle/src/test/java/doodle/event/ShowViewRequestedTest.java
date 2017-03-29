package doodle.event;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ShowViewRequestedTest {
    @Test
    public void handlesShowViewRequested() {
        ShowViewRequestedHandler showViewRequestedHandler = new ShowViewRequestedHandler();

        EventBus.subscribe(showViewRequestedHandler);

        EventBus.post(new ShowViewRequested());

        assertTrue(showViewRequestedHandler.called);
    }

    class ShowViewRequestedHandler {
        boolean called = false;

        @Subscribe
        public void onShowViewRequested(ShowViewRequested event) {
            this.called = true;
        }
    }
}
