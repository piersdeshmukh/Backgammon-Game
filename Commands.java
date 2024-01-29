/**
 * Group 29, Piyush Deshmukh(23200229) & Abhishek Wadmare(23200277)
 */
import java.io.*;
import java.util.ArrayList;
/**
 * Loads and stores a list of commands from a file
 */
public class Commands extends ArrayList<String> {
    /**
     * Constructs the Commands list from the given file
     * @param fileName The name of the file containing the commands
     */
    public Commands(String fileName) {
        loadCommands(fileName);
    }
    /**
     * Loads the commands from the file into the list
     * @param fileName The file to load commands from
     */
    public void loadCommands(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader("resources/" + fileName))) {
            String command;
            while ((command = reader.readLine()) != null) {
                this.add(command);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
