import java.lang.reflect.Array;

public abstract class Ship {
    private int length;
    private Coordinate startCoordinate;
    private Coordinate endCoordinate;
    public boolean isSunk;


    /* to check after hit if ship is destroyed and change status */
    public void check_is_sunk(String name){
        /* if all coordinates of ship are hit, set is_destroyed to True */
    }

    private Coordinate[] createCoordinateList(Coordinate startCoordinate, Coordinate endCoordinate, int length){
        Coordinate[] coordinateList = new Coordinate[length];
        String validlines = "ABCDEFGHIJ";

        /* check if boat is placed horizontal and create list of coordinates */
        if (startCoordinate.getX()  == endCoordinate.getX()){
            int pos_lst = 0;
            int index = validlines.indexOf(startCoordinate.getX());
            for (int i = startCoordinate.getY(); i <= endCoordinate.getY(); i++) {
                coordinateList[pos_lst] = new Coordinate(index, i);
                pos_lst += 1;
            }
        }

        /* creat list of coordinates with vertical placed boat */
        else{
            int pos_lst = 0;
            int index_start = validlines.indexOf(startCoordinate.getX());
            for (int i = index_start; i <= index_start+length; i++){
                coordinateList[pos_lst] = new Coordinate(i, startCoordinate.getY());
                pos_lst += 1;
            }
        }

        return coordinateList;
    }
}
