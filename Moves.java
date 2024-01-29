/**
 * Group 29, Piyush Deshmukh(23200229) & Abhishek Wadmare(23200277)
 */
public class Moves {
    /**
     * Switches the active player
     */
    public static void switchPlayer() {
        if (View.activePlayer == 1)
            View.activePlayer = 2;
        else
            View.activePlayer = 1;
    }
    /**
     * Makes a move based on the input command
     * @param board The Board
     * @param commandLine The command
     */
    public static void move(Board board, String commandLine) {
        String[] command = commandLine.toUpperCase().split(" ");
        switch (command[0].length()) {
            case 6:
                switch (command[0]) {
                    case "ACCEPT":
                        if (View.isDoublingOffered) {
                            Dices.setDoublingCubeOwner(board);
                            View.isDoublingOffered = false;
                        } else {
                            View.isWrongInput = true;
                            return;
                        }
                        break;
                    case "REFUSE":
                        if (View.isDoublingOffered) {
                            board.isQuit = true;
                            System.out.println(board.getActivePlayer().getName() + " Loses this match!!!");
                            View.isDoublingOffered = false;
                            return;
                        } else {
                            View.isWrongInput = true;
                            return;
                        }
                    case "DOUBLE":
                        View.isDoublingOffered = true;
                        Dices.rollDoublingCube();
                        break;
                }
                break;
            case 4:
                switch (command[0]) {
                    case "QUIT":
                        board.isQuit = true;
                        System.out.println("Bye!!!");
                        break;
                    case "ROLL":
                        //Rolling dice with manual values
                        if (command.length == 3) {
                            if (Character.isDigit(command[1].charAt(0)) &&
                                    Character.isDigit(command[2].charAt(0))) {
                                Dices.roll(Integer.parseInt(command[1]), Integer.parseInt(command[2]));
                                View.displayMoves = true;
                                board.getActivePlayer().remainingMoves = 2;
                            } else {
                                // If the manual values are not digits
                                View.isWrongInput = true;
                                return;
                            }
                        } else {
                            // Random values assigned to both dice
                            Dices.roll();
                            View.displayMoves = true;
                            board.getActivePlayer().remainingMoves = 2;
                        }
                        break;
                    case "HINT":
                        View.isHintCalled = true;
                        return;
                    case "TEST":                                        // If the command is TEST, execute the commands from the specified file
                        Commands commands = new Commands(command[1].toLowerCase());
                        for (String commandL : commands) {
                            View.displayBoard(board);
                            System.out.println("\nInput from file: " + commandL);
                            move(board, commandL);
                            if (View.isWrongInput) {                    // If the command from the file is invalid, print an error message and set the wrong input flag to false
                                System.err.print("\nLast command from file was invalid, please enter a valid command!!!");
                                View.isWrongInput = false;
                                return;
                            }
                        }
                        return;
                    default:
                        View.isWrongInput = true;
                        return;
                }
                break;
            case 3:
                switch (command[0]) {
                    case "PIP":
                        View.isPipCalled = true;
                        break;
                    default:
                        View.isWrongInput = true;
                        return;
                }

            case 1:                                                     // If there are two arguments, interpret them as the option and move
                int option = Integer.parseInt(command[0]) - 1;
                int move = Integer.parseInt(command[1]) - 1;
                if (board.getActivePlayer().remainingMoves == 2) {
                    if (option == 0)
                        Dices.diceOne = 0;
                    else if (option == 1)
                        Dices.diceTwo = 0;
                }
                moveChecker(option, move, board);
                if (View.isWrongInput)
                    return;
                break;
            default:
                View.isWrongInput = true;
                return;
        }
        if (View.isWrongInput) return;
        if (board.getActivePlayer().remainingMoves <= 0) {              // If the active player has no remaining moves, reset the dice, stop displaying moves, and switch the active player
            Dices.resetDice();
            View.displayMoves = false;
            switchPlayer();
        }

    }
    /**
     * Attempts to move a checker from source triangle to target triangle
     * @param option Index of the die value used
     * @param move Index of the move
     * @param board The Board
     */
    public static void moveChecker(int option, int move, Board board) {
        try {
            int source = board.possibleMoves.get(option).get(move)[0] - 1;
            int target = board.possibleMoves.get(option).get(move)[1] - 1;
            if (source - target == Dices.diceOne + Dices.diceTwo)           // If the move uses both dice values, decrement the remaining moves by 2
                board.getActivePlayer().remainingMoves -= 2;
            else
                board.getActivePlayer().remainingMoves -= 1;                // Otherwise, decrement the remaining moves by 1
            if (source == -1 || source == 24) {                             // If the source is a bar
                Bar bar = source == -1 ? board.getRedBar() : board.getWhiteBar();       // Get the appropriate bar based on the source index
                Triangle targetTriangle = board.getTriangles().getTriangle(target);
                targetTriangle.insertChecker(bar.removeChecker(), board);               // Remove a checker from the bar and insert it into the target triangle
            } else if (target >= board.TOTAL_TRIANGLES || target < 0) {                 // If the target is off the board
                Triangle sourceTriangle = board.getTriangles().getTriangle(source);
                sourceTriangle.removeChecker();
            } else {                                                                    // If the move is within the board
                Triangle sourceTriangle = board.getTriangles().getTriangle(source);
                Triangle targetTriangle = board.getTriangles().getTriangle(target);
                Checker tempChecker = sourceTriangle.removeChecker();
                targetTriangle.insertChecker(tempChecker, board);
            }
        } catch (IndexOutOfBoundsException e) {
            View.isWrongInput = true;                                                   // If an invalid option or move is provided, set the wrong input flag to true
        }
    }
    /**
     * Checks if a potential move is valid
     * @param board The Board
     * @param index Triangle index
     * @param diceValue Value of the die
     * @return true if valid, false otherwise
     */
    public static boolean isValidMove(Board board, int index, int diceValue) {
        int targetIndex = (board.getActivePlayer() == board.playerOne) ? index - diceValue : index + diceValue;
        if (targetIndex > 0 && targetIndex <= board.TOTAL_TRIANGLES) {
            Triangle targetTriangle = board.getTriangles().getTriangle(targetIndex - 1);
            // If the target triangle is unoccupied, occupied by the active player, or only has one checker
            if (targetTriangle.getColor() == null || targetTriangle.getColor().equals(board.getActivePlayer().getColour()) || targetTriangle.triangle.size() <= 1) {
                return true;                        // The move is valid
            }
        } else
            return board.getTriangles().getHomeQuadrantCheckerCount(board) == 15;   // If the target index is outside the valid range, check if all checkers are in the home quadrant
        return false;                               // The move is invalid
    }

}
