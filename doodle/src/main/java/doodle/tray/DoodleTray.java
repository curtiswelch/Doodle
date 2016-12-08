package doodle.tray;

import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.TrayIcon;

import doodle.DoodleController;
import doodle.Strings;

public class DoodleTray extends TrayIcon {

    public DoodleTray(final DoodleController doodle, final Image startImage) {
        super(startImage, Strings.START_DOODLE);

        MenuItem exit = new MenuItem(Strings.EXIT);
        exit.addActionListener(new DoodleTrayActionListener());

        PopupMenu menu = new PopupMenu();
        menu.add(exit);

        this.setPopupMenu(menu);

        this.addMouseListener(new DoodleTrayMouseListener(doodle));
    }
}

