package doodle;

import doodle.event.*;
import doodle.ui.DoodleCollection;
import doodle.ui.text.Strings;
import doodle.ui.tray.DoodleTray;
import doodle.ui.window.DoodleView;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class DoodleController {
    private DoodleView view;
    private DoodleTray tray;

    private List<KeyInputAction> actions;

    public DoodleController() throws Exception {
        BufferedImage startImage = ImageIO.read(ClassLoader.getSystemResourceAsStream("doodle/images/doodle.png"));
        BufferedImage stopImage = ImageIO.read(ClassLoader.getSystemResourceAsStream("doodle/images/doodle_stop.png"));

        this.actions = new ArrayList<>();

        this.view = new DoodleView(this);
        this.tray = new DoodleTray(this, startImage, stopImage);

        SystemTray.getSystemTray().add(this.tray);

        EventBus.subscribe(this);
    }

    public void addKeyHandler(int keyCode, Consumer<KeyEvent> handler) {
        this.actions.add(new KeyInputAction(keyCode, handler));
    }

    public void handleKeyInput(KeyEvent event) {
        this.actions.stream().
                filter(a -> a.handlesKey(event.getKeyCode())).
                forEach(a -> a.performAction(event));
    }

    @Subscribe
    public void onShowViewRequested(ShowViewRequested showViewRequested) {
        this.view.setVisible(true);
    }

    @Subscribe
    public void onHideViewRequested(HideViewRequested hideViewRequested) {
        DoodleCollection.INSTANCE.clearDoodles();

        SwingUtilities.invokeLater(new DoodleViewHider(this.view));
    }

    class DoodleViewHider implements Runnable {
        private DoodleView view;

        DoodleViewHider(DoodleView view) {
            this.view = view;
        }

        @Override
        public void run() {
            this.view.setVisible(false);
        }
    }
}
