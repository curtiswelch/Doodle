package doodle.tray;

import java.awt.CheckboxMenuItem;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import doodle.DoodleColor;
import doodle.window.Doodle;

public class DoodlePopupMenu extends PopupMenu implements ItemListener {
    private static final long serialVersionUID = 1;

    private Doodle doodle;
    private MenuItem stop;

    public DoodlePopupMenu(Doodle doodle) {
        this.doodle = doodle;
        this.stop = new MenuItem("Stop Doodle!");
        this.add(this.stop);
        this.addSeparator();

        for(DoodleColor color : DoodleColor.values()) {
            CheckboxMenuItem item = new CheckboxMenuItem(color.getLabel());
            item.addItemListener(this);

            if (color.ordinal() == 0) {
                item.setState(true);
            }

            this.add(item);
        }
    }

    @Override
	public void itemStateChanged(ItemEvent event) {
        DoodleColor doodleColor = DoodleColor.getByLabel((String)event.getItem());

        if (doodleColor != null) {
        	for(int i = 0; i < this.getItemCount(); i++) {
                MenuItem menuItem = this.getItem(i);

                if(menuItem instanceof CheckboxMenuItem) {
                	CheckboxMenuItem item = (CheckboxMenuItem) menuItem;
	                if (item.equals(event.getSource())) {
	                    item.setState(true);
	                } else {
	                    item.setState(false);
	                }
                }
            }

            this.doodle.setDoodleColor(doodleColor);
        }
    }

    public void addStopActionListener(ActionListener listener) {
        this.stop.addActionListener(listener);
    }
}

