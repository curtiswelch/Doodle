package doodle.ui.window;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import doodle.Doodle;
import doodle.DoodleFactory;

public class DoodleMouseListener implements MouseListener, MouseMotionListener {

    private DoodleView doodleView;
    private Doodle doodle;

    DoodleMouseListener(DoodleView doodleView) {
        this.doodleView = doodleView;
    }

    @Override
    public void mousePressed(MouseEvent event) {
    	this.doodle = DoodleFactory.instance().create();
    	this.doodle.setStartingPoint(event.getX(), event.getY());
    	this.doodleView.addDoodle(this.doodle);
    }

	@Override
	public void mouseDragged(MouseEvent event) {
        if(this.doodle != null) {
            doodle.setEndingPoint(event.getX(), event.getY());
            this.doodleView.repaint();
        }
    }

	@Override
	public void mouseReleased(MouseEvent event) {
        if(this.doodle != null) {
			doodle.setEndingPoint(event.getX(), event.getY());

			if(!doodle.isMinimumSize()) {
				this.doodleView.undo();
				this.doodle = null;
			}
        }

        if (event.isPopupTrigger()) {
            this.doodleView.showMenu(event.getX(), event.getY());
        } else if (this.doodle == null){
            this.doodleView.removeDoodleAt(event.getX(), event.getY());
            this.doodle = null;
        }
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
}

