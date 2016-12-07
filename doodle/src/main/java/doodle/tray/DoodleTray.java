package doodle.tray;

import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import doodle.Doodle;
import doodle.Strings;

public class DoodleTray extends TrayIcon {

    public DoodleTray(final Doodle doodle, final Image startImage) {
        super(startImage);

        this.setToolTip(Strings.START_DOODLE);

        MenuItem exit = new MenuItem(Strings.EXIT);
        exit.addActionListener(new ActionListener() {
            @Override
			public void actionPerformed(ActionEvent event) {
                String string = event.getActionCommand();

                if(Strings.EXIT.equals(string)) {
                	System.exit(0);
                }
            }
        });

        PopupMenu menu = new PopupMenu();
        menu.add(exit);

        this.setPopupMenu(menu);

        this.addMouseListener(new DoodleTrayMouseListener(doodle));
    }
}

