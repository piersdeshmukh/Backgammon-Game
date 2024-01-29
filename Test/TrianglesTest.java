import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
public class TrianglesTest {

    @Test
    public void testGetTriangle() {
        Triangles triangles = new Triangles(24);

        // Get a triangle
        Triangle triangle = triangles.getTriangle(0);

        // Check if the triangle is correct
        assertEquals(1, triangle.getId());
        assertEquals(2, triangle.getCheckerCount());
        assertEquals("RED", triangle.getColor());
    }

    @Test
    public void testGetColoredTriangles() {
        Triangles triangles = new Triangles(24);

        // Get colored triangles
        ArrayList<Triangle> coloredTriangles = triangles.getColoredTriangles();

        // Check if the colored triangles are correct
        assertEquals(8, coloredTriangles.size());
    }

    @Test
    public void testResetTriangles() {
        Triangles triangles = new Triangles(24);

        // Reset the triangles
        triangles.resetTriangles();

        // Check if the triangles were reset correctly
        for (int i = 0; i < 24; i++) {
            Triangle triangle = triangles.getTriangle(i);
            switch (triangle.getId()) {
                case 1, 24:
                    assertEquals(2, triangle.getCheckerCount());
                    break;
                case 6, 13, 12, 19:
                    assertEquals(5, triangle.getCheckerCount());
                    break;
                case 8,17:
                    assertEquals(3, triangle.getCheckerCount());
                    break;
                default:
                    assertEquals(0, triangle.getCheckerCount());
                    assertNull(triangle.getColor());
                    break;
            }
        }
    }
}
