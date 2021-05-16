package spaxel.entity.behaviour.spawn;

import java.util.List;

import voide.entity.Entity;

public abstract class Spawner {
    private SpawnerType type;

    public Spawner(SpawnerType type) {
        this.type = type;
    }

    public static Spawner createSpawner(SpawnerType type) {
        switch (type) {
            case STATIC:
                return new StaticSpawner();
            case RANDOM:
                return new RandomSpawner();
            default:
                throw new RuntimeException("No spawner with type " + type + " exists");
        }
    }

    public abstract List<Entity> spawn(Entity entity);

    /**
     * @return the type
     */
    public SpawnerType getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(SpawnerType type) {
        this.type = type;
    }

}
