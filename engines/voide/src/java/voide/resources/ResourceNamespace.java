package voide.resources;

import java.util.List;

public class ResourceNamespace {
    private String className;
    private String mixin;
    private List<ResourceFile> files;

    public ResourceNamespace() {

    }

    public String getClassName() {
        return className;
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

    public void setMixin(String mixin) {
        this.mixin = mixin;
    }

    public String getMixin() {
        return mixin;
    }

}
