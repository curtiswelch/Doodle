package doodle.ui.window;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Optional;

import doodle.Doodle;
import doodle.DoodleFactory;

public class DoodleMouseListener implements MouseListener, MouseMotionListener {

    private DoodleView doodleView;
    private Optional<Doodle> doodle;

    public DoodleMouseListener(DoodleView doodleView) {
        this.doodleView = doodleView;
        this.doodle = Optional.empty();
    }

    @Override
    public void mousePressed(MouseEvent event) {
    	Doodle newDoodle = DoodleFactory.instance().create();
    	newDoodle.setStartingPoint(event.getX(), event.getY());
    	this.doodleView.addDoodle(newDoodle);

    	this.doodle = Optional.of(newDoodle);
    }

	@Override
	public void mouseDragged(MouseEvent event) {
        this.doodle.ifPresent(doodle -> doodle.setEndingPoint(event.getX(), event.getY()));

        this.doodleView.repaint();
    }

	@Override
	public void mouseReleased(MouseEvent event) {
		this.doodle.ifPresent(doodle -> {
			doodle.setEndingPoint(event.getX(), event.getY());

			if(!doodle.isMinimumSize()) {
				this.doodleView.undo();
			}
		});

        if (event.isPopupTrigger()) {
            this.doodleView.showMenu(event.getX(), event.getY());
        } else if (!this.doodle.isPresent()) {
            this.doodleView.removeDoodleAt(event.getX(), event.getY());
        }

        this.doodle = Optional.empty();
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

