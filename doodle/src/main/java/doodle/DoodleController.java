package doodle;

import doodle.ui.DoodleCollection;
import doodle.ui.text.Strings;
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
    private DoodleTray tray;

    private BufferedImage startImage;
    private BufferedImage stopImage;

    private List<KeyInputAction> actions;

    public DoodleController() throws Exception {
        this.startImage = ImageIO.read(ClassLoader.getSystemResourceAsStream("doodle/images/doodle.png"));
        this.stopImage = ImageIO.read(ClassLoader.getSystemResourceAsStream("doodle/images/doodle_stop.png"));

        this.actions = new ArrayList<>();

        this.view = new DoodleView(this);
        this.tray = new DoodleTray(this, this.startImage);

        SystemTray.getSystemTray().add(this.tray);
    }

    public void addKeyHandler(int keyCode, Consumer<KeyEvent> handler) {
        this.actions.add(new KeyInputAction(keyCode, handler));
    }

    public void handleKeyInput(KeyEvent event) {
        this.actions.stream().
                filter(a -> a.handlesKey(event.getKeyCode())).
                forEach(a -> a.performAction(event));
    }

    public void clearDoodles() {
        DoodleCollection.INSTANCE.clearDoodles();
        this.view.repaint();
    }

    public void undo() {
        DoodleCollection.INSTANCE.undo();
        this.view.repaint();
    }

    public void toggleView() {
        if (this.view.isVisible()) {
            this.hideView();
        } else {
            this.showView();
        }
    }

    private void showView() {
        this.view.setVisible(true);
        this.tray.setImage(stopImage);
        this.tray.setToolTip(Strings.STOP_DOODLE.value());
    }

    public void hideView() {
        DoodleCollection.INSTANCE.clearDoodles();

        SwingUtilities.invokeLater(new DoodleViewHider(this.view, this.tray));
    }

    class DoodleViewHider implements Runnable {
        private DoodleView view;
        private DoodleTray tray;

        DoodleViewHider(DoodleView view, DoodleTray tray) {
            this.view = view;
            this.tray = tray;
        }

        @Override
        public void run() {
            this.view.setVisible(false);
            this.tray.setImage(startImage);
            this.tray.setToolTip(Strings.START_DOODLE.value());
        }
    }
}
