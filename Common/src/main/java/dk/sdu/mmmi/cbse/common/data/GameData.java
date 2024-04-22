package dk.sdu.mmmi.cbse.common.data;

public class GameData {

    private int displayWidth  = 800 ;
    private int displayHeight = 800;
    private final GameKeys keys = new GameKeys();
    private long delta;
    private double deltaSeconds;
    private double time;

    public GameKeys getKeys() {
        return keys;
    }

    public void setDisplayWidth(int width) {
        this.displayWidth = width;
    }

    public int getDisplayWidth() {
        return displayWidth;
    }

    public void setDisplayHeight(int height) {
        this.displayHeight = height;
    }

    public int getDisplayHeight() {
        return displayHeight;
    }

    public long getDelta() {
        return delta;
    }
    public void setDelta(long delta) {
        this.delta = delta;
        this.deltaSeconds = delta * 0.001f;
        this.time += deltaSeconds;
    }

    public double getDeltaSeconds() {
        return deltaSeconds;
    }

    public double getTime() {
        return time;
    }

}
