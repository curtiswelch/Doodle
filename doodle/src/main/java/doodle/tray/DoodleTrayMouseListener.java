package doodle.tray;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import doodle.Doodle;

public class DoodleTrayMouseListener implements MouseListener {

	private Doodle doodle;

	public DoodleTrayMouseListener(Doodle doodle) {
		this.doodle = doodle;
	}

	@Override
	public void mouseClicked(MouseEvent event) {
        if (event.getButton() == MouseEvent.BUTTON1) {
        	doodle.toggleView();
        }
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

}
