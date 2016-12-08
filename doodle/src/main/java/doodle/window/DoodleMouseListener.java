package doodle.window;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import doodle.DoodleBox;

public class DoodleMouseListener implements MouseListener, MouseMotionListener {

	private final double MIN_SIZE = 5.0;

    private DoodleView doodle;
    private DoodleBox doodleBox;
    private int startX;
    private int startY;

    public DoodleMouseListener(DoodleView doodle) {
        this.doodle = doodle;
    }

	@Override
	public void mouseDragged(MouseEvent event) {
        if (this.doodleBox == null) {
            this.doodleBox = new DoodleBox();
            this.startX = event.getX();
            this.startY = event.getY();
            this.doodle.addDoodle(this.doodleBox);
        }

        this.doodleBox.set(this.startX, this.startY, event.getX(), event.getY());

        this.doodle.repaint();
    }

	@Override
	public void mouseReleased(MouseEvent event) {
        if (this.doodleBox != null) {
            this.doodleBox.set(this.startX, this.startY, event.getX(), event.getY());
            Rectangle r = this.doodleBox.toRectangle();

            if (r.getWidth() < MIN_SIZE || r.getHeight() < MIN_SIZE) {
                this.doodle.undo();
            }
        }

        if (event.isPopupTrigger()) {
            this.doodle.showMenu(event.getX(), event.getY());
        } else if (this.doodleBox == null) {
            this.doodle.removeDoodleAt(event.getX(), event.getY());
        }

        this.doodleBox = null;
    }

	@Override
	public void mouseMoved(MouseEvent event) {
    }

	@Override
	public void mouseClicked(MouseEvent event) {
    }

	@Override
	public void mouseEntered(MouseEvent event) {
    }

	@Override
	public void mouseExited(MouseEvent event) {
    }

	@Override
	public void mousePressed(MouseEvent event) {
    }
}

