package voide.resources;

import com.fasterxml.jackson.annotation.JsonCreator;

public class ResourceFile {
    private String path;
    private String key;
    private boolean xml = true;
    private boolean single = false;

    public ResourceFile() {

    }

    @JsonCreator
    public ResourceFile(String path) {
        this.path = path;
        this.xml = false;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
        this.xml = path.contains(".xml");
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
        this.single = true;
    }

    public boolean isXML() {
        return this.xml;
    }

    public boolean isSingle() {
        return this.single;
    }
}
