package voide.resources;

import java.util.ArrayList;
import java.util.List;

public class ResourceNamespace {
    private String className;
    private List<ClassMixin> mixins;
    private List<ResourceFile> files;

    public ResourceNamespace() {
        mixins = new ArrayList<>();
        files = new ArrayList<>();
    }

    public String getClassName() {
        return className;
    }

    public Class<Resource> getClassType() {
        try {
            return (Class<Resource>) Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(String.format("Resource class (%s) does not exist!", className));
        }
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<ResourceFile> getFiles() {
        return files;
    }

    public void setFiles(List<ResourceFile> files) {
        this.files = files;
    }

    public void setMixins(List<ClassMixin> mixins) {
        this.mixins = mixins;
    }

    public List<ClassMixin> getMixins() {
        return mixins;
    }

}
