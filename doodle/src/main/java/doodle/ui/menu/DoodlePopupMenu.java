package doodle.ui.menu;

import doodle.DoodleController;
import doodle.color.DoodleColor;
import doodle.color.DoodleColorRegistry;
import doodle.event.*;
import doodle.ui.text.Strings;
import doodle.ui.window.DoodleView;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Optional;
import java.util.function.Predicate;

public class DoodlePopupMenu extends PopupMenu implements ItemListener {
    private static final long serialVersionUID = 1;

    private DoodleView view;

    public DoodlePopupMenu(DoodleView view) {
        this.view = view;

        MenuItem stop = new MenuItem(Strings.STOP_DOODLE.value());
        stop.addActionListener(e -> EventBus.post(new HideViewRequested()));

        this.add(stop);
        this.addSeparator();

        addColors();

        EventBus.subscribe(this);
    }

    @Subscribe
    public void onMenuRequested(MenuRequested menuRequested) {
        this.show(this.view, menuRequested.x(), menuRequested.y());
    }

    @Subscribe
    public void onDoodleColorChanged(ColorChanged colorChanged) {
        resetCheckboxStates(item -> item.getLabel().equals(colorChanged.newColor().getLabel()));
    }

    @Override
    public void itemStateChanged(ItemEvent event) {
        Optional<DoodleColor> doodleColor = DoodleColorRegistry.INSTANCE.getByLabel((String) event.getItem());

        doodleColor.ifPresent(color -> {
            colorChanged((MenuItem) event.getSource());

            DoodleColorRegistry.INSTANCE.currentColor(color);
        });
    }

    private void addColors() {
        DoodleColorRegistry.INSTANCE.allColors().forEach(color -> {
            CheckboxMenuItem item = new CheckboxMenuItem(color.getLabel());
            item.addItemListener(this);

            if (2 == this.getItemCount()) {
                item.setState(true);
            }

            this.add(item);
        });
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

}

