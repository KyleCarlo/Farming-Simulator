package Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class reads the content of a file and stores it in a string.
 */
public class ReadFile {
    private String content = "";

    /**
     * Constructs a ReadFile object and initializes it with the content of the
     * specified file.
     *
     * @param filename the name of the file to read
     */
    public ReadFile(String filename) {
        try {
            File file = new File("./resources/" + filename);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                content += line + "\n";
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the content of the file that was read.
     *
     * @return the content of the file as a string
     */
    public String getContent() {
        return content;
    }
}
