/**
 * Group 29, Piyush Deshmukh(23200229) & Abhishek Wadmare(23200277)
 */
import java.util.ArrayList;

public class Triangles {

    private final ArrayList<Triangle> triangles;

    /**
     *
     * @return triangles
     */
    public ArrayList<Triangle> getTriangles() {
        return triangles;
    }
    /**
     * Constructs the triangle list of the given size
     * @param size Number of triangles
     */
    public Triangles(int size) {
        triangles = new ArrayList<>(size);
        for (int i = 1; i <= size; i++) {
            Triangle triangle;

            switch (i) {
                case 1:
                    triangle = new Triangle(i, 2, "RED");
                    break;
                case 6, 13:
                    triangle = new Triangle(i, 5, "WHITE");
                    break;
                case 8:
                    triangle = new Triangle(i, 3, "WHITE");
                    break;
                case 12, 19:
                    triangle = new Triangle(i, 5, "RED");
                    break;
                case 17:
                    triangle = new Triangle(i, 3, "RED");
                    break;
                case 24:
                    triangle = new Triangle(i, 2, "WHITE");
                    break;
                default:
                    triangles.add(new Triangle(i));
                    continue;
            }
            triangles.add(triangle);
        }
    }
    /**
     * Gets a triangle by index
     * @param i Index of triangle
     * @return Triangle object
     */
    public Triangle getTriangle(int i) {
        return triangles.get(i);
    }
    /**
     * Gets only triangles containing checkers
     * @return List of populated triangles
     */
    public ArrayList<Triangle> getColoredTriangles() {
        ArrayList<Triangle> coloredTriangles = new ArrayList<>();
        for (int i = 0; i < triangles.size(); i++) {
            if (triangles.get(i).getColor() != null) {
                coloredTriangles.add(triangles.get(i));
            }
        }
        return coloredTriangles;
    }
    /**
     * Gets checker count in active player's home quadrant
     * @param board The Board
     * @return Checker count
     */
    public int getHomeQuadrantCheckerCount(Board board) {
        int start = (View.activePlayer == 1) ? 1 : 19;
        int end = start + 6;
        int count = 0;
        for (int i = start - 1; i < end - 1; i++) {
            if (getTriangle(i).getColor() == null || getTriangle(i).getColor().equals(board.getActivePlayer().getColour()))
                count += getTriangle(i).triangle.size();
        }
        return count;
    }
    /**
     * Resets all triangles for a new game
     */
    public void resetTriangles() {
        for (Triangle triangle : triangles) {
            switch (triangle.getId()) {
                case 1:
                    triangle.resetTriangle(2, "RED");
                    break;
                case 6, 13:
                    triangle.resetTriangle(5, "WHITE");
                    break;
                case 8:
                    triangle.resetTriangle(3, "WHITE");
                    break;
                case 12, 19:
                    triangle.resetTriangle(5, "RED");
                    break;
                case 17:
                    triangle.resetTriangle(3, "RED");
                    break;
                case 24:
                    triangle.resetTriangle(2, "WHITE");
                    break;
                default:
                    triangle.resetTriangle(0, null);
                    break;
            }
        }
    }
}
