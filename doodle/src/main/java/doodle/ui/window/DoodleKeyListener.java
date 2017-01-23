package doodle.ui.window;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import doodle.DoodleController;

public class DoodleKeyListener implements KeyListener {
    private DoodleController doodle;

    DoodleKeyListener(DoodleController doodle) {
        this.doodle = doodle;
    }

    @Override
    public void keyPressed(KeyEvent event) {
        this.doodle.handleKeyInput(event);
    }

    @Override
    public void keyReleased(KeyEvent event) {
    }

    @Override
    public void keyTyped(KeyEvent event) {
    }
}

