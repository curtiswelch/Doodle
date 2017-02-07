package doodle.ui.mouse;

import java.awt.event.MouseEvent;

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

    void switchState(MouseState state, MouseEvent event) {
        this.state = state;
        this.state.init(this, event);
    }
}
