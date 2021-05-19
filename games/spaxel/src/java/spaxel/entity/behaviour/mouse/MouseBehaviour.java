package spaxel.entity.behaviour.mouse;

import spaxel.engine.Engine;
import spaxel.entity.Behaviour;
import spaxel.entity.SpaxelComponent;
import voide.entity.Entity;

public class MouseBehaviour extends Behaviour {

    private MouseHandler handler;

    public MouseBehaviour() {
        super(SpaxelComponent.MOUSE);
    }

    public MouseBehaviour(MouseHandler handler) {
        super(SpaxelComponent.MOUSE);
        this.handler = handler;
    }

    public void execute(Entity entity) {
        // TODO mouseStorage
        handler.handle(entity, Engine.get().getMouseWrapper());
    }

    /**
     * @return the handler
     */
    public MouseHandler getHandler() {
        return handler;
    }

    /**
     * @param handler the handler to set
     */
    public void setHandler(MouseHandler handler) {
        this.handler = handler;
    }

    public MouseBehaviour copy() {
        return new MouseBehaviour(handler);
    }
}
