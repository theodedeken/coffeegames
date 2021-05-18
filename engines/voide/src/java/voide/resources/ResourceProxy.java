package voide.resources;

import voide.debug.Representable;
import voide.debug.RepresentationBuilder;

public class ResourceProxy<T extends Resource> implements Representable {
    private String key;
    private Class<T> resourceType;
    private T resource;

    public ResourceProxy(String key, Class<T> resourceType) {
        this.key = key;
        this.resourceType = resourceType;
    }

    public T get() {
        if (resource == null) {
            resource = Resources.get().getResource(key, resourceType);
        }
        return resource;
    }

    public T hardGet() {
        return Resources.get().getResource(key, resourceType);
    }

    public ResourceProxy<T> copy() {
        return new ResourceProxy<>(key, resourceType);
    }

    public String getKey() {
        return key;
    }

    public String repr() {
        return String.format("ResourceProxy { %s --> %s }", key, resourceType.getName());
    }

    public String fullRepr() {
        return new RepresentationBuilder(getClass().getName()).field("key", key).field("resourceType", resourceType)
                .build();
    }

}