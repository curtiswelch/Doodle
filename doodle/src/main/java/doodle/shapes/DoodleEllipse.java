package doodle.shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;

import doodle.Doodle;

public class DoodleEllipse extends Doodle {
	private static final int MIN_SIZE = 5;

	private Color color;

	private int x1;
	private int y1;
	private int x2;
	private int y2;

	private Ellipse2D.Double ellipse;

	public DoodleEllipse() {
		this.ellipse = new Ellipse2D.Double();
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

		this.ellipse.setFrame(
				this.x1 < this.x2 ? this.x1 : this.x2,
				this.y1 < this.y2 ? this.y1 : this.y2,
				Math.abs(this.x2 - this.x1),
				Math.abs(this.y2 - this.y1));
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(new Color(this.color.getRGB() + 838860800, true));
		g.fill(this.ellipse);

		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g.setColor(this.color);
		g.setStroke(new BasicStroke(2.5f));
		g.draw(this.ellipse);

		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
	}

	@Override
	public void setColor(Color color) {
		this.color = color;
	}

	@Override
	public boolean hitTest(int x, int y) {
		return this.ellipse.contains(x, y);
	}

	@Override
	public boolean isMinimumSize() {
		return Math.abs(this.x1 - this.x2) > MIN_SIZE && Math.abs(this.y1 - this.y2) > MIN_SIZE;
	}

}
