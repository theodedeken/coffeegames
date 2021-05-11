package spaxel.entity.behaviour.keyboard;

import spaxel.entity.Entity;
import voide.input.Keyboard;

public abstract class KeyboardHandler {
    private KeyboardHandlerType type;

    public KeyboardHandler(KeyboardHandlerType type) {
        this.type = type;
    }

    public abstract void handle(Entity entity, Keyboard keyboard);

    /**
     * @return the type
     */
    public KeyboardHandlerType getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(KeyboardHandlerType type) {
        this.type = type;
    }

}
