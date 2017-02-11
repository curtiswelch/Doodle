package doodle.ui.mouse;

import doodle.color.DoodleColorRegistry;

import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class Mouse extends AbstractMouseListener {
    private MouseState state;

    public Mouse() {
        this.state = Idle.INSTANCE;
    }

    @Override
    public void mousePressed(MouseEvent event) {
        this.state.mousePressed(this, event);
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        this.state.mouseReleased(this, event);
    }

    @Override
    public void mouseDragged(MouseEvent event) {
        this.state.mouseDragged(this, event);
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        DoodleColorRegistry.adjustOpacity(e.getPreciseWheelRotation());
    }

    void switchState(MouseState state, MouseEvent event) {
        this.state = state;
        this.state.init(this, event);
    }
}
