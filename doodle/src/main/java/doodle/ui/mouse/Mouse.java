package doodle.ui.mouse;

import doodle.ui.Doodle;
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
        System.out.println(state.getClass().getSimpleName() + " mousePressed");
        this.state.mousePressed(this, event);
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        System.out.println(state.getClass().getSimpleName() + " mouseReleased");
        this.state.mouseReleased(this, event);
    }

    @Override
    public void mouseDragged(MouseEvent event) {
        System.out.println(state.getClass().getSimpleName() + " mouseDragged");
        this.state.mouseDragged(this, event);
    }

    void addDoodle(Doodle doodle) {
        this.view.addDoodle(doodle);
        this.updateView();
    }

    void removeDoodle(Doodle doodle) {
        this.view.removeDoodle(doodle);
        this.updateView();
    }

    void removeDoodleAt(int x, int y) {
        this.view.removeDoodleAt(x, y);
        this.updateView();
    }

    void updateView() {
        this.view.repaint();
    }

    void showMenu(int x, int y) {
        this.view.showMenu(x, y);
        this.updateView();
    }

    void switchState(MouseState state, MouseEvent event) {
        this.state = state;
        this.state.init(this, event);
    }
}
