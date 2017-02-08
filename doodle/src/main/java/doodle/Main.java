package doodle;

import doodle.color.DoodleColor;
import doodle.color.DoodleColorRegistry;
import doodle.event.EventBus;
import doodle.event.HideViewRequested;
import doodle.ui.DoodleCollection;
import doodle.ui.DoodleFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class Main {
    private static final Log log = LogFactory.getLog(Main.class);

    public static void main(String[] args) {
        try {
            DoodleController doodle = new DoodleController();

            addClearDoodles(doodle);
            addUndoDoodle(doodle);
            addHideDoodle(doodle);
            addShapes(doodle);
            addColors(doodle);

        } catch (Exception e) {
            log.error(e);
            JOptionPane.showMessageDialog(null, "Something bad happened :(");
        }
    }

    private static void addClearDoodles(DoodleController doodle) {
        doodle.addKeyHandler(KeyEvent.VK_C, event -> {
            if (event.isControlDown()) {
                DoodleCollection.INSTANCE.clearDoodles();
            }
        });
    }

    private static void addUndoDoodle(DoodleController doodle) {
        doodle.addKeyHandler(KeyEvent.VK_Z, event -> {
            if (event.isControlDown()) {
                DoodleCollection.INSTANCE.undo();
            }
        });
    }

    private static void addHideDoodle(DoodleController doodle) {
        doodle.addKeyHandler(KeyEvent.VK_ESCAPE, event -> EventBus.post(new HideViewRequested()));
    }

    private static void addShapes(DoodleController doodle) {
        doodle.addKeyHandler(KeyEvent.VK_1, event -> DoodleFactory.INSTANCE.switchType(DoodleFactory.DoodleType.BOX));
        doodle.addKeyHandler(KeyEvent.VK_2, event -> DoodleFactory.INSTANCE.switchType(DoodleFactory.DoodleType.ROUNDED_BOX));
        doodle.addKeyHandler(KeyEvent.VK_3, event -> DoodleFactory.INSTANCE.switchType(DoodleFactory.DoodleType.ELLIPSE));
    }

    private static void addColors(DoodleController doodle) {
        for (DoodleColor color : DoodleColorRegistry.allColors()) {
            doodle.addKeyHandler(color.getKeyCode(), event -> DoodleColorRegistry.currentColor(color));
        }
    }
}

