/**
 * Group 29, Piyush Deshmukh(23200229) & Abhishek Wadmare(23200277)
 */
import java.util.Random;

public class Dices {
    public static int diceOne, diceTwo, doublingCube = 1;
    private static boolean diceOneRolled = false, diceTwoRolled = false;
    public static Player doublingCubeOwner;
    /**
     * Sets the current doubling cube owner
     * @param board The Board
     */
    public static void setDoublingCubeOwner(Board board) {
        Dices.doublingCubeOwner = board.getActivePlayer();
        doublingCube *= 2;
    }

    /**
     * Rolls a single die
     * @param diceNumber 1 for first die, 2 for second die
     */
    public static void roll(int diceNumber) {
        Random rand = new Random();
        if (diceNumber == 1) {
            diceOne = rand.nextInt(6) + 1;
            diceOneRolled = true;
        } else {
            diceTwo = rand.nextInt(6) + 1;
            diceTwoRolled = true;
        }
    }
    /**
     * Doubles the current value of the doubling cube
     */
    public static void rollDoublingCube() {
        if (doublingCube != 64) {
            System.out.println("Stakes are now being doubled to: " + doublingCube * 2);
        } else {
            View.isDoublingOffered = false;
            System.err.println("Cannot Double Further");
        }
    }
    /**
     * Rolls both dice with random values
     */
    public static void roll() {
        Random rand = new Random();
        diceOne = rand.nextInt(6) + 1;
        diceTwo = rand.nextInt(6) + 1;
        diceOneRolled = true;
        diceTwoRolled = true;
    }
    /**
     * Sets fixed values for both dice
     * @param diceOneValue Value for first die
     * @param diceTwoValue Value for second die
     */
    public static void roll(int diceOneValue, int diceTwoValue) {
        diceOne = diceOneValue;
        diceTwo = diceTwoValue;
        diceOneRolled = true;
        diceTwoRolled = true;
    }
    /**
     * Resets rolled flags to false
     */
    public static void resetDice() {
        diceOneRolled = false;
        diceTwoRolled = false;
    }
    /**
     * Checks if both dice have been rolled
     * @return true if rolled, false otherwise
     */
    public static boolean isRolled() {
        return diceTwoRolled && diceOneRolled;
    }
}