package spaxel.entity.behaviour.spawn;

import spaxel.entity.Behaviour;
import spaxel.entity.ComponentType;
import spaxel.engine.Engine;
import spaxel.entity.Entity;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SpawnBehaviour extends Behaviour {
    private Spawner spawner;

    public SpawnBehaviour() {
        super(ComponentType.SPAWN);
    }

    public SpawnBehaviour(Spawner spawner) {
        super(ComponentType.SPAWN);
        this.spawner = spawner;
    }

    public void execute(Entity entity) {
        entity.getStream().addEntities(spawner.spawn(entity));

    }

    public SpawnBehaviour copy() {
        return new SpawnBehaviour(spawner);
    }

    /**
     * @return the spawner
     */
    public Spawner getSpawner() {
        return spawner;
    }

    /**
     * @param spawner the spawner to set
     */
    public void setSpawner(Spawner spawner) {
        this.spawner = spawner;
    }

    @JsonProperty("spawner")
    public void setSpawner(SpawnerType type) {
        this.spawner = Spawner.createSpawner(type);
    }

}
