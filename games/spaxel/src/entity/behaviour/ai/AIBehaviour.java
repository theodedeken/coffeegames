package spaxel.entity.behaviour.ai;

import spaxel.entity.Behaviour;
import spaxel.entity.ComponentType;
import spaxel.entity.Entity;

/**
 * Created by theo on 7/06/17.
 */
public class AIBehaviour extends Behaviour {
    private AIHandler handler;

    public AIBehaviour() {
        super(ComponentType.AI);
    }

    public AIBehaviour(AIHandler handler) {
        super(ComponentType.AI);
        this.handler = handler;
    }

    public void execute(Entity entity) {
        handler.execute(entity);
    }

    public AIBehaviour copy() {
        return new AIBehaviour(handler);
    }

    public void setHandler(AIType handler) {
        this.handler = AIHandler.createAIHandler(handler);
    }
}
