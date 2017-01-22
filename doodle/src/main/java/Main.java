

import javax.swing.JOptionPane;

import doodle.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.awt.event.KeyEvent;
import java.security.Key;
import java.util.Optional;

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

            doodle.addKeyHandler(KeyEvent.VK_ESCAPE, event -> {
                doodle.hideView();
            });

            doodle.addKeyHandler(KeyEvent.VK_1, event -> {
                DoodleFactory.instance().switchType(DoodleFactory.DoodleType.BOX);
            });

            doodle.addKeyHandler(KeyEvent.VK_2, event -> {
                DoodleFactory.instance().switchType(DoodleFactory.DoodleType.ELLIPSE);
            });

            doodle.addKeyHandler(KeyEvent.VK_3, event -> {
                DoodleFactory.instance().switchType(DoodleFactory.DoodleType.ROUNDED_BOX);
            });

            doodle.addKeyHandler(KeyEvent.VK_B, event -> {
                doodle.setDoodleColor(DoodleColor.BLUE);
            });

            doodle.addKeyHandler(KeyEvent.VK_R, event -> {
                doodle.setDoodleColor(DoodleColor.RED);
            });

            doodle.addKeyHandler(KeyEvent.VK_G, event -> {
                doodle.setDoodleColor(DoodleColor.GREEN);
            });

            doodle.addKeyHandler(KeyEvent.VK_P, event -> {
                doodle.setDoodleColor(DoodleColor.PURPLE);
            });

            doodle.addKeyHandler(KeyEvent.VK_Y, event -> {
                doodle.setDoodleColor(DoodleColor.YELLOW);
            });

            doodle.addKeyHandler(KeyEvent.VK_O, event -> {
                doodle.setDoodleColor(DoodleColor.ORANGE);
            });

            doodle.addKeyHandler(KeyEvent.VK_W, event -> {
                doodle.setDoodleColor(DoodleColor.WHITE);
            });

            doodle.addKeyHandler(KeyEvent.VK_K, event -> {
                doodle.setDoodleColor(DoodleColor.BLACK);
            });

        } catch (Exception e) {
            log.error(e);
            JOptionPane.showMessageDialog(null, "Something bad happened :(");
        }
    }
}

