package doodle;

import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.awt.event.KeyEvent;

import static org.junit.Assert.*;

public class KeyInputActionTest {
    private KeyInputAction action;

    @Before
    public void createAction() {
        this.action = new KeyInputAction(KeyEvent.VK_C, e -> e.setKeyChar('t'));
    }

    @Test
    public void instantiation() {
        assertNotNull(this.action);
    }

    @Test
    public void matchesGivenKeyCode() {
        assertTrue(this.action.handlesKey(KeyEvent.VK_C));
    }

    @Test
    public void doesNotHandleWrongKey() {
        assertFalse(this.action.handlesKey(KeyEvent.VK_B));
    }

    @Test
    public void performsAction() {
        KeyEvent event = new KeyEvent(new Component() {
            @Override
            public String getName() {
                return "toot";
            }
        }, 0, 0, 0, KeyEvent.VK_C, 'c');

        this.action.performAction(event);

        assertEquals('t', event.getKeyChar());
    }


}
