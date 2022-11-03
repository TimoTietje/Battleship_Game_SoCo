import java.util.List;

public class Battleship extends Ship{
    public Battleship(int start_x, int start_y, int end_x, int end_y){
        int length = 4;
        boolean isSunk = false;
        Coordinate startCoordinate = new Coordinate(start_x, start_y);
        Coordinate endCoordinate = new Coordinate(end_x, end_y);
        List<Coordinate> coordinateList = createCoordinateList(startCoordinate, endCoordinate, length);
    }
}
