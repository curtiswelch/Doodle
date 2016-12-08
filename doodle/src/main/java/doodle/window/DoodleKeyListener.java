package doodle.window;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import doodle.DoodleController;
import doodle.DoodleColor;

public class DoodleKeyListener implements KeyListener {
    private DoodleController doodle;

    public DoodleKeyListener(DoodleController doodle) {
        this.doodle = doodle;
    }

    @Override
	public void keyPressed(KeyEvent event) {
        switch (event.getKeyCode()) {
            case KeyEvent.VK_C :
                if (event.isControlDown()) {
                	this.doodle.clearDoodles();
                }
                break;

            case KeyEvent.VK_Z:
                if (event.isControlDown()) {
                	this.doodle.undo();
                }
                break;
            case KeyEvent.VK_ESCAPE:
            	this.doodle.hideView();

            default:
                DoodleColor doodleColor = DoodleColor.getByKeyCode(event.getKeyCode());

                if (doodleColor != null) {
                	this.doodle.setDoodleColor(doodleColor);
                }
                break;
        }
    }

    @Override
	public void keyReleased(KeyEvent event) {
    }

    @Override
	public void keyTyped(KeyEvent event) {
    }
}

