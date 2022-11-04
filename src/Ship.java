import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Ship {
    protected String type;
    protected int length;
    protected boolean isSunk;
    protected Coordinate startCoordinate;
    protected Coordinate endCoordinate;
    protected List<Coordinate> coordinateList;


    /* create list with all positions of ship in it */
    protected List<Coordinate> createCoordinateList(Coordinate startCoordinate, Coordinate endCoordinate, int length){
        coordinateList = new ArrayList<Coordinate>();

        /* check if boat is placed vertical and create list of coordinates */
        if (startCoordinate.getX()  == endCoordinate.getX()){
            for (int i = startCoordinate.getY(); i <= endCoordinate.getY(); i++) {
                coordinateList.add(new Coordinate(startCoordinate.getX(), i));
            }
        }

        /* creat list of coordinates with horizontal placed boat */
        else{
            for (int i = startCoordinate.getX(); i < startCoordinate.getX()+length; i++){
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

    public List<Coordinate> getCoordinateList() {
        return coordinateList;
    }
}
