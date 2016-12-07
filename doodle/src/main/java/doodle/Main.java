package doodle;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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

            new Doodle();
        } catch (Exception e) {
        	log.error(e);
            JOptionPane.showMessageDialog(null, "Something bad happend :(");
        }
    }
}

