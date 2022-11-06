import java.util.ArrayList;
import java.util.List;

public class Grid {
    private char coordinateSystem[][];
    private ArrayList<Ship>[] shipList; // Stores the ships e.g. [{Carrier}, {Battleship1, Battleship2}, ...]

    public Grid() {
        coordinateSystem = new char[][] {{' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                                        {' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                                        {' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                                        {' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                                        {' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                                        {' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                                        {' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                                        {' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                                        {' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                                        {' ',' ',' ',' ',' ',' ',' ',' ',' ',' '}};
    }

    public char getCoordinateValue(int y, int x) {
        return coordinateSystem[y][x];
    }

    public void setCoordinateValue(int y, int x, char c){
        coordinateSystem[y][x] = c;
    }

    public void setShipList(ArrayList<Ship>[] shipList) {
        this.shipList = shipList;
    }

    public ArrayList<Ship>[] getShipList() {
        return shipList;
    }

    // Save the ships from the shipList in the grid aka overwrite the grid
    public void setShip(Coordinate start, Coordinate end, int shipType){ //shipType: 1 = C, 2= B, 3= S ...
        char[] shipTypeName = new char[]{'C', 'B', 'S', 'P'}; // Trage diese Symbole ins Grid ein C-Carrier, B-Battleship, S-Submarine, P-Patrol Boat
        for(int x = start.getX(); x <= end.getX(); x++){    //
            for(int y = start.getY(); y <= end.getY(); y++) {    //
                coordinateSystem[y][x] = shipTypeName[shipType];
            }
        }
    }

    public boolean isCollision(int x, int y){;
        return coordinateSystem[y][x] != ' ';
    }

    /* This updateGrid method is used after a player entered a valid shot.
    * All targeted cells get marked with a 'X'.*/
    public void updateGrid(int x, int y){
        coordinateSystem[y][x] = 'X';
    }

    public char[][] getCoordinateSystem() {
        return coordinateSystem;
    }
}
