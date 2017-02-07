package doodle.ui.mouse;

import doodle.DoodleController;
import doodle.event.DoodlesChanged;
import doodle.event.EventBus;
import doodle.ui.Doodle;
import doodle.ui.DoodleCollection;
import doodle.ui.DoodleFactory;

import java.awt.event.MouseEvent;

public enum Draw implements MouseState {
    INSTANCE;

    @Override
    public void init(Mouse mouse, MouseEvent event) {
        DoodleCollection.INSTANCE.addNewDoodle(event.getX(), event.getY());
    }

    @Override
    public void mousePressed(Mouse mouse, MouseEvent event) {
    }

    @Override
    public void mouseReleased(Mouse mouse, MouseEvent event) {
        DoodleCollection.INSTANCE.saveNewDoodle(event.getX(), event.getY());

        mouse.switchState(Idle.INSTANCE, event);
    }

    @Override
    public void mouseDragged(Mouse mouse, MouseEvent event) {
        DoodleCollection.INSTANCE.updateNewDoodle(event.getX(), event.getY());
    }

}
