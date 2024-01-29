import org.junit.Test;
import static org.junit.Assert.*;
public class MovesTest {

    @Test
    public void testSwitchPlayer() {
        // Set the active player to 1
        View.activePlayer = 1;

        // Switch the active player
        Moves.switchPlayer();

        // Check if the active player is now 2
        assertEquals(2, View.activePlayer);

        // Switch the active player again
        Moves.switchPlayer();

        // Check if the active player is now 1
        assertEquals(1, View.activePlayer);
    }

    @Test
    public void testMoveChecker() {
        Board board = new Board();
        int option = 0;
        int move = 0;

        // Add code here to set up the possible moves

        // Move a checker
        Moves.moveChecker(option, move, board);

        // Add assertions here to check if the checker was moved correctly
    }

    @Test
    public void testIsValidMove() {
        Board board = new Board();
        int index = 0;
        int diceValue = 1;

        // Check if the move is valid
        boolean isValid = Moves.isValidMove(board, index, diceValue);

        // Add an assertion here to check if the move is valid or not
    }
}
