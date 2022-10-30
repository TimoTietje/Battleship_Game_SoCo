public class Battleship_Game {
    private  Board theBoard;
    private Player humanPlayer;
    private Player computerPlayer;
    public Battleship_Game() {
        theBoard = new Board();
        humanPlayer = new Player();
        computerPlayer = new Player();

        setUpGame();
    }
    private void setUpGame(){
        /* When the game starts, the program outputs the empty
        ocean grid and target grids.*/
        theBoard.printBoard();
    }
}
