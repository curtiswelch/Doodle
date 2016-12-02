package doodle;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.SystemTray;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import doodle.tray.DoodleTray;
import doodle.window.Doodle;

public class Main {
	private static final Log log = LogFactory.getLog(Main.class);

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice gd = ge.getDefaultScreenDevice();

            if (!gd.isWindowTranslucencySupported(GraphicsDevice.WindowTranslucency.TRANSLUCENT)) {
                JOptionPane.showMessageDialog(null, "Transparent Windows aren't supported :(");
                System.exit(0);
            }

            BufferedImage start = ImageIO.read(ClassLoader.getSystemResourceAsStream("doodle/images/doodle.png"));
            BufferedImage stop = ImageIO.read(ClassLoader.getSystemResourceAsStream("doodle/images/doodle_stop.png"));

            Doodle doodle = new Doodle();

            SystemTray tray = SystemTray.getSystemTray();
            tray.add(new DoodleTray(doodle, start, stop));
        } catch (Exception e) {
        	log.error(e);
            JOptionPane.showMessageDialog(null, "Something bad happend :(");
        }
    }
}

