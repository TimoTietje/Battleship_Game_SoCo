import java.lang.reflect.Array;
import java.util.ArrayList;

public class Grid {
    private char coordinateSystem[][];
    private ArrayList<Ship>[] shipList;

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

    public char getCoordinateValue(int x, int y) {
        return coordinateSystem[x][y];
    }

    public void setShipList(ArrayList<Ship>[] shipList) {
        this.shipList = shipList;
        updateGrid(shipList);
    }

    // Save the ships from the shipList in the grid aka overwrite the grid
    private void updateGrid(ArrayList<Ship>[] shipList){
    }
}
