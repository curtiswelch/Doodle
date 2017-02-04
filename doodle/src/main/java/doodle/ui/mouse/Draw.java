package doodle.ui.mouse;

import doodle.ui.Doodle;
import doodle.ui.DoodleFactory;

import java.awt.event.MouseEvent;

public enum Draw implements MouseState {
    INSTANCE;

    private Doodle doodle;

    @Override
    public void init(Mouse mouse, MouseEvent event) {
        this.doodle = DoodleFactory.INSTANCE.create();
        this.doodle.setStartingPoint(event.getX(), event.getY());

        mouse.addDoodle(this.doodle);
    }

    @Override
    public void mousePressed(Mouse mouse, MouseEvent event) {
    }

    @Override
    public void mouseReleased(Mouse mouse, MouseEvent event) {
        this.doodle.setEndingPoint(event.getX(), event.getY());
        mouse.updateView();

        if (!this.doodle.isMinimumSize()) {
            mouse.removeDoodle(this.doodle);
        }

        mouse.switchState(Idle.INSTANCE, event);
    }

    @Override
    public void mouseDragged(Mouse mouse, MouseEvent event) {
        this.doodle.setEndingPoint(event.getX(), event.getY());
        mouse.updateView();
    }
}
