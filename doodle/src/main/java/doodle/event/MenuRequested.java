package doodle.event;

public class MenuRequested {
    private int x;
    private int y;

    public MenuRequested(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int x() {
        return this.x;
    }

    public int y() {
        return this.y;
    }
}
