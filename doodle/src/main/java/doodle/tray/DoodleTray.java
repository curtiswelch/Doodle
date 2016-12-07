package doodle.tray;

import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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

        this.addMouseListener(new MouseListener() {
            @Override
			public void mouseClicked(MouseEvent event) {
                if (event.getButton() == MouseEvent.BUTTON1) {
                	doodle.toggleView();
                }
            }

            @Override
			public void mouseEntered(MouseEvent event) {
            }

            @Override
			public void mouseExited(MouseEvent event) {
            }

            @Override
			public void mousePressed(MouseEvent event) {
            }

            @Override
			public void mouseReleased(MouseEvent event) {
            }

        });
    }
}

