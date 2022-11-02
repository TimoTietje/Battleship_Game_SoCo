import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

public class Player {
    private boolean isHuman;

    public Player(boolean isHuman) {
        this.isHuman = isHuman;
    }

    public void setShips() {
        if (isHuman) {
            humanSetShips();
        } else {
            computerSetShips();
        }
    }
    //pre: line,col,directions, length are of type integer
    //post: ship position is valid or not
    private boolean is_Valid(int line, int col, int direction, int length){
        //test if position (line, col) is on grid 0 <= col,line < 10
        if((col > 10 || col < 0) || (line > 10 || line < 0)){
            return false;
        }
        //north
        if(direction == 0){
            if(line - length < 0){
                return false;
            }
        }
        //east
        if(direction == 1){
            if(col + length >= 10){
                return false;
            }
        }
        //south
        if(direction == 2){
            if(line + length >= 10){
                return false;
            }
        }
        //west
        if(direction == 3){
            if(col - length < 0){
                return false;
            }
        }
        return true;
    }

    private void computerSetShips() {
        // generate random input
        Random rand = new Random(); //we need rand to create random numbers
        int grid_length = 10;
        int[] amount_of_ships = {1, 2, 3, 4}; //the amount of each ship
        int[] ship_lengths = {6,4,3,2}; //to get ship length of amount_of_ships[index]
        int index = 0; //index operate on amount_of_ships and ship_lengths, because
        //each position matches in amount of ship and length
        while(index<4) {
            //generate random col, line & direction
            int col = rand.nextInt(grid_length);
            int line = rand.nextInt(grid_length);
            int direction = rand.nextInt(4); //4 directions possible 0 == North & clockwise
            //validate  input
            if(is_Valid(line, col, direction, ship_lengths[index])){
                //reduce the amount of ships that has to be set
                amount_of_ships[index]--;
                //TO DO
                //setShiponGrid(line, col, direction, ship_lengths[index]);
            }
            if(amount_of_ships[index] == 0){
                index++;
            }
        }
    }

    //search for similarities between computerSetShips and humanSetShips to merge
    private void humanSetShips() {
        /* Human player must place 1x Carrier (length: 6), 2x Battleship (length: 4,
         3x Submarine (length: 3), 4x Patrol boat (length: 2).
         The game then asks the human player where they want to place each
         boat in their fleet, one by one. The user inputs the starting
         block and the ending block as capitalized characters and numbers,
         separated by a comma e.g. A1,B3 */

        /* CONTINUE HERE BY CHECKING WHETHER THE INPUT IS VALID */

        Scanner input = new Scanner(System.in);

        String validlines = "ABCDEFGHIJ";

        List<String> ships = Arrays.asList("Carrier (Length: 6)", "Battleship (Length: 4)",
                "Submarine (Length: 3)", "Patrol Boat (Length: 2)");
        List<Integer> amount = Arrays.asList(1, 2, 3, 4);
        int idx = 0;
        int i;

        System.out.println("Deploy your ships");
        for (i = 1; i <= amount.get(idx); ) {
            System.out.println("Enter Coordinates for your " + ships.get(idx));
            System.out.println("Start Coordinate");
            String start = input.next();
            System.out.println("End Coordinate");
            String end = input.next();

            if (start.length() == 2 && end.length() == 2) {
                char line1 = start.charAt(0); char line2 = end.charAt(0);
                int col1 = Character.getNumericValue(start.charAt(1));
                int col2 = Character.getNumericValue(end.charAt(1));

                if (validlines.indexOf(line1) != -1 && validlines.indexOf(line2) != -1 &&
                        col1 >= 0 && col1 <= 9 && col2 >= 0 && col2 <= 9
                        /*how to connect with humanTargetGrid?
                        (grid[line1][col1] == " ") && (grid[line2][col2] == " ")*/) {

                    /*CHECK IF COORDINATES = LENGTH OF SPECIFIC SHIP*/
                    /*When ship is placed vertically*/
                    if (line1 == line2) {
                        int length = Math.abs(col1-col2) + 1;
                        if (idx == 0 && length != 6) {
                            System.out.println("Length of your ship should be 6. Try Again");
                            continue;
                        }
                        if (idx == 1 && length != 4) {
                            System.out.println("Length of your ship should be 4. Try Again");
                            continue;
                        }
                        if (idx == 2 && length != 3) {
                            System.out.println("Length of your ship should be 3. Try Again");
                            continue;
                        }
                        if (idx == 3 && length != 2) {
                            System.out.println("Length of your ship should be 2. Try Again");
                            continue;
                        }
                    }
                    /*when ship is placed horizontally*/
                    if (col1 == col2) {
                        int len1 = validlines.indexOf(line1);
                        int len2 = validlines.indexOf(line2);
                        int length = Math.abs(len1 - len2) + 1;

                        if (idx == 0 && length != 6) {
                            System.out.println("Length of your ship should be 6. Try Again");
                            continue;
                        }
                        if (idx == 1 && length != 4) {
                            System.out.println("Length of your ship should be 4. Try Again");
                            continue;
                        }
                        if (idx == 2 && length != 3) {
                            System.out.println("Length of your ship should be 3. Try Again");
                            continue;
                        }
                        if (idx == 3 && length != 2) {
                            System.out.println("Length of your ship should be 2. Try Again");
                            continue;
                        }
                    }

                    /* SAFE COORDINATES IN GRID*/
                    /*test*/

                    if (i < amount.get(idx)) {i++;}
                    else if (idx < 4) {i = 1; idx++;}
                    else {break;}
                }
            }
            else {
                System.out.println("Input is invalid. Try again");
                continue;
            }
        }
    }
}
