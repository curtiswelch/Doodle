package doodle.window;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import doodle.Doodle;
import doodle.DoodleFactory;

public class DoodleMouseListener implements MouseListener, MouseMotionListener {

    private DoodleView doodleView;
    private Doodle doodle;
    private int startX;
    private int startY;

    public DoodleMouseListener(DoodleView doodle) {
        this.doodleView = doodle;
    }

	@Override
	public void mouseDragged(MouseEvent event) {
        if (this.doodle == null) {
            this.doodle = DoodleFactory.instance().create();
            this.startX = event.getX();
            this.startY = event.getY();
            this.doodle.setStartingPoint(this.startX, this.startY);
            this.doodleView.addDoodle(this.doodle);
        }

        this.doodle.setEndingPoint(event.getX(), event.getY());

        this.doodleView.repaint();
    }

	@Override
	public void mouseReleased(MouseEvent event) {
        if (this.doodle != null) {
            this.doodle.setEndingPoint(event.getX(), event.getY());

            if (!this.doodle.isMinimumSize()) {
                this.doodleView.undo();
            }
        }

        if (event.isPopupTrigger()) {
            this.doodleView.showMenu(event.getX(), event.getY());
        } else if (this.doodle == null) {
            this.doodleView.removeDoodleAt(event.getX(), event.getY());
        }

        this.doodle = null;
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

