public class Carrier {
    private int length;
    private Coordinate startCoordinate;
    public Carrier(int l, int start_x, int start_y){
        length = l;
        Coordinate startCoordinate = new Coordinate(start_x, start_y);
    }
}
