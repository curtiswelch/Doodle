package doodle.ui.tray;

import doodle.DoodleController;
import doodle.event.*;
import doodle.ui.text.Strings;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class DoodleTray extends TrayIcon implements ActionListener, MouseListener {

    private Image startImage;
    private Image stopImage;

    public DoodleTray(final DoodleController doodle, final Image startImage, Image stopImage) {
        super(startImage, Strings.START_DOODLE.value());

        this.startImage = startImage;
        this.stopImage = stopImage;

        MenuItem settings = new MenuItem(Strings.SETTINGS.value());
        settings.addActionListener(this);

        MenuItem exit = new MenuItem(Strings.EXIT.value());
        exit.addActionListener(this);

        PopupMenu menu = new PopupMenu();
        menu.add(settings);
        menu.addSeparator();
        menu.add(exit);

        this.setPopupMenu(menu);

        this.addMouseListener(this);

        EventBus.subscribe(this);
    }

    @Subscribe
    public void onHideViewRequested(HideViewRequested hideViewRequested) {
        this.setToolTip(Strings.START_DOODLE.value());
        this.setImage(this.startImage);
    }

    @Subscribe
    public void onShowViewRequested(ShowViewRequested showViewRequested) {
        this.setToolTip(Strings.STOP_DOODLE.value());
        this.setImage(this.stopImage);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        String string = event.getActionCommand();

        if (Strings.EXIT.value().equals(string)) {
            System.exit(0);
        }
    }

    private void postViewEvent() {
        if(this.getImage().equals(this.startImage)) {
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

