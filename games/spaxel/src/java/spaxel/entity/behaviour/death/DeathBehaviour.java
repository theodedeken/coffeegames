package spaxel.entity.behaviour.death;

import spaxel.entity.Behaviour;
import spaxel.entity.SpaxelComponent;
import voide.entity.Entity;

public class DeathBehaviour extends Behaviour {
    private DeathHandler handler;

    public DeathBehaviour() {
        super(SpaxelComponent.DEATH);
    }

    public DeathBehaviour(DeathHandler handler) {
        super(SpaxelComponent.DEATH);
        this.handler = handler;
    }

    public void execute(Entity entity) {
        handler.die(entity);
    }

    /**
     * @return the handler
     */
    public DeathHandler getHandler() {
        return handler;
    }

    public void setHandler(DeathType type) {
        this.handler = DeathHandler.createDeathHandler(type);
    }

    public DeathBehaviour copy() {
        return new DeathBehaviour(handler);
    }
}