package doodle.ui.mouse;

import doodle.ui.DoodleCollection;

import java.awt.event.MouseEvent;

public enum Idle implements MouseState {
    INSTANCE;

    @Override
    public void init(Mouse mouse, MouseEvent event) {
    }

    @Override
    public void mousePressed(Mouse mouse, MouseEvent event) {
    }

    @Override
    public void mouseReleased(Mouse mouse, MouseEvent event) {
        if (event.isPopupTrigger()) {
            mouse.showMenu(event.getX(), event.getY());
        } else {
            DoodleCollection.INSTANCE.removeDoodleAt(event.getX(), event.getY());
        }
    }

    @Override
    public void mouseDragged(Mouse mouse, MouseEvent event) {
        mouse.switchState(Draw.INSTANCE, event);
    }
}
