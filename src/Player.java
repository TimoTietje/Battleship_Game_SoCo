import java.util.Scanner;

public class Player {
    private boolean isHuman;
    public Player(boolean isHuman) {
        this.isHuman = isHuman;
    }
    public void setShips(){
        if(isHuman){
            humanSetShips();
        }else{
            computerSetShips();
        }
    }

    private void computerSetShips() {
    }

    private void humanSetShips() {
        /* Human player must place 1x Carrier (length: 6), 2x Battleship (length: 4,
         3x Submarine (length: 3), 4x Patrol boat (length: 2).
         The game then asks the human player where they want to place each
         boat in their fleet, one by one. The user inputs the starting
         block and the ending block as capitalized characters and numbers,
         separated by a comma e.g. A1,B3 */
        Scanner sc = new Scanner(System.in);
        String input;
        boolean inputIsValid = false;
        while(!inputIsValid){
            System.out.println("Give the start and end coordinate of the position " +
                    "of your Carrier (length: 6) e.g. A1,A6");
            input = sc.next();

            /* CONTINUE HERE BY CHECKING WHETHER THE INPUT IS VALID */
        }
    }
}
