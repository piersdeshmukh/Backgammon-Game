/**
 * Group 29, Piyush Deshmukh(23200229) & Abhishek Wadmare(23200277)
 */
import java.util.Scanner;
import java.util.ArrayList;

public class View {
    public static boolean isWrongInput = false,
            isPipCalled = false,
            isHintCalled = false,
            displayMoves = false,
            isDoublingOffered = false;

    public static int matchLength, activePlayer = 1;

    public static void setMatchLength() {
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Match Length: ");
        View.matchLength = scanner.nextInt();
        System.out.println();
    }
    /**
     * Displays the game board
     * @param board The Board
     */
    public static void displayBoard(Board board) {
        displayHeader();
        displayLengthAndScore(board);
        displayDoublingCube();
        displayPlayer(board);
        displayPipScore(board);
        displayTopTriangles(board);
        displayBar(board);
        displayBottomTriangles(board);
        displayDice();
        if (displayMoves)
            displayMoves(board);
    }
    /**
     * Displays the bar with checker counts
     * @param board The Board
     */
    private static void displayBar(Board board) {
        System.out.println("\n                                                        Bar");
        System.out.printf("                                                       [" + board.getWhiteBar().bar.size() + " \u001B[31m" + board.getRedBar().bar.size() + "\u001B[0m]");
    }

    public static void displayHeader() {
        System.out.println("------------------------------------------------------------------");
        System.out.println("                    ------   Backgammon   ------                  ");
        System.out.println("------------------------------------------------------------------");
    }
    /**
     * Displays current match length and scores
     * @param board The Board
     */
    public static void displayLengthAndScore(Board board) {
        System.out.print("Scores: " + board.playerOne.getName()+": "+board.playerOne.getScore() +", "+board.playerTwo.getName()+": "+board.playerTwo.getScore());
        System.out.println("    Match Length: " + matchLength);
    }
    /**
     * Displays doubling cube value and owner
     */
    public static void displayDoublingCube() {
        System.out.print("Doubling Cube Value: " + Dices.doublingCube);
        if (Dices.doublingCubeOwner == null)
            System.out.println(" Owner: None");
        else
            System.out.println(" Owner: " + Dices.doublingCubeOwner.getName());
    }
    /**
     * Displays active player and color
     * @param board The Board
     */
    public static void displayPlayer(Board board) {
        Player player;
        if (View.activePlayer == 1) {
            player = board.playerOne;
        } else {
            player = board.playerTwo;
        }
        if (isWrongInput || isHintCalled)
            System.out.println("It is still " + player.getName() + "'s turn:");
        else
            System.out.println("It is now " + player.getName() + "'s turn:");
        System.out.println("your checker's colour is: " + player.getColour());
    }
    /**
     * Displays pip count for active player
     * @param board The Board
     */
    public static void displayPipScore(Board board) {
        int pipCount = 0;
        Player player = board.getActivePlayer();
        for (Triangle t : board.getTriangles().getColoredTriangles()) {
            if (t.getColor() == null || t.getColor().equals(board.getActivePlayer().getColour())) {
                int index = t.getId();
                int pipPointCount = (board.getActivePlayer() == board.playerOne) ? index * t.getCheckerCount() : (25 - index) * t.getCheckerCount();
                pipCount += pipPointCount;
                player.setPipCount(pipCount);
            }
        }
        System.out.println("Pip Count : " + pipCount);
    }
    /**
     * Displays dice values
     */
    public static void displayDice() {
        System.out.println(" Dices One \t  Dices Two");
        if (Dices.isRolled()) {
            if (Dices.diceOne == 0)
                System.out.println("  [     ]  " + "    [  " + Dices.diceTwo + "  ]\n");
            else if (Dices.diceTwo == 0)
                System.out.println("  [  " + Dices.diceOne + "  ]  " + "    [     ]\n");
            else
                System.out.println("  [  " + Dices.diceOne + "  ]  " + "    [  " + Dices.diceTwo + "  ]\n");
        } else {
            System.out.println("  [     ]  " + "    [     ]\n");
        }
    }
    /**
     * Displays top triangle row
     * @param board The Board
     */
    public static void displayTopTriangles(Board board) {
        System.out.print("\t   13       14       15       16       17       18       19       20       21       22       23       24");
        System.out.print("\n\t");
        for (int i = 12; i < 24; i++)
            System.out.printf(board.getTriangles().getTriangle(i) + "  ");

    }
    /**
     * Displays bottom triangle row
     * @param board The Board
     */
    public static void displayBottomTriangles(Board board) {
        System.out.print("\n\t");
        for (int i = 11; i >= 0; i--)
            System.out.print(board.getTriangles().getTriangle(i) + "  ");
        System.out.print("\n\t ");
        System.out.println("  12       11       10       9        8         7        6        5        4        3       2        1   ");
        System.out.print("\n");
    }

    public static Player setPlayer() {
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        String name;
        String color;
        System.out.println("  ---  Welcome Player " + activePlayer + "   ---   ");
        System.out.print("Enter Your Name: ");
        name = scanner.nextLine();
        if (activePlayer == 1)
            color = "WHITE";
        else
            color = "RED";
        Moves.switchPlayer();
        System.out.println();
        return new Player(name, color);
    }
    /**
     * Gets player input move
     * @param board The Board
     * @return Input string
     */
    public static String getInput(Board board) {
        View.displayBoard(board);
        Scanner sc = new Scanner(System.in);
        if (isWrongInput) {
            System.err.print("\nLast command was invalid, please enter a valid command :");
            isWrongInput = false;
        } else if (isPipCalled) {
            System.out.println("Pip counts:" +
                    "\nPlayer 1: " + board.playerOne.getPipCount() +
                    "\nPlayer 2: " + board.playerTwo.getPipCount());
            System.out.print("\ninput your move: ");
            isPipCalled = false;
        } else if (isHintCalled) {
            if (!displayMoves)
                if (board.getActivePlayer() == Dices.doublingCubeOwner || Dices.doublingCubeOwner == null) {
                    System.out.println("""
                            HINTS:\s
                            1.''ROLL''
                            2.''ROLL <Dice One Value> <Dice Two Value>''
                            3.''PIP''
                            4.''QUIT''
                            5.''DOUBLE''""");
                } else {
                    System.out.println("""
                            HINTS:\s
                            1.''ROLL''
                            2.''ROLL <Dice One Value> <Dice Two Value>''
                            3.''PIP''
                            4.''QUIT''""");
                }
            else
                System.out.println("""
                        HINTS:\s
                        1.''<Option-number> <Move-number>''
                        2.''PIP''
                        3.''QUIT''""");
            System.out.print("\ninput your move: ");
            isHintCalled = false;
        } else if (isDoublingOffered) {
            System.out.print("\nACCEPT or REFUSE Doubling: ");
        } else
            System.out.print("\ninput your move: ");
        return sc.nextLine();
    }
    /**
     * Displays possible moves for the current player
     * @param board The Board
     */
    public static void displayMoves(Board board) {
        String playerColour = board.getActivePlayer().getColour();
        Bar curr_bar = View.activePlayer == 1 ? board.getWhiteBar() : board.getRedBar();
        int[] diceValues = {Dices.diceOne, Dices.diceTwo, Dices.diceTwo + Dices.diceOne};
        ArrayList<ArrayList<int[]>> allMoves = new ArrayList<>();
        if (Dices.diceOne == 0 || Dices.diceTwo == 0)
            diceValues[2] = 0;

        for (int diceValue : diceValues) {
            int index;
            ArrayList<int[]> diceMoves = new ArrayList<>();
            if (curr_bar.bar.isEmpty()) {
                for (Triangle t : board.getTriangles().getColoredTriangles()) {
                    if (diceValue == 0)
                        break;
                    if (t.getColor() == null || t.getColor().equals(playerColour)) {    // If the triangle is unoccupied or occupied by the active player
                        index = t.getId();
                        if (Moves.isValidMove(board, index, diceValue)) {
                            int targetIndex = (board.getActivePlayer() == board.playerOne) ? index - diceValue : index + diceValue;
                            diceMoves.add(new int[]{index, targetIndex});               // Add the move to the list
                        }
                    }
                }
            } else {
                index = View.activePlayer == 1 ? 25 : 0;
                if (Moves.isValidMove(board, index, diceValue)) {
                    int targetIndex = (board.getActivePlayer() == board.playerOne) ? index - diceValue : index + diceValue;
                    diceMoves.add(new int[]{index, targetIndex});                       // Add the move to the list
                }
            }
            allMoves.add(diceMoves);                            // Add the list of moves for the current dice value to the list of all moves
        }
        board.possibleMoves = allMoves;                         // Set the possible moves for the board
        for (int i = 0; i < allMoves.size(); i++) {             // Display the possible moves
            if (allMoves.get(i).isEmpty())
                System.out.println("Option " + (i + 1) + ": Not Available");
            else
                System.out.println("Option " + (i + 1) + ":");
            int choice = 0;
            for (int[] move : allMoves.get(i)) {
                String moveText = ++choice + ". Play ";
                if (move[0] == 0 || move[0] == 25) {
                    System.out.println(moveText + "Bar - " + move[1]);
                } else if (move[1] > board.TOTAL_TRIANGLES || move[1] < 0) {
                    System.out.println(moveText + move[0] + "- OFF");
                } else {
                    System.out.println(moveText + move[0] + "-" + move[1]);
                }
            }
        }

    }
}
