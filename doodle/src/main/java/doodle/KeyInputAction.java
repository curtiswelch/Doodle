package doodle;

import java.awt.event.KeyEvent;
import java.util.function.Consumer;

class KeyInputAction {
    private final int keyCode;
    private final Consumer<KeyEvent> action;

    KeyInputAction(int keyCode, Consumer<KeyEvent> action) {
        this.keyCode = keyCode;
        this.action = action;
    }

    boolean handlesKey(int keyCode) {
        return this.keyCode == keyCode;
    }

    void performAction(KeyEvent event) {
        this.action.accept(event);
    }
}
