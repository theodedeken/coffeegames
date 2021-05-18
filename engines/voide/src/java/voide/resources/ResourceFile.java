package voide.resources;

import com.fasterxml.jackson.annotation.JsonCreator;

public class ResourceFile {
    private String path;
    private String key;
    private FileType type;
    private boolean single = false;

    public ResourceFile() {

    }

    @JsonCreator
    public ResourceFile(String path) {
        this.path = path;
        typecheck();
    }

    private void typecheck() {
        if (path.contains(".xml")) {
            type = FileType.XML;
        } else if (path.contains(".json")) {
            type = FileType.JSON;
        } else if (path.contains(".yml") || path.contains(".yaml")) {
            type = FileType.YAML;
        } else {
            throw new RuntimeException("File extension not supported");
        }
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
        typecheck();
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
        this.single = true;
    }

    public FileType getType() {
        return type;
    }

    public boolean isSingle() {
        return this.single;
    }

    public enum FileType {
        XML, JSON, YAML
    }
}
