package spaxel.entity.behaviour.event;

import voide.entity.Entity;

public abstract class EventHandler {

    private EventHandlerType type;

    public EventHandler(EventHandlerType type) {
        this.type = type;
    }

    public abstract void activate(Entity entity);

    /**
     * @return the type
     */
    public EventHandlerType getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(EventHandlerType type) {
        this.type = type;
    }
}
