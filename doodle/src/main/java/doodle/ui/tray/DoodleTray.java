package doodle.ui.tray;

import doodle.event.EventBus;
import doodle.event.HideViewRequested;
import doodle.event.ShowViewRequested;
import doodle.event.Subscribe;
import doodle.ui.text.Strings;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class DoodleTray extends TrayIcon implements MouseListener {

    private final Image startImage;
    private final Image stopImage;

    public DoodleTray(Image startImage, Image stopImage) {
        super(startImage, Strings.START_DOODLE.value());

        this.startImage = startImage;
        this.stopImage = stopImage;

        this.setPopupMenu(new DoodleTrayMenu());

        this.addMouseListener(this);

        EventBus.subscribe(this);
    }

    @Subscribe
    private void onHideViewRequested(HideViewRequested hideViewRequested) {
        this.setToolTip(Strings.START_DOODLE.value());
        this.setImage(this.startImage);
    }

    @Subscribe
    private void onShowViewRequested(ShowViewRequested showViewRequested) {
        this.setToolTip(Strings.STOP_DOODLE.value());
        this.setImage(this.stopImage);
    }

    private void postViewEvent() {
        if (this.getImage().equals(this.startImage)) {
            EventBus.post(new ShowViewRequested());
        } else {
            EventBus.post(new HideViewRequested());
        }
    }

    @Override
    public void mouseClicked(MouseEvent event) {
        if (event.getButton() == MouseEvent.BUTTON1) {
            postViewEvent();
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

