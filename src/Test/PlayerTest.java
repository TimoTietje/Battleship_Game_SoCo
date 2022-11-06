import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
/*
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
*/
    @Test
    void computerShootTest() {
        /* All shots should be within the field. The shots don't need to be next to each other
        * because at any point during this the first for loop, the target grid is empty.*/
        System.out.println("Test computerShoot method on empty Grid: ");
        Player computerPLayer = new Player(false);
        Grid targetGrid = new Grid();
        Coordinate lastShot;
        for(int i = 0; i < 6; i++){    // Let the computer shoot 10 times
            lastShot = computerPLayer.shoot(targetGrid);
            System.out.println("X: " + lastShot.getX() + ", Y: " + lastShot.getY());
        }

        /* All shots should be next to the coordinate (6,5)*/
        System.out.println("\nTest computer Shoot method on non empty Grid");
        targetGrid.setCoordinateValue(5, 5, 'X');
        for(int i = 0; i < 4; i++) {
            lastShot = computerPLayer.shoot(targetGrid);
            System.out.println("X: " + lastShot.getX() + ", Y: " + lastShot.getY());
        }
    }
}