public class Carrier {
    private int length;
    private Coordinate startCoordinate;
    private Coordinate endCoordinate;

    public Carrier(int l, int start_x, int start_y, int end_x, int end_y){
        length = l;
        Coordinate startCoordinate = new Coordinate(start_x, start_y);
        Coordinate endCoordinate = new Coordinate(end_x, end_y);
    }
}
