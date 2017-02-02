package doodle.ui;

import java.awt.*;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Doodle {

    private static final AtomicInteger idGen;

    static {
        idGen = new AtomicInteger(0);
    }

    private final int id;

    protected Doodle() {
        this.id = idGen.incrementAndGet();
    }

    public int getId() {
        return this.id;
    }

    public abstract void setStartingPoint(int x, int y);

    public abstract void setEndingPoint(int x, int y);

    public abstract void draw(Graphics2D g);

    public abstract void setColor(Color color);

    public abstract boolean hitTest(int x, int y);

    public abstract boolean isMinimumSize();
}