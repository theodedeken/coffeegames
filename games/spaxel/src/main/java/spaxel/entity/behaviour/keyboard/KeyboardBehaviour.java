package spaxel.entity.behaviour.keyboard;

import spaxel.engine.Engine;
import spaxel.entity.Behaviour;
import spaxel.entity.SpaxelComponent;
import voide.entity.Entity;

public class KeyboardBehaviour extends Behaviour {

    private KeyboardHandler handler;

    public KeyboardBehaviour() {
        super(SpaxelComponent.KEYBOARD);
    }

    public KeyboardBehaviour(KeyboardHandler handler) {
        super(SpaxelComponent.KEYBOARD);
        this.handler = handler;
    }

    public void execute(Entity entity) {
        handler.handle(entity, Engine.get().getKeyboard());
    }

    /**
     * @return the handler
     */
    public KeyboardHandler getHandler() {
        return handler;
    }

    /**
     * @param handler the handler to set
     */
    public void setHandler(KeyboardHandler handler) {
        this.handler = handler;
    }

    public KeyboardBehaviour copy() {
        return new KeyboardBehaviour(handler);
    }
}
