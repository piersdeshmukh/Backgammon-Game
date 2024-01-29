import org.junit.Test;
import static org.junit.Assert.*;
public class TriangleTest {

    @Test
    public void testInsertChecker() {
        Triangle triangle = new Triangle(1);
        Checker checker = new Checker("RED");

        // Insert a checker into the triangle
        triangle.insertChecker(checker, null);

        // Check if the checker was inserted correctly
        assertEquals(1, triangle.getCheckerCount());
        assertEquals("RED", triangle.getColor());
    }

    @Test
    public void testRemoveChecker() {
        Triangle triangle = new Triangle(1);
        Checker checker = new Checker("RED");

        // Insert a checker into the triangle
        triangle.insertChecker(checker, null);

        // Remove the checker from the triangle
        Checker removedChecker = triangle.removeChecker();

        // Check if the checker was removed correctly
        assertEquals(0, triangle.getCheckerCount());
        assertEquals(checker, removedChecker);
    }

    @Test
    public void testResetTriangle() {
        Triangle triangle = new Triangle(1, 5, "RED");

        // Check if the triangle was initialized correctly
        assertEquals(5, triangle.getCheckerCount());
        assertEquals("RED", triangle.getColor());

        // Reset the triangle
        triangle.resetTriangle(3, "WHITE");

        // Check if the triangle was reset correctly
        assertEquals(3, triangle.getCheckerCount());
        assertEquals("WHITE", triangle.getColor());
    }
}
