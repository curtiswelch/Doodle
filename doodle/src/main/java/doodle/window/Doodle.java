package doodle.window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;

import doodle.DoodleBox;
import doodle.DoodleColor;
import doodle.tray.DoodlePopupMenu;

public class Doodle extends JDialog {
    private static final long serialVersionUID = 1;

    private List<DoodleBox> doodles = new ArrayList<DoodleBox>();
    private DoodleColor doodleColor = DoodleColor.BLUE;
    private DoodlePopupMenu menu;

    public Doodle() {
        Dimension screen = this.getToolkit().getScreenSize();
        Insets insets = this.getToolkit().getScreenInsets(this.getGraphicsConfiguration());

        this.setSize(screen.width - insets.left - insets.right, screen.height - insets.top - insets.bottom);

        this.setUndecorated(true);
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(0, 0, 0, 1));
        this.setLocationRelativeTo(null);
        this.setAlwaysOnTop(true);

        this.menu = new DoodlePopupMenu(this);
        this.add(this.menu);

        DoodleKeyListener keyListener = new DoodleKeyListener(this);
        this.addKeyListener(keyListener);

        DoodleMouseListener mouseListener = new DoodleMouseListener(this);
        this.addMouseListener(mouseListener);
        this.addMouseMotionListener(mouseListener);
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);

        Graphics2D g = (Graphics2D)graphics;

        for (DoodleBox doodleBox : this.doodles) {
            doodleBox.draw(g);
        }

        g.dispose();
    }

    public void setStopActionMenuListener(ActionListener al) {
        this.menu.addStopActionListener(al);
    }

    public void showMenu(int x, int y) {
        this.menu.show(this, x, y);
    }

    public void setDoodleColor(DoodleColor color) {
        this.doodleColor = color;
    }

    public void clearDoodles() {
        this.doodles = new ArrayList<DoodleBox>();
        this.repaint();
    }

    public void addDoodle(DoodleBox doodleBox) {
        doodleBox.setColor(new Color(this.doodleColor.getColor().getRGB()));
        this.doodles.add(doodleBox);
        this.repaint();
    }

    public void removeDoodleAt(int x, int y) {
        DoodleBox remove = null;

        for (DoodleBox doodleBox : this.doodles) {
            if (!doodleBox.toRectangle().contains(x, y)) continue;
            remove = doodleBox;
        }

        if (remove != null) {
            this.doodles.remove(remove);
            this.repaint();
        }
    }

    public void removeDoodle(DoodleBox doodle) {
        this.doodles.remove(doodle);
        this.repaint();
    }

    public void undo() {
        if (this.doodles.size() > 0) {
            this.doodles.remove(this.doodles.size() - 1);
            this.repaint();
        }
    }
}

