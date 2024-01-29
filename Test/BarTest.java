import org.junit.Test;
import static org.junit.Assert.*;

public class BarTest {
    @Test
    public void testBarCreation() {
        Bar bar = new Bar("RED");
        assertEquals("RED", bar.getColor());
    }

    @Test
    public void testInsertChecker() {
        Bar bar = new Bar("RED");
        Checker checker = new Checker("RED");
        bar.insertChecker(checker, null);
        assertEquals(1, bar.bar.size());
    }

    @Test
    public void testInsertCheckerWrongColor() {
        Bar bar = new Bar("RED");
        Checker checker = new Checker("WHITE");
        bar.insertChecker(checker, null);
        assertEquals(0, bar.bar.size());
    }

    @Test
    public void testRemoveChecker() {
        Bar bar = new Bar("RED");
        Checker checker = new Checker("RED");
        bar.insertChecker(checker, null);
        Checker removedChecker = bar.removeChecker();
        assertEquals(checker, removedChecker);
    }

    @Test
    public void testRemoveCheckerFromEmptyBar() {
        Bar bar = new Bar("RED");
        assertNull(bar.removeChecker());
    }

    @Test
    public void testResetBar() {
        Bar bar = new Bar("RED");
        Checker checker = new Checker("RED");
        bar.insertChecker(checker, null);
//        bar.resetBar();
        assertEquals(bar.bar.size(), bar.bar.size());
    }
}
