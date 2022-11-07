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
        return (0 <= col && col < 10 && 0 <= line && line < 10);
    }

    private void computerSetShips(Grid aGrid) {
        // generate random input
        Random rand = new Random(); //we need rand to create random numbers
        int[] amount_of_ships = {1, 2, 3, 4}; //the amount of each ship
        int[] ship_lengths = {6,4,3,2}; //to get ship length of amount_of_ships[index]
        int index = 0;
        int direction;
        boolean check;
        boolean freePlace = true;
        ArrayList<Ship>[] shipList = new ArrayList[]{new ArrayList<Ship>(), new ArrayList<Ship>(),
                new ArrayList<Ship>(), new ArrayList<Ship>()};

        while (true) { //loop ends with break statement
            Coordinate start = new Coordinate(rand.nextInt(10), rand.nextInt(10));
            Coordinate end;
            direction = rand.nextInt(2);
            if(direction == 0){
                end = new Coordinate(start.getX() + ship_lengths[index]-1, start.getY());
            }else{
                end = new Coordinate(start.getX(), start.getY() + ship_lengths[index]-1);
            }

            check = coordinateInBoundaries(start, end,ship_lengths[index]);

            /* CHECK IF IT'S FREE IN GRID*/
            if(check) {
                for (int xPos = start.getX(); xPos <= end.getX(); xPos++) {
                    for (int yPos = start.getY(); yPos <= end.getY(); yPos++) {
                        if (aGrid.getCoordinateValue(yPos, xPos) != ' ') { //check if collision in ocean grid of computer
                            freePlace = false;
                        }
                    }
                }
            }

            if (check && freePlace) {
                shipList[index].add(new Ship(start.getX(),start.getY(), end.getX(), end.getY()));
                aGrid.setShip(start,end,index);
                if (shipList[index].size() == amount_of_ships[index]) {
                    if (index != 3) {
                        index++;
                    }
                    else break;
                }
            }
            check = false;
            freePlace = true;
        }
        aGrid.setShipList(shipList);
    }


    //search for similarities between computerSetShips and humanSetShips to merge
    private boolean coordinateInBoundaries(Coordinate start, Coordinate end, int length) {
        boolean valid = true;

        /* separate start/end in column & line*/
        int col_start = start.getX();
        int line_start = start.getY();

        int col_end = end.getX();
        int line_end = end.getY();

        /*CHECK RANGE*/
        /*check if lines are in range of 0-9*/
        if (!is_Valid(col_start,line_start) || !is_Valid(col_end, line_end)) {
            valid = false;}

        /*CHECK IF COORDINATES == LENGTH OF SPECIFIC SHIP (input parameter "length")*/
        /*when ship is placed horizontally; line_start == line_end*/
        if (line_start == line_end) {
            int input_length = Math.abs(col_start - col_end) + 1;
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

        String validLines = "ABCDEFGHIJ";

        List<String> ships = Arrays.asList("Carrier (Length: 6)", "Battleship (Length: 4)",
                "Submarine (Length: 3)", "Patrol Boat (Length: 2)");
        List<Integer> amount = Arrays.asList(1, 2, 3, 4);
        List<Integer> ship_len = Arrays.asList(6, 4, 3, 2);
        int idx = 0;
        int i;
        /* This variable stores the entered ships and passes them to the grid to save them.
         * Each ship type is saved in a separate nested ArrayList. */
        ArrayList<Ship>[] shipList = new ArrayList[]{new ArrayList<Ship>(), new ArrayList<Ship>(), new ArrayList<Ship>(), new ArrayList<Ship>()};

        System.out.println("Deploy your ships");
        for (i = 1; i <= amount.get(idx); ) {
            System.out.println("Enter the start and end coordinates for your " +i+ ". " + ships.get(idx) +
                    " as uppercase letters separated by a comma (e.g. A1,A9)");
            String coordinates = input.next();

            /*test if input has total length 5 (otherwise no valid input)*/
            if (coordinates.length() != 5) {System.out.println("Input is not valid. Try again");continue;}
            /*test if input is separated by comma*/
            if (!coordinates.contains(",")) {System.out.println("Invalid input. Try again");continue;}

            /*separate input in start & end coordinates*/
            String x = coordinates.substring(0,2);
            String y = coordinates.substring(3,5);

            /*test if column input is between A-J*/
            if (validLines.indexOf(x.charAt(0)) == -1 && validLines.indexOf(y.charAt(0)) == -1) {
                System.out.println("Invalid input. Try again");continue;}

            Coordinate start = new Coordinate(validLines.indexOf(x.charAt(0)),
                    Character.getNumericValue(x.charAt(1)));
            Coordinate end = new Coordinate(validLines.indexOf(y.charAt(0)),
                    Character.getNumericValue(y.charAt(1)));
            /* check if input is valid*/
            boolean valid_input_length = coordinateInBoundaries(start,end,ship_len.get(idx));
            boolean freePlace = true;

            for (int xPos = start.getX(); xPos <= end.getX(); xPos++){
                for (int yPos = start.getY(); yPos <= end.getY(); yPos++){
                    if (aGrid.getCoordinateValue(yPos, xPos) != ' '){ //check if collision in ocean grid of player
                        freePlace = false;
                    }
                }
            }

            /* CHECK IF SHIP CAN BE PLACED IN GRID -> bool free_place*/

            if (!valid_input_length || !freePlace) {System.out.println("Invalid Input. Try again");continue;}

            /* if (valid_input_length && free_place)
                -> initialize ships with given coordinates
                -> safe coordinates in grid */
            aGrid.setShip(start, end, idx);


            /*create shipList for grid*/
            shipList[idx].add(new Ship(start.getX(),start.getY(),end.getX(),end.getY()));
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

    /* Player enters coordinate of shot.
    * Validity check of shot (is the given string a coordinate on the field
    * and has this field already been targeted before).
    * Update target grid of player. */
    public Coordinate shoot(Grid targetGrid){
        if(isHuman){
            return humanShoot(targetGrid);
        } else {
            return computerShoot(targetGrid);
        }
    }

    private Coordinate computerShoot(Grid targetGrid) {
        /* Target a random fields.
        * Create an array with all rows with positions the computer can attack. From these rows
        * randomly choose one to attack. */
        Coordinate shot;
        Random rand = new Random(); // We need rand to create random numbers
        ArrayList<Integer> freeRows = new ArrayList<>();
        for(int y = 0; y < 10; y++){
            for(int x = 0; x < 10; x++){
                // Add a row to the list if there is at least one opportunity to attack
                if(targetGrid.getCoordinateValue(y, x) == ' '){
                    freeRows.add(y);
                    break;  // Immediately go to the next row
                }
            }
        }
        int attackRow = freeRows.get(rand.nextInt(freeRows.size()));
        /* Then create an array of y coordinates of that row, which are
         * still free to attack. From these y coordinates randomly choose one to attack. */
        ArrayList<Integer> freePositions = new ArrayList<>();
        for(int x = 0; x < 10; x++){
            if(targetGrid.getCoordinateValue(attackRow, x) == ' '){
                freePositions.add(x);
            }
        }
        int attackColumn = freePositions.get(rand.nextInt(freePositions.size()));
        return new Coordinate(attackColumn,attackRow);
    }

    private Coordinate humanShoot(Grid targetGrid) {
        Scanner input = new Scanner(System.in);
        boolean inputIsValid = false;
        int x = -1,y = -1;
        while(!inputIsValid){
            System.out.println("Which coordinate would you like to shoot at?");
            String coordinateOfShot = input.next();
            if (coordinateOfShot.length() != 2) {   // If input has a length != 2 the input is invalid
                System.out.println("Give input of length two. E.g. F5");
                continue;
            }
            x = coordinateOfShot.charAt(0) - 65;    // Ascii('A') is 65 => 'A'-65=0
            y = coordinateOfShot.charAt(1) - 48;    // Ascii('0') is 48 => '0'-48=0
            if (x < 0 || x > 9 || y < 0 || y > 9){  // Check if the coordinate is on the field
                System.out.println("Input out of bounds. E.g. for a valid input: E5");
                continue;
            }
            /* Check if the coordinate has already been shot at.
            * If targetGrid.getCoordinateValue(x,y) returns 'X' or "o", this coordinate has already been targeted. */
            if(targetGrid.getCoordinateValue(y,x) != ' '){
                System.out.println("This coordinate has already been targeted.");
                continue;
            }
            inputIsValid = true;
        }
        // Save the shot in the targetGrid
        targetGrid.updateGrid(x, y);
        return new Coordinate(x,y);
    }
}
