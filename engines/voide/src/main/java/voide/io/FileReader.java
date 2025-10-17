package voide.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileReader {

    private static final Logger LOGGER = Logger.getLogger(
        FileReader.class.getName()
    );
    private String file;

    /**
     * Create a new FileToStringLoader
     */
    public FileReader(String file) {
        this.file = file;
    }

    /**
     * Load a file as an inputstream
     *
     * @param path path to the file
     *
     * @return the file as inputstream
     */
    public InputStream toStream() {
        return getClass().getResourceAsStream(file);
    }

    /**
     * Read the given file and return the contents as a string
     *
     * @return the contents of the file
     */
    public String toString() {
        StringBuilder result = new StringBuilder();
        try {
            InputStreamReader isreader = new InputStreamReader(toStream());
            BufferedReader reader = new BufferedReader(isreader);
            String buffer;
            while ((buffer = reader.readLine()) != null) {
                result.append(buffer + '\n');
            }
            reader.close();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
        }
        return result.toString();
    }
}
