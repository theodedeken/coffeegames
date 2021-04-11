package spaxel.entity.storage.stack;

import spaxel.entity.Storage;
import spaxel.entity.ComponentType;

/**
 * Created by theo on 16/06/17.
 */
public class StackStorage extends Storage {
    private int stacks;

    public StackStorage(int stacks) {
        super(ComponentType.STACK);
        this.stacks = stacks;
    }

    public int getStacks() {
        return stacks;
    }

    public void setStacks(int stacks) {
        this.stacks = stacks;
    }

    public StackStorage copy() {
        return new StackStorage(stacks);
    }
}
