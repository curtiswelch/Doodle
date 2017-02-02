package doodle.ui.tray;

import doodle.DoodleController;
import doodle.ui.text.Strings;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DoodleTray extends TrayIcon implements ActionListener {

    public DoodleTray(final DoodleController doodle, final Image startImage) {
        super(startImage, Strings.START_DOODLE.value());

        MenuItem settings = new MenuItem(Strings.SETTINGS.value());
        settings.addActionListener(this);

        MenuItem exit = new MenuItem(Strings.EXIT.value());
        exit.addActionListener(this);

        PopupMenu menu = new PopupMenu();
        menu.add(settings);
        menu.addSeparator();
        menu.add(exit);

        this.setPopupMenu(menu);

        this.addMouseListener(new DoodleTrayMouseListener(doodle));
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        String string = event.getActionCommand();

        if (Strings.EXIT.value().equals(string)) {
            System.exit(0);
        }

//        if(Strings.SETTINGS.equals(string)) { }
    }
}

