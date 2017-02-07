package doodle.ui.mouse;

import doodle.ui.window.DoodleView;

import java.awt.event.MouseEvent;

public class Mouse extends AbstractMouseListener {
    private MouseState state;

    private DoodleView view;

    public Mouse(DoodleView view) {
        this.state = Idle.INSTANCE;
        this.view = view;
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

    void showMenu(int x, int y) {
        this.view.showMenu(x, y);
    }

    void switchState(MouseState state, MouseEvent event) {
        this.state = state;
        this.state.init(this, event);
    }
}
