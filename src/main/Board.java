import java.util.ArrayList;
import java.util.List;

public class Board {
    private Grid humanOceanGrid;
    private Grid humanTargetGrid;
    private Grid computerOceanGrid;
    private Grid computerTargetGrid;

    public Board() {
        humanOceanGrid = new Grid();
        humanTargetGrid = new Grid();
        computerOceanGrid = new Grid();
        computerTargetGrid = new Grid();
    }

    /* This method must compare the target grid to the enemies ocean grid
    * to give the correct information about which ships were hit/sunk. */
    public void printBoard(){
        System.out.println("===== TARGET GRID =====");
        System.out.println("  A B C D E F G H I J  ");
        System.out.println(" +-+-+-+-+-+-+-+-+-+-+ ");
        for(int i = 0; i < 10; i++){
            System.out.print(i);
            System.out.print('|');
            for(int j = 0; j < 10; j++){
                if(humanTargetGrid.getCoordinateValue(i,j) == ' '){ // Case 1: Field has not been targeted yet
                    System.out.print(' ');
                } else if (computerOceanGrid.getCoordinateValue(i,j) == ' ') {  // Case 2: Field has been targeted but there is no ship.
                    System.out.print('o');
                }else{  // Case 3: Field has been targeted and a ship was hit.
                    System.out.print(humanTargetGrid.getCoordinateValue(i, j));
                }
                System.out.print("|");
            }
            System.out.println(i);
        }
        System.out.println(" +-+-+-+-+-+-+-+-+-+-+ ");
        System.out.println("  A B C D E F G H I J  ");
        System.out.print("=======================");

        System.out.print("\n\n-----------------------\n\n");

        System.out.println("===== OCEAN  GRID =====");
        System.out.println("  A B C D E F G H I J  ");
        System.out.println(" +-+-+-+-+-+-+-+-+-+-+ ");
        for(int i = 0; i < 10; i++){
            System.out.print(i);
            System.out.print('|');
            for(int j = 0; j < 10; j++){
                System.out.print(humanOceanGrid.getCoordinateValue(i, j));
                System.out.print("|");
            }
            System.out.println(i);
        }
        System.out.println(" +-+-+-+-+-+-+-+-+-+-+ ");
        System.out.println("  A B C D E F G H I J  ");
        System.out.println("=======================");
    }

    public Grid getHumanOceanGrid() {
        return humanOceanGrid;
    }

    public Grid getComputerOceanGrid() {
        return computerOceanGrid;
    }

    public Grid getHumanTargetGrid() {
        return humanTargetGrid;
    }

    public Grid getComputerTargetGrid() {
        return computerTargetGrid;
    }

    /* Checks if the human player hit all enemy ships.
    * To achieve that the method compares the human target grid with the
    * computer ocean grid.*/
    public Boolean humanHitAllEnemyShips() {
        return false;
    }

    /* Checks if the computer player hit all enemy ships.
     * To achieve that the method compares the computer target grid with the
     * human ocean grid.*/
    public Boolean computerHitAllEnemyShips() {
        return false;
    }

    /* This method compares the human target grid with the computer ocean grid after every
    * human shot. If a shot makes a ship sink, the 'X' symbols in the human target grid are
    * overwritten with capital letters. */
    public void upDateTargetGrid(){
        ArrayList<Ship>[] computerShips = computerOceanGrid.getShipList();
        for(int i = 0; i < 4; i++){ // Iterate over ship types
            for(int j = 0; j < computerShips[i].size(); j++){   // Iterate over ships from current ship type
                Ship currentShip = computerShips[i].get(j);
                List<Coordinate> coordinatesCurrentShip = currentShip.getCoordinateList();
                int countHitCoordinates = 0;
                for(int k = 0; k < currentShip.getLength(); k++){   // Iterate over coordinates of current ship
                    Coordinate currentCoordinate = coordinatesCurrentShip.get(k);
                    if(humanTargetGrid.getCoordinateValue(currentCoordinate.getY(), currentCoordinate.getX()) == 'X'){
                        countHitCoordinates++;
                    }
                }
                /* If all positions of a ship are hit, overwrite the 'X' values with capital letters
                * in the human target grid. */
                if(currentShip.getLength() == countHitCoordinates){
                    for(int k = 0; k < currentShip.getLength(); k++){   // Iterate over coordinates of current ship
                        Coordinate currentCoordinate = coordinatesCurrentShip.get(k);
                        char c;
                        Ship.shipClass shipType = currentShip.getShipType();
                        switch(shipType){
                            case CARRIER:
                                humanTargetGrid.setCoordinateValue(currentCoordinate.getY(), currentCoordinate.getX(), 'C');
                                break;
                            case BATTLESHIP:
                                humanTargetGrid.setCoordinateValue(currentCoordinate.getY(), currentCoordinate.getX(), 'B');
                                break;
                            case SUBMARINE:
                                humanTargetGrid.setCoordinateValue(currentCoordinate.getY(), currentCoordinate.getX(), 'S');
                                break;
                            case PATROL_BOAT:
                                humanTargetGrid.setCoordinateValue(currentCoordinate.getY(), currentCoordinate.getX(), 'P');
                        }
                    }
                }
            }
        }
    }
}
