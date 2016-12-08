package doodle;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Doodle {

	private static final AtomicInteger id;

	static {
		id = new AtomicInteger(0);
	}

	public int getId() {
		return id.incrementAndGet();
	};

	public abstract void draw(Graphics2D g);

	public abstract void setColor(Color color);

	public abstract boolean hitTest(int x, int y);

}