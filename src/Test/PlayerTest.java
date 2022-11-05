import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void setShips() {
        Player computerPlayer = new Player(false);
        Grid computerOceanGrid = new Grid();
        computerPlayer.setShips(computerOceanGrid);
        System.out.println("===== OCEAN  GRID =====");
        System.out.println("  A B C D E F G H I J  ");
        System.out.println(" +-+-+-+-+-+-+-+-+-+-+ ");
        for(int i = 0; i < 10; i++){
            System.out.print(i);
            System.out.print('|');
            for(int j = 0; j < 10; j++){
                System.out.print(computerOceanGrid.getCoordinateValue(i, j));
                System.out.print("|");
            }
            System.out.println(i);
        }
        System.out.println(" +-+-+-+-+-+-+-+-+-+-+ ");
        System.out.println("  A B C D E F G H I J  ");
        System.out.println("=======================");
    }
}