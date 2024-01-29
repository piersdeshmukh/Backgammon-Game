import org.junit.Test;
import static org.junit.Assert.*;

public class CheckerTest {
    @Test
    public void testCheckerCreation() {
        Checker checker = new Checker("RED");
        assertEquals("RED", checker.getColour());
    }

    @Test
    public void testGetColour() {
        Checker checker = new Checker("WHITE");
        assertEquals("WHITE", checker.getColour());
    }
}
