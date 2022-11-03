import java.lang.reflect.Array;

public class Grid {
    private char coordinateSystem[][];

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
}
