public class Battleship_Game {
    public Battleship_Game() {
        Board theBoard = new Board();
        Player humanPlayer = new Player();
        Player computerPlayer = new Player();

        setUpGame(theBoard);
    }
    private void setUpGame(Board aBoard){
        /* When the game starts, the program outputs the empty
        ocean grid and target grids.*/
        aBoard.printBoard();
    }
}
