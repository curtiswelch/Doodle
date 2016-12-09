package doodle.ui.tray;

import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import doodle.DoodleController;
import doodle.Strings;

public class DoodleTray extends TrayIcon implements ActionListener {

    public DoodleTray(final DoodleController doodle, final Image startImage) {
        super(startImage, Strings.START_DOODLE);

        MenuItem settings = new MenuItem(Strings.SETTINGS);
        settings.addActionListener(this);

        MenuItem exit = new MenuItem(Strings.EXIT);
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

        if(Strings.EXIT.equals(string)) {
        	System.exit(0);
        }

        if(Strings.SETTINGS.equals(string)) {

        }
    }
}
