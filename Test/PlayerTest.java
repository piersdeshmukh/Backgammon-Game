import org.junit.Test;
import static org.junit.Assert.*;
public class PlayerTest {

    @Test
    public void testPlayer() {
        Player player = new Player("John", "Red");

        // Check if the player's name and colour are correct
        assertEquals("John", player.getName());
        assertEquals("Red", player.getColour());

        // Check if the initial score and pip count are correct
        assertEquals(0, player.getScore());
        assertEquals(167, player.getPipCount());
    }

    @Test
    public void testSetScore() {
        Player player = new Player("John", "Red");

        // Set the player's score
        player.setScore(10);

        // Check if the score is updated correctly
        assertEquals(10, player.getScore());
    }

    @Test
    public void testSetPipCount() {
        Player player = new Player("John", "Red");

        // Set the player's pip count
        player.setPipCount(150);

        // Check if the pip count is updated correctly
        assertEquals(150, player.getPipCount());
    }

    @Test
    public void testResetPlayer() {
        Player player = new Player("John", "Red");


        // Reset the player
        player.resetPlayer();

        // Check if the score and pip count are reset correctly
        assertEquals(0, player.remainingMoves);
        assertEquals(167, player.getPipCount());
    }
}
