public class Battleship_Game {
    private  Board theBoard;
    private Player humanPlayer;
    private Player computerPlayer;
    private Boolean humanHitAllEnemyShips;
    private Boolean computerHitAllEnemyShips;
    public Battleship_Game() {
        theBoard = new Board();
        humanPlayer = new Player(true);
        computerPlayer = new Player(false);
    }
    public void setUpGame(){
        /* When the game starts, the program outputs the empty
        ocean grid and target grids.*/
        theBoard.printBoard();
        humanPlayer.setShips(theBoard.getHumanOceanGrid());
        theBoard.printBoard();
        computerPlayer.setShips(theBoard.getComputerOceanGrid());
    }

    public void playGame(){
        while(true){    // This loop stops when a break statement is reached
            humanPlayer.shoot(theBoard.getHumanTargetGrid());    // Registers a new shot in the human target grid
            theBoard.upDateTargetGrid();    // Checks if a ship is sunk, if so it overwrites the 'X' symbols in target grid with capital letters and changes Ship.isSunk to true
            theBoard.printBoard();
            humanHitAllEnemyShips = theBoard.hitAllEnemyShips(theBoard.getComputerOceanGrid());   // Checks if the human player hit all enemy ships.
            if(humanHitAllEnemyShips){
                endGame(humanPlayer);
                break;
            }
            System.out.println("\nNow it's the computers turn.");
            computerPlayer.shoot(theBoard.getComputerTargetGrid()); // Registers a new shot in the computer target grid
            // We need a method that checks if a shot results in a ship being sunk
            theBoard.printBoard();
            computerHitAllEnemyShips = theBoard.hitAllEnemyShips(theBoard.getHumanOceanGrid()); // Checks if the computer player hit all enemy ships.
            if(computerHitAllEnemyShips){
                endGame(computerPlayer);
                break;
            }
            break;  // Currently this is an endless loop. This break point must be removed as soon as the game works.
        }
    }

    /* This method prints who the winner is and prints target grids of the human and the computer,
    * so the player can see, where the remaining ships are.*/
    private void endGame(Player winner){

    }
}
