import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ship {
    private shipClass shipType;
    private final int length;
    private boolean isSunk;
    private final Coordinate startCoordinate;
    private final Coordinate endCoordinate;
    private List<Coordinate> coordinateList;
    enum shipClass{
        CARRIER, BATTLESHIP, SUBMARINE, PATROL_BOAT
    }

    public Ship(int start_x, int start_y, int end_x, int end_y) {
        // Initialize length of ship
        if (start_x == end_x){
            this.length = end_y - start_y + 1;
        }else{
            length = end_x - start_x + 1;
        }
        // Initialize shipType
        switch (length) {
            case 6 -> shipType = shipClass.CARRIER;
            case 4 -> shipType = shipClass.BATTLESHIP;
            case 3 -> shipType = shipClass.SUBMARINE;
            case 2 -> shipType = shipClass.PATROL_BOAT;
        }
        this.startCoordinate = new Coordinate(start_x, start_y);
        this.endCoordinate = new Coordinate(end_x, end_y);
        coordinateList = createCoordinateList(startCoordinate, endCoordinate, length);
        isSunk = false;
    }



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

    public int getLength() {
        return length;
    }

    public shipClass getShipType() {
        return shipType;
    }

    public void setSunk(boolean sunk) {
        isSunk = sunk;
    }

    public boolean isSunk() {
        return isSunk;
    }

    public char getChar(){
        switch (shipType){
            case CARRIER:
                return 'C';
            case SUBMARINE:
                return 'S';
            case BATTLESHIP:
                return 'B';
        }
        return 'P';
    }
}
