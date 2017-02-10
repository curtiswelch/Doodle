package doodle.ui.tray;

import doodle.ui.text.Strings;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DoodleTrayMenu extends PopupMenu implements ActionListener {

    DoodleTrayMenu() {
        MenuItem settings = new MenuItem(Strings.SETTINGS.value());
        settings.addActionListener(this);

        MenuItem exit = new MenuItem(Strings.EXIT.value());
        exit.addActionListener(this);

        this.add(settings);
        this.addSeparator();
        this.add(exit);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        String string = event.getActionCommand();

        if (Strings.EXIT.value().equals(string)) {
            System.exit(0);
        }
    }
}
