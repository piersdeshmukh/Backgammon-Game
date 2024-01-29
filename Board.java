
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Group 29, Piyush Deshmukh(23200229) & Abhishek Wadmare(23200277)
 */
public class Board {
    private final Triangles triangles;
    private final Bar redBar;
    private final Bar whiteBar;
    public Player playerOne, playerTwo;
    ArrayList<ArrayList<int[]>> possibleMoves;
    public boolean isQuit = false;
    public final int TOTAL_TRIANGLES = 24;
    public static int no_of_matches = 0;
    /**
     * Constructs a new Board
     */
    public Board() {
        View.displayHeader();
        playerOne = View.setPlayer();
        playerTwo = View.setPlayer();
        View.setMatchLength();
        redBar = new Bar("RED");
        whiteBar = new Bar("WHITE");
        triangles = new Triangles(TOTAL_TRIANGLES);
        determineTurn();
    }

    /**
     * Checks if the match is over
     * @return true if match is over, false otherwise
     */
    public Boolean isMatchOver() {
        // If the game is quit, the player who didn't quit wins
        if (isQuit)
            if (getActivePlayer() == playerTwo) {
                System.out.println(playerOne.getName() + " Wins a Single!!");
                playerOne.setScore(1);
                return true;
            } else {
                System.out.println(playerTwo.getName() + " Wins a Single!!");
                playerTwo.setScore(1);
                return true;
            }
        // If player one has no pips left or the game is quit by player two, player one wins
        if (playerOne.getPipCount() == 0 ) {
            System.out.print(playerOne.getName() + "Wins!!");
            // If there are checkers in the home quadrant of the winner or on the bar, it's a Backgammon
            if (triangles.getHomeQuadrantCheckerCount(this) > 0 || !redBar.bar.isEmpty()){
                System.out.println(" a BackGammon with " + (3 * Dices.doublingCube) + " points");
                playerOne.setScore(3 * Dices.doublingCube);
            }
            // Count the opponent's checkers
            int opponent_checkers = 0;
            for (Triangle t : triangles.getColoredTriangles()) {
                if (t.getColor().equals("RED"))
                    opponent_checkers++;
            }
            // If all opponent's checkers are present and not even one is borne off, it's a Gammon
            if (opponent_checkers == 15)
            {
                System.out.println(" a Gammon with " + (2 * Dices.doublingCube) + " points");
                playerOne.setScore(2 * Dices.doublingCube);
            }
            else
                System.out.print(" a Single");
            playerOne.setScore(1);
            return true;
        } else if (playerTwo.getPipCount() == 0 ) {             // If player one has no pips left or the game is quit by player two, player one wins
            System.out.println(playerTwo.getName() + "Wins!!");
            // If there are checkers in the home quadrant of the winner or on the bar, it's a Backgammon
            if (triangles.getHomeQuadrantCheckerCount(this) > 0 || !whiteBar.bar.isEmpty())
            {
                System.out.println(" a BackGammon" + (3 * Dices.doublingCube) + " points");
                playerTwo.setScore(3 * Dices.doublingCube);
            }
            // Count the opponent's checkers
            int opponent_checkers = 0;
            for (Triangle t : triangles.getColoredTriangles()) {
                if (t.getColor().equals("WHITE"))
                    opponent_checkers++;
            }
            // If all opponent's checkers are present and not even one is borne off, it's a Gammon
            if (opponent_checkers == 15)
            {
                System.out.println(" a Gammon with " + (2 * Dices.doublingCube) + " points");
                playerTwo.setScore(2 * Dices.doublingCube);
            }
            else
                System.out.print(" a Single");
            playerTwo.setScore(1);
            return true;
        }
        return isQuit;
    }
    /**
     * Checks if the overall game is over
     * @return true if game is over, false otherwise
     */
    public boolean isGameOver() {
        if (isMatchOver() || isQuit) {
            no_of_matches++;
            isQuit = false;
            if (no_of_matches != View.matchLength) {
                setupNextMatch();
                return false;
            } else {
                if (playerOne.getScore() > playerTwo.getScore()) {
                    System.out.println(playerOne.getName() + " won " + playerOne.getScore() + " compared to "+ playerTwo.getScore()+" points of "+playerTwo.getName()+" in total " + View.matchLength+ " matches");
                    System.out.println(playerOne.getName() + " Wins the game!! ");
                }
                if (playerOne.getScore() < playerTwo.getScore()) {
                    System.out.println(playerTwo.getName() + " won " + playerTwo.getScore() + " compared to "+ playerOne.getScore()+" points of "+playerOne.getName()+" in total " + View.matchLength+ " matches");
                    System.out.println(playerTwo.getName() + " Wins the game!! ");
                }
                return true;
            }
        }
        return false;
    }

    /**
     * Sets up the board for the next match
     */
    private void setupNextMatch() {
        View.displayHeader();
        playerOne.resetPlayer();
        playerTwo.resetPlayer();
        redBar.resetBar();
        whiteBar.resetBar();
        triangles.resetTriangles();
        determineTurn();
    }
    /**
     * Gets the current active player
     * @return The active Player object
     */
    public Player getActivePlayer() {
        if (View.activePlayer == 1)
            return playerOne;
        return playerTwo;
    }
    /**
     * Gets the red bar
     * @return The red Bar object
     */
    public Bar getRedBar() {
        return redBar;
    }
    /**
     * Gets the white bar
     * @return The white Bar object
     */
    public Bar getWhiteBar() {
        return whiteBar;
    }
    /**
     * Gets the Triangles board
     * @return The Triangles object
     */
    public Triangles getTriangles() {
        return triangles;
    }
    /**
     * Rolls dice to determine first player and starts the game
     */
    public void determineTurn() {
        Dices.roll(1);
        Dices.roll(2);
        System.out.println("Player 1 rolls Dice 1 and Player 2 rolls Dice 2");
        System.out.println();

        do {
            if (Dices.diceOne < Dices.diceTwo)
                View.activePlayer = 2;
            else
                View.activePlayer = 1;
            View.displayDice();
        } while (Dices.diceOne == Dices.diceTwo);
        System.out.println();

        if (View.activePlayer == 1)
            System.out.println("Congratulations " + playerOne.getName() + " you got bigger dice!");
        else
            System.out.println("Congratulations " + playerTwo.getName() + " you got bigger dice!");
        Dices.resetDice();
        System.out.print("\nPress Enter to start Game : ");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        System.out.println();
    }
}
