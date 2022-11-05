import java.lang.reflect.Array;
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
        updateGrid(shipList);
    }

    public ArrayList<Ship>[] getShipList() {
        return shipList;
    }

    // Save the ships from the shipList in the grid aka overwrite the grid
    private void updateGrid(ArrayList<Ship>[] shipList){
        char[] shipTypeName = new char[]{'C', 'B', 'S', 'P'}; // Trage diese Symbole ins Grid ein C-Carrier, B-Battleship, S-Submarine, P-Patrol Boat
        for(int shipType = 0; shipType < 4; shipType++){    // Alle Schiffe im Grid speichern
            for(int i = 0; i < shipList[shipType].size(); i++) {    // Alle Schiffe vom aktuellen shipType im Grid speichern
                List<Coordinate> coordinatesOfCurrentShip = shipList[shipType].get(i).getCoordinateList();
                for(int j = 0; j < coordinatesOfCurrentShip.size(); j++){   // Alle Koordinaten vom aktuellen Schiff im Grid speichern
                    coordinateSystem[coordinatesOfCurrentShip.get(j).getY()][coordinatesOfCurrentShip.get(j).getX()] = shipTypeName[shipType];
                }
            }
        }
    }

    /* This updateGrid method is used after a player entered a valid shot.
    * All targeted cells get marked with a 'X'.*/
    public void updateGrid(int x, int y){
        coordinateSystem[y][x] = 'X';
    }
}
