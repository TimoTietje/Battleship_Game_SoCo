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

    public void printBoard(){
        System.out.println("===== TARGET GRID =====");
        System.out.println("  A B C D E F G H I J  ");
        System.out.println(" +-+-+-+-+-+-+-+-+-+-+ ");
        for(int i = 0; i < 10; i++){
            System.out.print(i);
            System.out.print('|');
            for(int j = 0; j < 10; j++){
                System.out.print(humanTargetGrid.getCoordinateValue(i, j));
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
}
