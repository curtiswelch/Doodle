package doodle.settings;

public class Setting {
    private String name;
    private String value;

    public Setting(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String name() {
        return this.name;
    }

    public String value() {
        return this.value;
    }
}