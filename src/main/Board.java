import java.util.ArrayList;
import java.util.List;

public class Board {
    /* The humanOceanGrid stores the locations of the humans ships. */
    private Grid humanOceanGrid;
    /* The humanTargetGrid stores which cells the human has shot at with an 'X'.
    * In case a ship has been completely destroyed, the X symbols are overwritten
    * with capital letters in the Board.upDateTargetGrid method. */
    private Grid humanTargetGrid;
    /* The computerOceanGrid stores the locations of the computers ships.*/
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

    /* Checks if a player hit all enemy ships.
    * To achieve that the method iterates through the enemies ship list which is stored in
    * his ocean grid and checks if the variable Ship.isSunk is true for all ships.*/
    public Boolean hitAllEnemyShips(Grid enemyOceanGrid) {
        ArrayList<Ship>[] computerShips = enemyOceanGrid.getShipList();
        int shipsDestroyedCounter = 0;
        for(int i = 0; i < computerShips.length; i++){  // Iterate over ship types
            for(int j = 0; j < computerShips[i].size(); j++){   // Iterate over ships from current ship type
                if(computerShips[i].get(j).isSunk()){
                    shipsDestroyedCounter++;
                }
            }
        }
        if(shipsDestroyedCounter == 10){
            return true;
        }
        return false;
    }

    /* This method compares the human target grid with the computer ocean grid if the last shot was
    * a hit. If a hit makes a ship sink, the 'X' symbols in the human target grid are
    * overwritten with the respective capital letters. And the Ship.isSunk variable is set true in
    * the enemies ocean grid. */
    public void upDateTargetGrid(Grid enemyOceanGrid, Grid ownerTargetGrid){
        ArrayList<Ship>[] computerShips = enemyOceanGrid.getShipList();
        for(int i = 0; i < 4; i++){ // Iterate over ship types
            for(int j = 0; j < computerShips[i].size(); j++){   // Iterate over ships from current ship type
                Ship currentShip = computerShips[i].get(j);
                List<Coordinate> coordinatesCurrentShip = currentShip.getCoordinateList();
                int countHitCoordinates = 0;
                for(int k = 0; k < currentShip.getLength(); k++){   // Iterate over coordinates of current ship
                    Coordinate currentCoordinate = coordinatesCurrentShip.get(k);
                    if(ownerTargetGrid.getCoordinateValue(currentCoordinate.getY(), currentCoordinate.getX()) == 'X'){
                        countHitCoordinates++;
                    }
                }
                /* If all positions of a ship are hit, overwrite the 'X' values with capital letters
                * in the human target grid. */
                if(currentShip.getLength() == countHitCoordinates){
                    currentShip.setSunk(true);
                    for(int k = 0; k < currentShip.getLength(); k++){   // Iterate over coordinates of current ship
                        Coordinate currentCoordinate = coordinatesCurrentShip.get(k);
                        Ship.shipClass shipType = currentShip.getShipType();
                        switch(shipType){
                            case CARRIER:
                                ownerTargetGrid.setCoordinateValue(currentCoordinate.getY(), currentCoordinate.getX(), 'C');
                                break;
                            case BATTLESHIP:
                                ownerTargetGrid.setCoordinateValue(currentCoordinate.getY(), currentCoordinate.getX(), 'B');
                                break;
                            case SUBMARINE:
                                ownerTargetGrid.setCoordinateValue(currentCoordinate.getY(), currentCoordinate.getX(), 'S');
                                break;
                            case PATROL_BOAT:
                                ownerTargetGrid.setCoordinateValue(currentCoordinate.getY(), currentCoordinate.getX(), 'P');
                        }
                    }
                }
            }
        }
    }

    /* Checks if the last shot was a hit. If so it puts an 'X' in the target grid, else an 'o'.
     * The input parameter registers a new shot and returns its coordinate. */
    public Boolean checkIfShotWasAHit(Coordinate lastShotCoordinate, Grid ownerTargetGrid, Grid enemyOceanGrid){
        if(enemyOceanGrid.getCoordinateValue(lastShotCoordinate.getY(), lastShotCoordinate.getX()) != ' '){
            ownerTargetGrid.setCoordinateValue(lastShotCoordinate.getY(), lastShotCoordinate.getX(), 'X');
            return true;
        }else{
            ownerTargetGrid.setCoordinateValue(lastShotCoordinate.getY(), lastShotCoordinate.getX(), 'o');
            return false;
        }
    }
}
