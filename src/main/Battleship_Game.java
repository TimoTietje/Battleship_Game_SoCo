import java.util.Random;

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
        Random rand = new Random(); //we need rand to create random numbers
        boolean computerStarts = rand.nextBoolean();
        boolean wasAHit = false;
        if(computerStarts){ // I inserted this block, in case the computer takes the first turn
            System.out.println("\nNow it's the computers turn.");
            Coordinate lastShot = computerPlayer.shoot(theBoard.getComputerTargetGrid());
            wasAHit = theBoard.checkIfShotWasAHit(lastShot, theBoard.getComputerTargetGrid(), theBoard.getHumanOceanGrid()); // Registers a new shot in the computer target grid
            if(wasAHit){
                theBoard.upDateTargetGrid(theBoard.getHumanOceanGrid(), theBoard.getComputerTargetGrid());
                theBoard.reportHit(lastShot, theBoard.getHumanOceanGrid()); // Report hit to humanOceanGrid
                System.out.println("The computer hit one of your ships.");
            }else{
                System.out.println("The computer missed.");
            }
            theBoard.printBoard();
        }
        while(true){    // This loop stops when a break statement is reached
            /* Checks if the last shot was a hit. If so it puts an 'X' in the target grid, else an "o".
            * The input parameter registers a new shot and returns its coordinate. */
            // Humans turn
            wasAHit = theBoard.checkIfShotWasAHit(humanPlayer.shoot(theBoard.getHumanTargetGrid()), theBoard.getHumanTargetGrid(), theBoard.getComputerOceanGrid());
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
            // Computers turn
            System.out.println("\nNow it's the computers turn.");
            Coordinate lastShot = computerPlayer.shoot(theBoard.getComputerTargetGrid());
            wasAHit = theBoard.checkIfShotWasAHit(lastShot, theBoard.getComputerTargetGrid(), theBoard.getHumanOceanGrid()); // Registers a new shot in the computer target grid
            if(wasAHit){
                theBoard.upDateTargetGrid(theBoard.getHumanOceanGrid(), theBoard.getComputerTargetGrid());
                theBoard.reportHit(lastShot, theBoard.getHumanOceanGrid()); // Report hit to humanOceanGrid
                System.out.println("The computer hit one of your ships.");
            }else{
                System.out.println("The computer missed.");
            }
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
        if (winner == computerPlayer){
            System.out.println("\nYou lost. The computers remaining ships not get revealed.");
            theBoard.createEndBoard();
            theBoard.printBoard();
        }else{
            System.out.println("\n CONGRATULATIONS, YOU WON!");
        }
    }
}
