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
            /* Checks if the last shot was a hit. If so it puts an 'X' in the target grid, else an "o".
            * The input parameter registers a new shot and returns its coordinate. */
            Boolean wasAHit = theBoard.checkIfShotWasAHit(humanPlayer.shoot(theBoard.getHumanTargetGrid()), theBoard.getHumanTargetGrid(), theBoard.getComputerOceanGrid());
            if(wasAHit){
                /* Checks if a ship is sunk, if so it overwrites the 'X' symbols in
                 * target grid with capital letters and changes Ship.isSunk to true. */
                theBoard.upDateTargetGrid(theBoard.getComputerOceanGrid(), theBoard.getHumanTargetGrid());}
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
        }
    }

    /* This method prints who the winner is and prints target grids of the human and the computer,
    * so the player can see, where the remaining ships are.*/
    private void endGame(Player winner){

    }
}
