package spaxel.entity.behaviour.death.actor;

import spaxel.entity.behaviour.death.DeathHandler;
import spaxel.entity.behaviour.death.DeathType;
import spaxel.engine.Engine;
import spaxel.entity.Entity;
import spaxel.ui.elements.UIType;
import spaxel.engine.Resources;

/**
 * Created by theo on 24/06/17.
 */
public class PlayerDeathComponent extends DeathHandler {
    public PlayerDeathComponent() {
        super(DeathType.PLAYER);
    }

    public void die(Entity entity) {
        // add particle effect
        // show game over
        // TODO revisit maybe
        Engine.get().stopGame();
        Engine.get().setCurrentUI(Resources.get().getUIS().get(UIType.GAME_OVER));
        Engine.get().setEngineState(Engine.EngineState.MENU);
    }

}
