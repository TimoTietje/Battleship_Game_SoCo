import java.util.*;

public class Player {
    private boolean isHuman;

    public Player(boolean isHuman) {
        this.isHuman = isHuman;
    }

    public void setShips(Grid aGrid) {
        if (isHuman) {
            humanSetShips(aGrid);
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
    private void humanSetShips(Grid aGrid) {
        /* Human player must place 1x Carrier (length: 6), 2x Battleship (length: 4,
         3x Submarine (length: 3), 4x Patrol boat (length: 2).
         The game then asks the human player where they want to place each
         boat in their fleet, one by one. The user inputs the starting
         block and the ending block as capitalized characters and numbers,
         separated by a comma e.g. A1,B3 */

        Scanner input = new Scanner(System.in);

        String validlines = "ABCDEFGHIJ";

        List<String> ships = Arrays.asList("Carrier (Length: 6)", "Battleship (Length: 4)",
                "Submarine (Length: 3)", "Patrol Boat (Length: 2)");
        List<Integer> amount = Arrays.asList(1, 2, 3, 4);
        int idx = 0;
        int i;
        /* This variable stores the entered ships and passes them to the grid to save them.
        * Each ship type is saved in a seperate nested ArrayList. */
        ArrayList<Ship>[] shipList = new ArrayList[]{new ArrayList<Ship>(), new ArrayList<Ship>(), new ArrayList<Ship>(), new ArrayList<Ship>()};

        System.out.println("Deploy your ships");
        for (i = 1; i <= amount.get(idx); ) {
            System.out.println("Enter Coordinates for your " + ships.get(idx));
            System.out.println("Start Coordinate");
            String start = input.next();
            System.out.println("End Coordinate");
            String end = input.next();

            if (start.length() == 2 && end.length() == 2) {
                char col1 = start.charAt(0); char col2 = end.charAt(0);
                int line1 = Character.getNumericValue(start.charAt(1));
                int line2 = Character.getNumericValue(end.charAt(1));

                if (validlines.indexOf(col1) != -1 && validlines.indexOf(col2) != -1 &&
                        line1 >= 0 && line1 <= 9 && line2 >= 0 && line2 <= 9
                        /*how to connect with humanTargetGrid?
                        (grid[col1][line1] == " ") && (grid[col2][line2] == " ")*/) {

                    /*CHECK IF COORDINATES = LENGTH OF SPECIFIC SHIP*/
                    /*When ship is placed vertically*/
                    if (col1 == col2) {
                        int length = Math.abs(line1-line2) + 1;
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
                    else if (line1 == line2) {
                        int len1 = validlines.indexOf(col1);
                        int len2 = validlines.indexOf(col2);
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
                    else {System.out.println("You can set your ships only horizontally or vertically. Try again");continue;}

                    /* SAFE COORDINATES IN GRID*/


                    /*initialize ships with given coordinates*/
                    /*create Instance of Class Carrier*/
                    int start_xpos = validlines.indexOf(start.charAt(0));
                    int end_xpos = validlines.indexOf(end.charAt(0));
                    if (idx == 0) {
                        shipList[idx].add(new Carrier(start_xpos, line1, end_xpos, line2));
                    }
                    if (idx == 1) {
                        shipList[idx].add(new Battleship(start_xpos, line1, end_xpos, line2));
                    }
                    if (idx == 2) {
                        shipList[idx].add(new Submarine(start_xpos, line1, end_xpos, line2));
                    }
                    if (idx == 3) {
                        shipList[idx].add(new PatrolBoat(start_xpos, line1, end_xpos, line2));
                    }


                    // What is the following code doing?
                    if (i < amount.get(idx)) {i++;}
                    else if (idx < 3) {i = 1; idx++;}   // idx must stop at 3
                    else {break;}
                }
            }
            else {
                System.out.println("Input is invalid. Try again");
                continue;
            }
        }
        aGrid.setShipList(shipList);    // Save the ships in the ocean grid of the player in the Battleship_Game class
    }
}
