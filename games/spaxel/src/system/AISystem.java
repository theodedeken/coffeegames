package spaxel.system;

import java.util.Set;
import spaxel.entity.ComponentType;
import spaxel.entity.behaviour.ai.AIBehaviour;
import spaxel.engine.Engine;
import spaxel.entity.Entity;
import spaxel.system.SystemType;

/**
 * The AISystem is responsible for updating all entities with an AI component
 */
public class AISystem extends GameSystem {
	/**
	 * Create a new AISystem
	 */
	public AISystem() {
		super(SystemType.AI);
	}

	public void update() {
		Set<Entity> enemies = Engine.get().getNEntityStream().getEntities(ComponentType.AI);

		for (Entity e : enemies) {
			AIBehaviour aic = (AIBehaviour) e.getComponent(ComponentType.AI);

			aic.execute(e);
		}
	}

}
