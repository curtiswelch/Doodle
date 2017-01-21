

import javax.swing.JOptionPane;

import doodle.DoodleController;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Main {
    private static final Log log = LogFactory.getLog(Main.class);

    public static void main(String[] args) {
        try {
            new DoodleController();
        } catch (Exception e) {
            log.error(e);
            JOptionPane.showMessageDialog(null, "Something bad happened :(");
        }
    }
}

