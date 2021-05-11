package voide.resources;

import java.util.List;

public class ResourceNamespace {
    private String className;
    private List<String> files;

    public ResourceNamespace() {

    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<String> getFiles() {
        return files;
    }

    public void setFiles(List<String> files) {
        this.files = files;
    }

}
