public class Carrier extends Ship {
    boolean isSunk;


    public Carrier(int start_x, int start_y, int end_x, int end_y){
        int length = 6;
        Coordinate startCoordinate = new Coordinate(start_x, start_y);
        Coordinate endCoordinate = new Coordinate(end_x, end_y);



        isSunk = false;


    }
}
