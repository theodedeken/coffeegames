package voide.resources;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

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
     * Load the configuration file for all the paths to the resources
     * 
     * @param path the path to the file
     * 
     * @return a map of resource identifier to path list
     */
    public static Map<String, ResourceNamespace> loadResourceMap(String path) {
        try {
            InputStream file = loadFile(path);
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            return mapper.readValue(file, new TypeReference<Map<String, ResourceNamespace>>() {
            });
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
        }
        return null;
    }

    public static Map<String, Resource> loadResources(String namespace, ResourceNamespace info) {
        try {
            Class<Resource> outputClass = (Class<Resource>) Class.forName(info.getClassName());
            Map<String, Resource> resources = new HashMap<>();
            for (String path : info.getFiles()) {
                InputStream file = loadFile(path);
                ObjectMapper mapper = new ObjectMapper();
                JsonNode tree = mapper.readTree(file);

                Iterator<Entry<String, JsonNode>> nodes = tree.fields();
                while (nodes.hasNext()) {
                    Entry<String, JsonNode> node = nodes.next();
                    Resource resource = mapper.readValue(node.getValue().toString(), outputClass);
                    resource.initialize();
                    resources.put(namespace + "." + node.getKey(), resource);
                }
            }

            return resources;
        } catch (IOException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
        }
        return null;

    }

}
