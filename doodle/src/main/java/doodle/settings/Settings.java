package doodle.settings;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Settings {
    private static final Log log = LogFactory.getLog(Settings.class);

    private static Settings instance;

    static {
        Settings.instance = new Settings();
    }

    public static Settings instance() {
        return instance;
    }

    private List<Setting> settings;

    private Settings() {
        this.settings = new ArrayList<>();
        this.load();
    }

    public List<Setting> settings() {
        return Collections.unmodifiableList(this.settings);
    }

    private void load() {
        try {
            InputStream properties = null;

            String userHome = System.getProperty("user.home");

            log.info("User Home: " + userHome);
            if (userHome != null) {
                File userSettings = new File(userHome + File.separatorChar + "doodle.properties");

                if (userSettings.exists()) {
                    log.info("Using settings from user");
                    properties = new FileInputStream(userSettings);
                }
            }

            if (properties == null) {
                log.info("Using default settings");
                properties = ClassLoader.getSystemResourceAsStream("doodle.properties");
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(properties));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] property = line.split("=");

                if (property.length == 2) {
                    this.settings.add(new Setting(property[0].trim(), property[1].trim()));
                }
            }
        } catch (IOException e) {
        }
    }

}
