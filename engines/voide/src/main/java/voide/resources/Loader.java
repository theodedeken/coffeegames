package voide.resources;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import voide.io.FileReader;

public final class Loader {

    private static final Logger LOGGER = Logger.getLogger(Loader.class.getName());

    private Loader() {
    }

    /**
     * Load the configuration file for all the paths to the resources
     *
     * @param path the path to the file
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
        Class<Resource> outputClass = info.getClassType();
        Map<String, Resource> resources = new HashMap<>();
        for (ResourceFile resFile : info.getFiles()) {
            if (resFile.isSingle()) {
                Resource resource = loadSingleResource(resFile, outputClass, info.getMixins());
                resources.put(namespace + "." + resFile.getKey(), resource);
            } else {
                Map<String, Resource> loaded = loadMultiResource(resFile, outputClass, info.getMixins());
                for (Entry<String, Resource> entry : loaded.entrySet()) {
                    resources.put(namespace + "." + entry.getKey(), entry.getValue());
                }
            }
        }

        return resources;
    }

    public static Resource loadSingleResource(
            ResourceFile file, Class<Resource> outputClass, List<ClassMixin> mixinClasses) {
        try {
            ObjectMapper mapper = getMapper(file.getType());
            for (ClassMixin mixinClass : mixinClasses) {
                mapper.addMixIn(mixinClass.getParent(), mixinClass.getMixin());
            }
            InputStream stream = new FileReader(file.getPath()).toStream();
            Resource resource = mapper.readValue(stream, outputClass);
            resource.initialize();
            return resource;
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Could not load resource file: " + file);
            LOGGER.log(Level.SEVERE, e.toString(), e);
        }
        return null;
    }

    public static Map<String, Resource> loadMultiResource(
            ResourceFile file, Class<Resource> outputClass, List<ClassMixin> mixinClasses) {
        Map<String, Resource> output = new HashMap<>();
        try {
            ObjectMapper mapper = getMapper(file.getType());
            for (ClassMixin mixinClass : mixinClasses) {
                mapper.addMixIn(mixinClass.getParent(), mixinClass.getMixin());
            }
            InputStream stream = new FileReader(file.getPath()).toStream();
            JsonNode tree = mapper.readTree(stream);

            Iterator<Entry<String, JsonNode>> nodes = tree.fields();
            while (nodes.hasNext()) {
                Entry<String, JsonNode> node = nodes.next();
                Resource resource = mapper.readValue(node.getValue().toString(), outputClass);
                resource.initialize();
                output.put(node.getKey(), resource);
            }

            return output;
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Could not load resource file: " + file);
            LOGGER.log(Level.SEVERE, e.toString(), e);
        }
        return output;
    }

    public static ObjectMapper getMapper(ResourceFile.FileType type) {
        switch (type) {
            case XML:
                return new XmlMapper();
            case YAML:
                return new ObjectMapper(new YAMLFactory());
            case JSON:
                return new ObjectMapper();
            default:
                throw new RuntimeException("File type not supported");
        }
    }
}
