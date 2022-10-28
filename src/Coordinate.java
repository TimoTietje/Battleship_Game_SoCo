public class Coordinate {
    private int X;
    private int Y;
    // Constructor
    public Coordinate(int x, int y){
        X = x;
        Y = y;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }
    /* I think this class doesn't need setter methods,
    you only need to set the ship once (when you create them) */
}
