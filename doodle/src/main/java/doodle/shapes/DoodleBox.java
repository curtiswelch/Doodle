package doodle.shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import doodle.Doodle;

public class DoodleBox extends Doodle {
	private static final int MIN_SIZE = 5;

    private Color color;

    private int x1;
    private int y1;
    private int x2;
    private int y2;

    private Rectangle rectangle = new Rectangle();

    @Override
	public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return this.color;
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
	}

	public Rectangle toRectangle() {
        int x = 0;
        int y = 0;

        int width = this.x1 - this.x2;
        int height = this.y1 - this.y2;

        if (this.x1 > this.x2) {
            x = this.x2;
        } else {
            x = this.x1;
            width *= -1;
        }

        if (this.y1 > this.y2) {
            y = this.y2;
        } else {
            y = this.y1;
            height *= -1;
        }

        this.rectangle.setBounds(x, y, width, height);

        return this.rectangle;
    }

    @Override
	public void draw(Graphics2D g) {
        Rectangle r = this.toRectangle();

        g.setColor(new Color(this.color.getRGB() + 838860800, true));
        g.fill(r);

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g.setColor(this.color);
        g.setStroke(new BasicStroke(2.5f));
        g.draw(r);

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
    }

    @Override
    public boolean hitTest(int x, int y) {
    	return this.toRectangle().contains(x, y);
    }

	@Override
	public boolean isMinimumSize() {
		return Math.abs(this.x1 - this.x2) > MIN_SIZE && Math.abs(this.y1 - this.y2) > MIN_SIZE;
	}

}

