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
        this.reload();
    }

    public List<Setting> settings() {
        return Collections.unmodifiableList(this.settings);
    }

    public void reload() {
        this.settings = new ArrayList<>();
        try {
            InputStream properties = propertiesInputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(properties));

            processSettings(reader);
        } catch (IOException e) {
        }
    }

    private void processSettings(BufferedReader reader) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            line = line.trim();

            processLine(line);
        }
    }

    private void processLine(String line) {
        if (!line.startsWith("#")) {
            processProperty(line);
        }
    }

    private void processProperty(String line) {
        String[] property = line.split("=");

        if (property.length == 2) {
            this.settings.add(new Setting(property[0].trim(), property[1].trim()));
        }
    }

    private InputStream propertiesInputStream() throws IOException {
        InputStream properties = userSettings();

        if (properties == null) {
            log.info("Using default settings");
            properties = ClassLoader.getSystemResourceAsStream("doodle.properties");
        }

        return properties;
    }

    private InputStream userSettings() throws IOException {
        InputStream properties = null;

        String userHome = System.getProperty("user.home");

        log.info("User Home: " + userHome);
        File userSettings = new File(userHome + File.separatorChar + "doodle.properties");

        if (userSettings.exists()) {
            log.info("Using settings from user");
            properties = new FileInputStream(userSettings);
        }

        return properties;
    }

}
