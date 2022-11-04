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
            computerSetShips(aGrid);
        }
    }
    //pre: line,col,directions, length are of type integer
    //post: ship position is valid or not
    private boolean is_Valid(int line, int col){
        //test if position (line, col) is on grid 0 <= col,line < 10
        return (col <= 10 && col >= 0) && (line <= 10 && line >= 0);
    }

    private void computerSetShips(Grid aGrid) {
        // generate random input
        Random rand = new Random(); //we need rand to create random numbers
        int grid_length = 10;
        int[] amount_of_ships = {1, 2, 3, 4}; //the amount of each ship
        int[] ship_lengths = {6,4,3,2}; //to get ship length of amount_of_ships[index]
        int index = 0; //index operate on amount_of_ships and ship_lengths, because
        int col1, col2, line1,line2, direction;
        //each position matches in amount of ship and length
        while(index<4) {
            //generate random col, line & direction
            col1 = rand.nextInt(grid_length);
            line1 = rand.nextInt(grid_length);
            direction = rand.nextInt(4); //4 directions possible 0 == North & clockwise
            //north
            if(direction == 0){
                line2 = line1 - ship_lengths[index];
                col2=col1;
            }
            //east
            else if(direction == 1){
                line2 = line1;
                col2=col1 + ship_lengths[index];
            }
            //south
            else if(direction == 2){
                line2 = line1 + ship_lengths[index];
                col2=col1;
            }
            //west
            else {
                line2 = line1;
                col2=col1 - ship_lengths[index];
            }
            //validate  input
            if(is_Valid(line1, col1) && is_Valid(line2,col2)){
                //reduce the amount of ships that has to be set
                amount_of_ships[index]--;
                //TO DO
                //setShiponGrid(line1, col2, line2, col2);
            }
            if(amount_of_ships[index] == 0){
                index++;
            }
        }
    }


    //search for similarities between computerSetShips and humanSetShips to merge
    private boolean valid_length(String start, String end, int length) {
        boolean valid = true;

        String validlines = "ABCDEFGHIJ";
        /* separate start/end in column & line*/
        char col_start = start.charAt(0);
        int line_start = Character.getNumericValue(start.charAt(1));

        char col_end = end.charAt(0);
        int line_end = Character.getNumericValue(end.charAt(1));

        /* column A-J as number*/
        int col_start_nr = validlines.indexOf(col_start);
        int col_end_nr = validlines.indexOf(col_end);

        /*CHECK RANGE*/
        /*check if lines are in range of 0-9*/
        if (Math.max(0, line_start) != Math.min(line_start,9) || Math.max(0,line_end) != Math.min(line_end,9)) {
            valid = false;}
        /*check if columns are in A-J*/
        if (validlines.indexOf(col_start) == -1 || validlines.indexOf(col_end) == -1) {valid = false;}

        /*CHECK IF COORDINATES == LENGTH OF SPECIFIC SHIP (input parameter "length")*/
        /*when ship is placed horizontally; line_start == line_end*/
        if (line_start == line_end) {
            int input_length = Math.abs(col_start_nr-col_end_nr) + 1;
            if (input_length != length) {valid = false;}
        }
        /*when ship is placed vertically; col_start = col_end*/
        if (col_start == col_end) {
            int input_length = Math.abs(line_start-line_end) + 1;
            if (input_length != length) {valid = false;}
        }
        /*when ship is neither horizontal nor vertical*/
        if (col_start != col_end && line_start != line_end) {valid = false;}

        return valid;
    }
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
        List<Integer> ship_len = Arrays.asList(6, 4, 3, 2);
        int idx = 0;
        int i;
        /* This variable stores the entered ships and passes them to the grid to save them.
         * Each ship type is saved in a seperate nested ArrayList. */
        ArrayList<Ship>[] shipList = new ArrayList[]{new ArrayList<Ship>(), new ArrayList<Ship>(), new ArrayList<Ship>(), new ArrayList<Ship>()};

        System.out.println("Deploy your ships");
        for (i = 1; i <= amount.get(idx); ) {
            System.out.println("Enter the start and end coordinates for your " +i+ ". " + ships.get(idx) +
                    " as uppercase letters separated by a coma (e.g. A1,A9)");
            String coordinates = input.next();

            /*test if input has total length 5 (otherwise no valid input)*/
            if (coordinates.length() != 5) {
                System.out.println("Input is not valid. Try again");
                continue;
            }

            /*separate input in start & end coordinates*/
            String start = coordinates.substring(0,2);
            String end = coordinates.substring(3,5);

            /* check if input is valid*/
            boolean valid_input_length = valid_length(start,end,ship_len.get(idx));
            if (!valid_input_length) {System.out.println("Invalid Input. Try again");continue;}
            /* CHECK IF SHIP CAN BE PLACED IN GRID -> bool free_place*/

            /* if (valid_input_length && free_place)
                -> initialize ships with given coordinates
                -> safe coordinates in grid */

            /*initialize ships with given coordinates*/
            /*create Instance of Class Carrier*/
            int start_xpos = validlines.indexOf(start.charAt(0));
            int start_ypos = Character.getNumericValue(start.charAt(1));
            int end_xpos = validlines.indexOf(end.charAt(0));
            int end_ypos = Character.getNumericValue(end.charAt(1));

            if (idx == 0) {
                shipList[idx].add(new Ship(start_xpos, start_ypos, end_xpos, end_ypos));
            }
            if (idx == 1) {
                shipList[idx].add(new Ship(start_xpos, start_ypos, end_xpos, end_ypos));
            }
            if (idx == 2) {
                shipList[idx].add(new Ship(start_xpos, start_ypos, end_xpos, end_ypos));
            }
            if (idx == 3) {
                shipList[idx].add(new Ship(start_xpos, start_ypos, end_xpos, end_ypos));
            }

            /* SAFE COORDINATES IN GRID*/

            // What is the following code doing? -> should end the for-loop correctly
            /*when required amount of specific boat has not yet been reached*/
            if (shipList[idx].size() < amount.get(idx)) {i++;}
            /*continue with new boat type*/
            else if (idx < 3) {i = 1; idx++;}   // idx must stop at 3
            /*all boats are placed*/
            else {break;}
        }

        aGrid.setShipList(shipList);    // Save the ships in the ocean grid of the player in the Battleship_Game class
    }
}
