package doodle.ui.tray;

import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import doodle.DoodleController;
import doodle.ui.text.Strings;

public class DoodleTray extends TrayIcon implements ActionListener {

    public DoodleTray(final DoodleController doodle, final Image startImage) {
        super(startImage, Strings.getText(Strings.START_DOODLE_KEY));

        MenuItem settings = new MenuItem(Strings.getText(Strings.SETTINGS_KEY));
        settings.addActionListener(this);

        MenuItem exit = new MenuItem(Strings.getText(Strings.EXIT_KEY));
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

        if (Strings.getText(Strings.EXIT_KEY).equals(string)) {
            System.exit(0);
        }

//        if(Strings.SETTINGS.equals(string)) { }
    }
}

