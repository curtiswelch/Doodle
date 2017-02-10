package doodle.ui.mouse;

import doodle.event.EventBus;
import doodle.event.MenuRequested;
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
            EventBus.post(new MenuRequested(event.getX(), event.getY()));
        } else {
            DoodleCollection.removeDoodleAt(event.getX(), event.getY());
        }
    }

    @Override
    public void mouseDragged(Mouse mouse, MouseEvent event) {
        mouse.switchState(Draw.INSTANCE, event);
    }
}
