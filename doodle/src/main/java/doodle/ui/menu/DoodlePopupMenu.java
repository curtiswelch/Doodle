package doodle.ui.menu;

import java.awt.CheckboxMenuItem;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Optional;
import java.util.function.Predicate;

import doodle.DoodleColor;
import doodle.DoodleController;
import doodle.Strings;

public class DoodlePopupMenu extends PopupMenu implements ItemListener {
    private static final long serialVersionUID = 1;

    private DoodleController doodle;

    public DoodlePopupMenu(DoodleController doodle) {
        this.doodle = doodle;

        MenuItem stop = new MenuItem(Strings.STOP_DOODLE);
        stop.addActionListener(e -> doodle.hideView());

        this.add(stop);
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
        Optional<DoodleColor> doodleColor = DoodleColor.getByLabel((String) event.getItem());

        if (doodleColor.isPresent()) {
        	colorChanged((MenuItem) event.getSource());

            this.doodle.setDoodleColor(doodleColor.get());
        }
    }

    private void resetCheckboxStates(Predicate<CheckboxMenuItem> test) {
    	for(int i = 2; i < this.getItemCount(); i++) {
    		CheckboxMenuItem item = (CheckboxMenuItem) this.getItem(i);

    		item.setState(test.test(item));
    	}
    }

    private void colorChanged(MenuItem source) {
    	resetCheckboxStates(item -> item.equals(source));
    }

    public void colorChanged(DoodleColor color) {
    	resetCheckboxStates(item -> item.getLabel().equals(color.getLabel()));
    }
}

