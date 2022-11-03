import java.util.List;

public class Carrier extends Ship {


    public Carrier(int start_x, int start_y, int end_x, int end_y){
        int length = 6;
        boolean isSunk = false;
        Coordinate startCoordinate = new Coordinate(start_x, start_y);
        Coordinate endCoordinate = new Coordinate(end_x, end_y);
        List<Coordinate> coordinateList = createCoordinateList(startCoordinate, endCoordinate, length);
    }
}
