public class Coordinate {
    private int x;
    private int y;
    // Constructor
    public Coordinate(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    /* I think this class doesn't need setter methods,
    you only need to set the ship once (when you create them) */
}
