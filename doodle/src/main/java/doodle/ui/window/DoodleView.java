package doodle.ui.window;

import doodle.DoodleController;
import doodle.event.DoodlesChanged;
import doodle.event.EventBus;
import doodle.event.Subscribe;
import doodle.ui.Doodle;
import doodle.ui.DoodleCollection;
import doodle.ui.menu.DoodlePopupMenu;
import doodle.ui.mouse.Mouse;
import doodle.ui.text.Strings;

import javax.swing.*;
import java.awt.*;

public class DoodleView extends JDialog {
    private static final long serialVersionUID = 1;

    public DoodleView(DoodleController doodle) throws Exception {
        setupUI();

        Dimension screen = this.getToolkit().getScreenSize();
        Insets insets = this.getToolkit().getScreenInsets(this.getGraphicsConfiguration());

        this.setSize(screen.width - insets.left - insets.right, screen.height - insets.top - insets.bottom);

        this.setUndecorated(true);
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(0, 0, 0, 1));
        this.setLocationRelativeTo(null);
        this.setAlwaysOnTop(true);

        this.add(new DoodlePopupMenu(this));

        this.addKeyListener(new DoodleKeyListener(doodle));

        Mouse mouse = new Mouse();

        this.addMouseListener(mouse);
        this.addMouseMotionListener(mouse);

        EventBus.subscribe(this);
    }

    private void setupUI() throws Exception {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();

        if (!gd.isWindowTranslucencySupported(GraphicsDevice.WindowTranslucency.TRANSLUCENT)) {
            throw new IllegalStateException(Strings.TRANSLUCENT_WINDOW_ERROR.value());
        }
    }

    @Subscribe
    private void onDoodlesChanged(DoodlesChanged event) {
        this.repaint();
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);

        Graphics2D g = (Graphics2D) graphics;

        for (Doodle doodleBox : DoodleCollection.doodles()) {
            doodleBox.draw(g);
        }

        g.dispose();
    }

}