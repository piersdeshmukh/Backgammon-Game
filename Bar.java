import java.util.Stack;

/** Group 29, Piyush Deshmukh(23200229) & Abhishek Wadmare(23200277)
 * This class represents a Bar in a game, which implements the Lane interface.
 */
public class Bar implements Lane {
    // Stack to hold the checkers in the bar
    Stack<Checker> bar;
    // Color of the bar
    private final String color;

    /**
     * Constructor for the Bar class.
     * @param color The color of the bar.
     */
    public Bar(String color) {
        this.bar = new Stack<>();
        this.color = color;
    }

    /**
     * This method is used to get the color of the bar.
     * @return String This returns the color of the bar.
     */
    @Override
    public String getColor() {
        return this.color;
    }

    /**
     * This method is used to insert a checker into the bar.
     * @param checker The checker to be inserted.
     * @param board The board on which the game is being played.
     */
    @Override
    public void insertChecker(Checker checker, Board board) {
        if (this.getColor().equals(checker.getColour()))
            bar.add(checker);
    }

    /**
     * This method is used to remove a checker from the bar.
     * @return Checker This returns the removed checker, or null if the bar is empty.
     */
    @Override
    public Checker removeChecker() {
        if (!bar.isEmpty()) {
            return bar.pop();
        }
        return null;
    }

    /**
     * This method is used to reset the bar by clearing all checkers.
     */
    public void resetBar() {
        bar.clear();
    }
}