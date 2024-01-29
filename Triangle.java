/**
 * Group 29, Piyush Deshmukh(23200229) & Abhishek Wadmare(23200277)
 */
import java.util.Stack;

public class Triangle implements Lane {

    Stack<Checker> triangle;
    private final int id;
    /**
     * Constructs a triangle with the given ID
     * @param id Triangle ID
     */
    public Triangle(int id) {
        triangle = new Stack<>();
        this.id = id;
    }
    /**
     * Constructs a triangle with given checkers
     * @param id ID of the triangle
     * @param checkerCount Number of checkers
     * @param color Color of the checkers
     */
    public Triangle(int id, int checkerCount, String color) {
        this.id = id;
        triangle = new Stack<>();
        for (int i = 0; i < checkerCount; i++)
            insertChecker(new Checker(color), null);
    }

    private String numberRepresentation(int n) {
        if (n < 10) {
            return " " + n;
        } else {
            return Integer.toString(n);
        }
    }
    /**
     * Gets a string representation of this triangle
     * @return String representation
     */
    public String toString() {
        if (triangle.isEmpty()) {
            return "  [  ] ";
        } else if (this.getColor().equals("RED")) {
            return "  [\u001B[31m" + numberRepresentation(triangle.size()) + "\u001B[0m] ";
        } else {
            return "  [\u001B[0m" + numberRepresentation(triangle.size()) + "] ";
        }
    }
    /**
     * Gets color based on checkers in triangle
     * @return Color as a string
     */
    @Override
    public String getColor() {
        if (triangle.isEmpty())
            return null;
        return triangle.peek().getColour();
    }
    /**
     * Gets the ID of this triangle
     * @return Triangle ID
     */
    public int getId() {
        return id;
    }
    /**
     * Inserts checker into this triangle
     * @param checker Checker to insert
     * @param board Game Board
     */
    @Override
    public void insertChecker(Checker checker, Board board) {
        if (this.getColor() == null || this.getColor().equals(checker.getColour())) {
            triangle.add(checker);
        } else if (triangle.size() == 1) {
            Bar bar = View.activePlayer == 1 ? board.getRedBar() : board.getWhiteBar();
            bar.insertChecker((Checker) triangle.pop(), null);
            triangle.add(checker);
        }
    }
    /**
     * Removes and returns a checker
     * @return Removed checker
     */
    @Override
    public Checker removeChecker() {
        if (!triangle.isEmpty()) {
            return triangle.pop();
        }
        return null;
    }
    /**
     * Gets the number of checkers in this triangle
     * @return The checker count
     */
    public int getCheckerCount() {
        return triangle.size();
    }
    /**
     * Resets this triangle for a new game
     * Clears existing checkers and inserts new checkers
     * @param checkerCount Number of checkers to insert
     * @param color Color of the checkers
     */
    public void resetTriangle(int checkerCount, String color) {
        triangle.clear();
        for (int i = 0; i < checkerCount; i++)
            insertChecker(new Checker(color), null);
    }
}
