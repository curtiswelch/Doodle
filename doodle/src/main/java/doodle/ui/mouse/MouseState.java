package doodle.ui.mouse;

import java.awt.event.MouseEvent;

public interface MouseState {

    void init(Mouse mouse, MouseEvent event);

    void mousePressed(Mouse mouse, MouseEvent event);

    void mouseReleased(Mouse mouse, MouseEvent event);

    void mouseDragged(Mouse mouse, MouseEvent event);

}
