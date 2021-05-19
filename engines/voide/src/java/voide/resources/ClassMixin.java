/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */
package voide.resources;

public class ClassMixin {

    private String parent;
    private String mixin;

    public ClassMixin() {}

    /**
     * @return the parent
     */
    public Class<Resource> getParent() {
        try {
            return (Class<Resource>) Class.forName(parent);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(
                String.format("Resource class (%s) does not exist!", parent)
            );
        }
    }

    /**
     * @param parent the parent to set
     */
    public void setParent(String parent) {
        this.parent = parent;
    }

    /**
     * @return the mixin
     */
    public Class<Resource> getMixin() {
        try {
            return (Class<Resource>) Class.forName(mixin);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(
                String.format("Resource class (%s) does not exist!", mixin)
            );
        }
    }

    /**
     * @param mixin the mixin to set
     */
    public void setMixin(String mixin) {
        this.mixin = mixin;
    }
}
