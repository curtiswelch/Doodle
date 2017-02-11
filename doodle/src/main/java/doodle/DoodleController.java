package doodle;

import doodle.event.EventBus;
import doodle.event.HideViewRequested;
import doodle.event.ShowViewRequested;
import doodle.event.Subscribe;
import doodle.ui.DoodleCollection;
import doodle.ui.tray.DoodleTray;
import doodle.ui.window.DoodleView;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class DoodleController {
    private DoodleView view;

    private List<KeyInputAction> actions;

    DoodleController() throws Exception {
        BufferedImage startImage = ImageIO.read(ClassLoader.getSystemResourceAsStream("doodle/images/doodle.png"));
        BufferedImage stopImage = ImageIO.read(ClassLoader.getSystemResourceAsStream("doodle/images/doodle_stop.png"));

        this.actions = new ArrayList<>();

        this.view = new DoodleView(this);

        SystemTray.getSystemTray().add(new DoodleTray(startImage, stopImage));

        EventBus.subscribe(this);
    }

    void addKeyHandler(int keyCode, Consumer<KeyEvent> handler) {
        this.actions.add(new KeyInputAction(keyCode, handler));
    }

    public void handleKeyInput(KeyEvent event) {
        this.actions.stream().
                filter(a -> a.handlesKey(event.getKeyCode())).
                forEach(a -> a.performAction(event));
    }

    @Subscribe
    private void onShowViewRequested(ShowViewRequested showViewRequested) {
        this.view.setVisible(true);
    }

    @Subscribe
    private void onHideViewRequested(HideViewRequested hideViewRequested) {
        DoodleCollection.clearDoodles();

        SwingUtilities.invokeLater(() -> this.view.setVisible(false));
    }

}
