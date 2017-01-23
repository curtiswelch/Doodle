

import javax.swing.JOptionPane;

import doodle.*;
import doodle.color.DoodleColor;
import doodle.color.DoodleColorRegistry;
import doodle.ui.DoodleFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.awt.event.KeyEvent;

public class Main {
    private static final Log log = LogFactory.getLog(Main.class);

    public static void main(String[] args) {
        try {
            DoodleController doodle = new DoodleController();

            doodle.addKeyHandler(KeyEvent.VK_C, event -> {
                if (event.isControlDown()) {
                    doodle.clearDoodles();
                }
            });

            doodle.addKeyHandler(KeyEvent.VK_Z, event -> {
                if (event.isControlDown()) {
                    doodle.undo();
                }
            });

            doodle.addKeyHandler(KeyEvent.VK_ESCAPE, event -> doodle.hideView());

            doodle.addKeyHandler(KeyEvent.VK_1, event -> DoodleFactory.instance().switchType(DoodleFactory.DoodleType.BOX));
            doodle.addKeyHandler(KeyEvent.VK_2, event -> DoodleFactory.instance().switchType(DoodleFactory.DoodleType.ROUNDED_BOX));
            doodle.addKeyHandler(KeyEvent.VK_3, event -> DoodleFactory.instance().switchType(DoodleFactory.DoodleType.ELLIPSE));

            for(DoodleColor color : DoodleColorRegistry.allColors()) {
                doodle.addKeyHandler(color.getKeyCode(), event -> doodle.setDoodleColor(color));
            }

        } catch (Exception e) {
            log.error(e);
            JOptionPane.showMessageDialog(null, "Something bad happened :(");
        }
    }
}

