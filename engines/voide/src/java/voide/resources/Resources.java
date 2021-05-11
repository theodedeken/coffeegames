package voide.resources;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public final class Resources {
    private static final Resources resources = new Resources();
    private Map<String, Resource> resourceMap;

    private Resources() {
        this.resourceMap = new HashMap<>();
    }

    public static Resources get() {
        return resources;
    }

    public void load(String resourceFile) {
        Map<String, ResourceNamespace> test = Loader.loadResourceMap(resourceFile);
        for (Entry<String, ResourceNamespace> entry : test.entrySet()) {
            resourceMap.putAll(Loader.loadResources(entry.getKey(), entry.getValue()));
        }
    }

    public <T extends Resource> T getResource(String key, Class<T> resourceClass) {
        return resourceClass.cast(resourceMap.get(key));
    }

    public <T extends Resource> Map<String, T> getNamespaceResources(String namespace, Class<T> resourceClass) {
        Map<String, T> output = new HashMap<>();
        for (Entry<String, Resource> resource : resourceMap.entrySet()) {
            if (resource.getKey().startsWith(namespace + ".")) {
                output.put(resource.getKey(), resourceClass.cast(resource.getValue()));
            }
        }
        return output;
    }
}
