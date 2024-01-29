import org.junit.Test;
import java.io.*;
import java.util.List;

import static org.junit.Assert.*;

public class CommandsTest {
    File tempFolder;
    @Test
    public void testLoadValidFile() throws Exception {
        File file = new File(tempFolder, "commands.txt");
        PrintWriter writer = new PrintWriter(file);
        writer.println("MOVE 1 2");
        writer.println("ROLL 4 3");
        writer.close();

        Commands commands = new Commands("commands.txt");

        List<String> expected = List.of("MOVE 1 2", "ROLL 4 3");
        assertNotEquals(expected, commands);
    }

    @Test
    public void testLoadInvalidFile() {
        Commands commands = new Commands("invalid.txt");

        assertTrue(commands.isEmpty());
    }

    @Test
    public void testLoadEmptyFile() throws Exception {
        File file = new File(tempFolder, "empty.txt");
        PrintWriter writer = new PrintWriter(file);
        writer.close();

        Commands commands = new Commands("empty.txt");

        assertTrue(commands.isEmpty());
    }
}
