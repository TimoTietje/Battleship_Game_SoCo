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
            /* We need a method that checks if a shot results in a ship being sunk (This method could
            overwrite the 'X' symbols in the target grids with capital letters, to tell the printBoard
            method, the hit of the ship is completed. */
            theBoard.printBoard();
            humanHitAllEnemyShips = theBoard.humanHitAllEnemyShips();   // Checks if the human player hit all enemy ships.
            if(humanHitAllEnemyShips){
                endGame(humanPlayer);
                break;
            }
            System.out.println("\nNow it's the computers turn.");
            computerPlayer.shoot(theBoard.getComputerTargetGrid()); // Registers a new shot in the computer target grid
            // We need a method that checks if a shot results in a ship being sunk
            theBoard.printBoard();
            computerHitAllEnemyShips = theBoard.computerHitAllEnemyShips(); // Checks if the computer player hit all enemy ships.
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
