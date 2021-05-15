package voide.resources;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.management.RuntimeErrorException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import voide.io.FileReader;

public final class Loader {
    private static final Logger LOGGER = Logger.getLogger(Loader.class.getName());

    private Loader() {
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
            InputStream file = new FileReader(path).toStream();
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
            for (ResourceFile resFile : info.getFiles()) {
                // TODO cleanup
                if (resFile.isXML()) {
                    if (resFile.isSingle()) {
                        Resource resource = loadXML(resFile.getPath(), outputClass);
                        resources.put(namespace + "." + resFile.getKey(), resource);
                    } else {
                        throw new RuntimeException("Not implemented");
                    }
                } else {
                    if (resFile.isSingle()) {
                        InputStream file = new FileReader(resFile.getPath()).toStream();
                        ObjectMapper mapper = new ObjectMapper();
                        Resource resource = mapper.readValue(file, outputClass);
                        resource.initialize();
                        resources.put(namespace + "." + resFile.getKey(), resource);
                    } else {
                        InputStream file = new FileReader(resFile.getPath()).toStream();
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

                }

            }

            return resources;
        } catch (IOException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
        }
        return null;

    }

    public static Resource loadXML(String path, Class<Resource> outputClass)
            throws JsonParseException, JsonMappingException, IOException {
        InputStream file = new FileReader(path).toStream();
        XmlMapper mapper = new XmlMapper();
        Resource resource = mapper.readValue(file, outputClass);
        resource.initialize();
        return resource;
    }
}
