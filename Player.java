/**
 * Group 29, Piyush Deshmukh(23200229) & Abhishek Wadmare(23200277)
 */
public class Player {
    private final String name;
    private final String colour;
    private int pipCount = 167;
    int remainingMoves = 0;
    private int score=0;
    /**
     * Gets the player's current score
     * @return The score
     */
    public int getScore() {
        return score;
    }
    /**
     * Adds to the player's current score
     * @param score Points to add
     */
    public void setScore(int score) {
        this.score += score;
    }
    /**
     * Sets the number of remaining pips
     * @param pipCount Remaining pips
     */
    public void setPipCount(int pipCount) {
        this.pipCount = pipCount;
    }
    /**
     * Gets the number of remaining pips
     * @return Remaining pips
     */
    public int getPipCount() {
        return pipCount;
    }
    /**
     * Constructs a new Player
     * @param name The name
     * @param colour The color
     */
    public Player(String name, String colour) {
        this.name = name;
        this.colour = colour;
    }
    /**
     * Gets the player's name
     * @return The name
     */
    public String getName() {
        return name;
    }
    /**
     * Gets the player's color
     * @return The color
     */
    public String getColour() {
        return colour;
    }
    /**
     * Resets player data for a new match
     */
    public void resetPlayer() {
        remainingMoves = 0;
        pipCount = 167;
    }

}
