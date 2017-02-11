package doodle.shapes;

import doodle.color.DoodleColorRegistry;
import doodle.ui.Doodle;

import java.awt.*;
import java.awt.geom.RectangularShape;

public class DoodleRectangularShape extends Doodle {
    private static final int MIN_SIZE = 5;

    private Color color;

    private int x1;
    private int y1;
    private int x2;
    private int y2;

    private RectangularShape shape;

    public DoodleRectangularShape(RectangularShape shape) {
        this.shape = shape;
    }

    @Override
    public void setStartingPoint(int x, int y) {
        this.x1 = x;
        this.y1 = y;
    }

    @Override
    public void setEndingPoint(int x, int y) {
        this.x2 = x;
        this.y2 = y;

        this.shape.setFrame(
                Math.min(this.x1, this.x2),
                Math.min(this.y1, this.y2),
                Math.abs(this.x2 - this.x1),
                Math.abs(this.y2 - this.y1)
        );
    }

    @Override
    public void draw(Graphics2D g) {
        int opacity = DoodleColorRegistry.opacity() << 24;
        g.setColor(new Color(this.color.getRGB() + opacity, true));
        g.fill(this.shape);

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g.setColor(this.color);
        g.setStroke(new BasicStroke(2.5f));
        g.draw(this.shape);

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public boolean hitTest(int x, int y) {
        return this.shape.contains(x, y);
    }

    @Override
    public boolean isMinimumSize() {
        return Math.abs(this.x1 - this.x2) > MIN_SIZE && Math.abs(this.y1 - this.y2) > MIN_SIZE;
    }
}
