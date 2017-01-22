package doodle;

import java.awt.event.KeyEvent;
import java.util.function.Consumer;

public class KeyInputAction {
    private final int keyCode;
    private final Consumer<KeyEvent> action;

    public KeyInputAction(int keyCode, Consumer<KeyEvent> action) {
        this.keyCode = keyCode;
        this.action = action;
    }

    public boolean handlesKey(int keyCode) {
        return this.keyCode == keyCode;
    }

    public void performAction(KeyEvent event) {
        this.action.accept(event);
    }
}
