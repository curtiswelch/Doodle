package doodle.ui.mouse;

import doodle.ui.DoodleCollection;

import java.awt.event.MouseEvent;

public enum Draw implements MouseState {
    INSTANCE;

    @Override
    public void init(Mouse mouse, MouseEvent event) {
        DoodleCollection.addNewDoodle(event.getX(), event.getY());
    }

    @Override
    public void mousePressed(Mouse mouse, MouseEvent event) {
    }

    @Override
    public void mouseReleased(Mouse mouse, MouseEvent event) {
        DoodleCollection.saveNewDoodle(event.getX(), event.getY());

        mouse.switchState(Idle.INSTANCE, event);
    }

    @Override
    public void mouseDragged(Mouse mouse, MouseEvent event) {
        DoodleCollection.updateNewDoodle(event.getX(), event.getY());
    }

}
