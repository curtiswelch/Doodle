package doodle.window;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import doodle.DoodleColor;
import doodle.DoodleController;
import doodle.DoodleFactory;

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
            	break;

            case KeyEvent.VK_1:
            	DoodleFactory.instance().switchType(DoodleFactory.DoodleType.BOX);
            	break;

            case KeyEvent.VK_2:
            	DoodleFactory.instance().switchType(DoodleFactory.DoodleType.ELLIPSE);
            	break;

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

