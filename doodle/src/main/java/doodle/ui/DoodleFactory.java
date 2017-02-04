package doodle.ui;

import doodle.shapes.DoodleRectangularShape;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
import java.util.function.Supplier;

public enum DoodleFactory {
    INSTANCE;

    private DoodleType type = DoodleType.BOX;

    public void switchType(DoodleType type) {
        this.type = type;
    }

    public Doodle create() {
        return this.type.create();
    }

    public enum DoodleType {
        BOX(() -> new DoodleRectangularShape(new Rectangle())),
        ROUNDED_BOX(() -> new DoodleRectangularShape(new Ellipse2D.Double())),
        ELLIPSE(() -> new DoodleRectangularShape(new RoundRectangle2D.Double(0, 0, 0, 0, 20, 20)));

        private Supplier<Doodle> supplier;

        DoodleType(Supplier<Doodle> supplier) {
            this.supplier = supplier;
        }

        public Doodle create() {
            return this.supplier.get();
        }
    }
}
