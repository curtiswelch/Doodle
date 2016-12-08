package doodle.tray;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import doodle.Strings;

public class DoodleTrayActionListener implements ActionListener {

    @Override
	public void actionPerformed(ActionEvent event) {
        String string = event.getActionCommand();

        if(Strings.EXIT.equals(string)) {
        	System.exit(0);
        }
    }

}
