import org.junit.Test;
import static org.junit.Assert.*;

public class BoardTest {
    @Test
    public void testIsMatchOver() {
        Board board = new Board();
        board.playerOne.setPipCount(0);

        assertTrue(board.isMatchOver());

        board.playerTwo.setPipCount(0);
        assertTrue(board.isMatchOver());

        board.isQuit = true;
        assertTrue(board.isMatchOver());
    }

    @Test
    public void testIsGameOver() {
        Board board = new Board();
        Board.no_of_matches = View.matchLength;

        assertTrue(board.isGameOver());

        Board.no_of_matches = View.matchLength - 1;
        assertFalse(board.isGameOver());
    }

    @Test
    public void testGetActivePlayer() {
        Board board = new Board();
        assertEquals(board.playerOne, board.getActivePlayer());

        View.activePlayer = 2;
        assertEquals(board.playerTwo, board.getActivePlayer());
    }

    @Test
    public void testGetRedBar() {
        Board board = new Board();
        assertNotNull(board.getRedBar());
        assertEquals("RED", board.getRedBar().getColor());
    }

    @Test
    public void testGetWhiteBar() {
        Board board = new Board();
        assertNotNull(board.getWhiteBar());
        assertEquals("WHITE", board.getWhiteBar().getColor());
    }

}