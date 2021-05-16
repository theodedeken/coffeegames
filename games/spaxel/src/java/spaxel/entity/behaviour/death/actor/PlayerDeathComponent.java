package spaxel.entity.behaviour.death.actor;

import spaxel.engine.Engine;
import spaxel.entity.behaviour.death.DeathHandler;
import spaxel.entity.behaviour.death.DeathType;
import spaxel.ui.UIType;
import voide.entity.Entity;
import voide.ui.UI;

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
        Engine.get().setCurrentUI(voide.resources.Resources.get().getResource(UIType.GAME_OVER.key(), UI.class));
        Engine.get().setEngineState(Engine.EngineState.MENU);
    }

}
