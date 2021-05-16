package spaxel.loaders;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import voide.input.Key;
import voide.input.KeyState;

/**
 * Created by theo on 28/05/17.
 */
public final class Loader {
    private static final Logger LOGGER = Logger.getLogger(Loader.class.getName());

    private Loader() {
    }

    /**
     * Load a file as an inputstream
     * 
     * @param path path to the file
     * 
     * @return the file as inputstream
     */
    public static InputStream loadFile(String path) {
        return Loader.class.getResourceAsStream(path);
    }

    /**
     * Loads the key configuration file specified in the argument
     * 
     * @param path the path to the key configuration file
     * 
     * @return the key configuration
     */
    public static Map<Key, KeyState> loadKeyConfiguration(String path) {
        try {
            InputStream file = loadFile(path);
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

            return mapper.readValue(file, new TypeReference<Map<Key, KeyState>>() {
            });
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
        }
        return null;
    }
}
