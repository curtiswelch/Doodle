package doodle.ui.tray;

import doodle.DoodleController;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class DoodleTrayMouseListener implements MouseListener {

    private DoodleController doodle;

    DoodleTrayMouseListener(DoodleController doodle) {
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
