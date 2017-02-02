package doodle.ui.menu;

import doodle.DoodleController;
import doodle.color.DoodleColor;
import doodle.color.DoodleColorRegistry;
import doodle.ui.text.Strings;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Optional;
import java.util.function.Predicate;

public class DoodlePopupMenu extends PopupMenu implements ItemListener {
    private static final long serialVersionUID = 1;

    private DoodleController doodle;

    public DoodlePopupMenu(DoodleController doodle) {
        this.doodle = doodle;

        MenuItem stop = new MenuItem(Strings.STOP_DOODLE.value());
        stop.addActionListener(e -> doodle.hideView());

        this.add(stop);
        this.addSeparator();

        boolean firstItem = true;

        for (DoodleColor color : DoodleColorRegistry.allColors()) {
            CheckboxMenuItem item = new CheckboxMenuItem(color.getLabel());
            item.addItemListener(this);

            if (firstItem) {
                item.setState(true);
                firstItem = false;
            }

            this.add(item);
        }
    }

    @Override
    public void itemStateChanged(ItemEvent event) {
        Optional<DoodleColor> doodleColor = DoodleColorRegistry.getByLabel((String) event.getItem());

        if (doodleColor.isPresent()) {
            colorChanged((MenuItem) event.getSource());

            this.doodle.setDoodleColor(doodleColor.get());
        }
    }

    private void resetCheckboxStates(Predicate<CheckboxMenuItem> test) {
        for (int i = 2; i < this.getItemCount(); i++) {
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

