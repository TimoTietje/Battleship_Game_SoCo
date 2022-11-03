import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Ship {
    private String type;
    private int length;
    private boolean isSunk;
    private Coordinate startCoordinate;
    private Coordinate endCoordinate;
    private List<Coordinate> coordinateList;


    /* create list with all positions of ship in it */
    protected List<Coordinate> createCoordinateList(Coordinate startCoordinate, Coordinate endCoordinate, int length){
        coordinateList = new ArrayList<Coordinate>();
        String validlines = "ABCDEFGHIJ";

        /* check if boat is placed horizontal and create list of coordinates */
        if (startCoordinate.getX()  == endCoordinate.getX()){
            int index = validlines.indexOf(startCoordinate.getX());
            for (int i = startCoordinate.getY(); i <= endCoordinate.getY(); i++) {
                coordinateList.add(new Coordinate(index, i));
            }
        }

        /* creat list of coordinates with vertical placed boat */
        else{
            int index_start = validlines.indexOf(startCoordinate.getX());
            for (int i = index_start; i <= index_start+length; i++){
                coordinateList.add(new Coordinate(i, startCoordinate.getY()));
            }
        }

        return coordinateList;
    }

    /* check if shot is a hit */
    public boolean isShipHit(Ship ship, Coordinate shot){
        if (ship.coordinateList.contains(shot)){
            ship.coordinateList.remove(shot);
            return true;
            /* !!!if true is returned, check if ship has been sunk!!! */
        }
        return false;
    }

    /* to check after hit if ship is destroyed and change status */
    public boolean ship_status(Ship ship){
        if (ship.coordinateList.size() == 0){
            ship.isSunk = true;
        }
        return ship.isSunk;
    }
}
