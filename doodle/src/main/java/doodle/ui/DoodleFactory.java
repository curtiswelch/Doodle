package doodle.ui;

import doodle.shapes.DoodleRectangularShape;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;

public enum DoodleFactory {
    INSTANCE;

    private DoodleType type = DoodleType.BOX;

    public void switchType(DoodleType type) {
        this.type = type;
    }

    public Doodle create() {
        Doodle inst = null;

        switch (this.type) {
            case BOX:
                inst = new DoodleRectangularShape(new Rectangle());
                break;
            case ELLIPSE:
                inst = new DoodleRectangularShape(new Ellipse2D.Double());
                break;
            case ROUNDED_BOX:
                inst = new DoodleRectangularShape(new RoundRectangle2D.Double(0, 0, 0, 0, 20, 20));
        }

        return inst;
    }

    public enum DoodleType {
        BOX,
        ROUNDED_BOX,
        ELLIPSE
    }
}
