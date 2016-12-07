package doodle.menu;

import java.awt.CheckboxMenuItem;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import doodle.Doodle;
import doodle.DoodleColor;
import doodle.Strings;

public class DoodlePopupMenu extends PopupMenu implements ItemListener {
    private static final long serialVersionUID = 1;

    private Doodle doodle;
    private MenuItem stop;

    public DoodlePopupMenu(Doodle doodle) {
        this.doodle = doodle;
        this.stop = new MenuItem(Strings.STOP_DOODLE);
        this.stop.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				doodle.hideView();
			}
        });
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
        	for(int i = 2; i < this.getItemCount(); i++) {
                MenuItem menuItem = this.getItem(i);

            	CheckboxMenuItem item = (CheckboxMenuItem) menuItem;
                if (item.equals(event.getSource())) {
                    item.setState(true);
                } else {
                    item.setState(false);
                }
            }

            this.doodle.setDoodleColor(doodleColor);
        }
    }
}

