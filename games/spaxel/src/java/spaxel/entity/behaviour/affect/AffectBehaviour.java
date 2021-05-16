package spaxel.entity.behaviour.affect;

import spaxel.entity.Behaviour;
import spaxel.entity.SpaxelComponent;
import voide.entity.Entity;

/**
 * Created by theod on 28-6-2017.
 */
public class AffectBehaviour extends Behaviour {
    private AffectHandler handler;

    public AffectBehaviour() {
        super(SpaxelComponent.AFFECT);
    }

    public AffectBehaviour(AffectHandler handler) {
        super(SpaxelComponent.AFFECT);
        this.handler = handler;
    }

    public void execute(Entity entity) {
        handler.affect(entity);
    }

    public AffectBehaviour copy() {
        return new AffectBehaviour(handler);
    }
}
