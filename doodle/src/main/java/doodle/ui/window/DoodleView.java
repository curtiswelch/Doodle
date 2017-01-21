package doodle.ui.window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import javax.swing.JDialog;

import doodle.Doodle;
import doodle.DoodleColor;
import doodle.DoodleController;
import doodle.ui.menu.DoodlePopupMenu;

public class DoodleView extends JDialog {
    private static final long serialVersionUID = 1;

    private List<Doodle> doodles = new ArrayList<>();
    private DoodleColor doodleColor = DoodleColor.BLUE;
    private DoodlePopupMenu menu;

    public DoodleView(DoodleController doodle) {
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

        DoodleKeyListener keyListener = new DoodleKeyListener(doodle);
        this.addKeyListener(keyListener);

        DoodleMouseListener mouseListener = new DoodleMouseListener(this);
        this.addMouseListener(mouseListener);
        this.addMouseMotionListener(mouseListener);
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);

        Graphics2D g = (Graphics2D)graphics;

        for (Doodle doodleBox : this.doodles) {
            doodleBox.draw(g);
        }

        g.dispose();
    }

    void showMenu(int x, int y) {
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

    void addDoodle(Doodle doodle) {
        doodle.setColor(new Color(this.doodleColor.getColor().getRGB()));
        this.doodles.add(doodle);
        this.repaint();
    }

    void removeDoodleAt(int x, int y) {
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