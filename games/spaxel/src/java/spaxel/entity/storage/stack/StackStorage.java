package spaxel.entity.storage.stack;

import spaxel.entity.SpaxelComponent;
import voide.entity.Storage;

/**
 * Created by theo on 16/06/17.
 */
public class StackStorage extends Storage {

    private int stacks;

    public StackStorage() {
        super(SpaxelComponent.STACK);
    }

    public StackStorage(int stacks) {
        super(SpaxelComponent.STACK);
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
