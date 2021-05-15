package voide.resources;

public class ResourceProxy<T extends Resource> {
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
}