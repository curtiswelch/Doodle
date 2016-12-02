package doodle;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class DoodleBox {
    private Color color;

    private int x1;
    private int y1;
    private int x2;
    private int y2;

    private Rectangle rectangle;

    public void setColor(Color color) {
        this.color = color;
        this.rectangle = new Rectangle();
    }

    public Color getColor() {
        return this.color;
    }

    public void set(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
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

    public void draw(Graphics2D g) {
        Rectangle r = this.toRectangle();

        g.setColor(new Color(this.color.getRGB() + 838860800, true));
        g.fill(r);

        g.setColor(this.color);
        g.draw(r);

        r.setBounds(r.x - 1, r.y - 1, r.width + 2, r.height + 2);
        g.draw(r);
    }
}

