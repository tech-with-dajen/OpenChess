package main.java.openchess;

public class Move {
    public final int startX;
    public final int startY;
    public final int endX;
    public final int endY;

    public Move(int startX, int startY, int endX, int endY) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
    }
}
