/**
 * Group 29, Piyush Deshmukh(23200229) & Abhishek Wadmare(23200277)
 */
public class Main {
    public static void main(String[] args) {
        Board board = new Board();
        while (!board.isGameOver()) {
            Moves.move(board, View.getInput(board));
        }
    }
}