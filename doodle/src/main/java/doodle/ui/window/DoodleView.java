package doodle.ui.window;

import doodle.DoodleController;
import doodle.color.DoodleColor;
import doodle.color.DoodleColorRegistry;
import doodle.ui.Doodle;
import doodle.ui.menu.DoodlePopupMenu;
import doodle.ui.mouse.Mouse;
import doodle.ui.text.Strings;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class DoodleView extends JDialog {
    private static final long serialVersionUID = 1;

    private List<Doodle> doodles = new ArrayList<>();
    private DoodleColor doodleColor = DoodleColorRegistry.defaultColor();
    private DoodlePopupMenu menu;

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

        this.menu = new DoodlePopupMenu(doodle);
        this.add(this.menu);

        this.addKeyListener(new DoodleKeyListener(doodle));

        Mouse mouse = new Mouse(this);

        this.addMouseListener(mouse);
        this.addMouseMotionListener(mouse);
    }

    private void setupUI() throws Exception {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();

        if (!gd.isWindowTranslucencySupported(GraphicsDevice.WindowTranslucency.TRANSLUCENT)) {
            throw new IllegalStateException(Strings.TRANSLUCENT_WINDOW_ERROR.value());
        }
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);

        Graphics2D g = (Graphics2D) graphics;

        for (Doodle doodleBox : this.doodles) {
            doodleBox.draw(g);
        }

        g.dispose();
    }

    public void showMenu(int x, int y) {
        this.menu.show(this, x, y);
    }

    public void setDoodleColor(DoodleColor color) {
        this.doodleColor = color;
        this.menu.colorChanged(color);
    }

    public void clearDoodles() {
        this.doodles = new ArrayList<>();
        this.repaint();
    }

    public void addDoodle(Doodle doodle) {
        doodle.setColor(new Color(this.doodleColor.getColor().getRGB()));
        this.doodles.add(doodle);
        this.repaint();
    }

    public void removeDoodle(Doodle doodle) {
        this.doodles.remove(doodle);
    }

    public void removeDoodleAt(int x, int y) {
        Optional<Doodle> remove = this.doodles.stream().
                filter(doodle -> doodle.hitTest(x, y)).
                max(new DoodleByIDComparator());

        remove.ifPresent(doodle -> {
            this.doodles.remove(doodle);
            this.repaint();
        });
    }

    public void undo() {
        if (!this.doodles.isEmpty()) {
            this.doodles.remove(this.doodles.size() - 1);
            this.repaint();
        }
    }

    class DoodleByIDComparator implements Comparator<Doodle> {
        @Override
        public int compare(Doodle d1, Doodle d2) {
            return d1.getId() - d2.getId();
        }
    }
}