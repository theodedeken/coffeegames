package spaxel.entity.behaviour.ai;

import voide.entity.Entity;

public abstract class AIHandler {
    private AIType type;

    public AIHandler(AIType type) {
        this.type = type;
    }

    public static AIHandler createAIHandler(AIType type) {
        switch (type) {
            case BASIC_ENEMY:
                return new BasicEnemyHandler();
            case HOMING_MISSILE:
                return new HomingMissileAIHandler();
            case DROPPED_ITEM:
                return new DroppedItemAIHandler();
            case BASIC_SHIELD:
                return new BasicShieldItemHandler();
            case FORCE_SHIELD:
                return new ForceShieldItemHandler();
            case ANTI_SHIELD:
                return new AntiShieldItemHandler();
            case ACTIVE_SHIELD:
                return new ActiveShieldHandler();
            case MARKER:
                return new MarkerHandler();
            default:
                throw new RuntimeException("AI Handler with type " + type + " does not exist");
        }
    }

    public abstract void execute(Entity entity);

    /**
     * @return the type
     */
    public AIType getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(AIType type) {
        this.type = type;
    }

}
