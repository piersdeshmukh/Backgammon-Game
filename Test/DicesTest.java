import org.junit.Test;
import static org.junit.Assert.*;
public class DicesTest {

    @Test
    public void testRoll() {
        // Roll the dice
        Dices.roll();

        // Check if the dice values are between 1 and 6
        assertTrue(Dices.diceOne >= 1 && Dices.diceOne <= 6);
        assertTrue(Dices.diceTwo >= 1 && Dices.diceTwo <= 6);
    }

    @Test
    public void testIsRolled() {
        // Reset the dice
        Dices.resetDice();

        // Check if the dice have not been rolled
        assertFalse(Dices.isRolled());

        // Roll the dice
        Dices.roll();

        // Check if the dice have been rolled
        assertTrue(Dices.isRolled());
    }
}
