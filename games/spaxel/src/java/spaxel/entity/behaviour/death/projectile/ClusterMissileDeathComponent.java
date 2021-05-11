package spaxel.entity.behaviour.death.projectile;

import spaxel.Constants;
import spaxel.entity.ComponentType;
import spaxel.entity.behaviour.death.DeathHandler;
import spaxel.entity.behaviour.death.DeathType;
import spaxel.entity.storage.move.MoveStorage;
import spaxel.entity.storage.transformation.TransformationStorage;
import spaxel.entity.storage.change.ChangeStorage;
import spaxel.engine.Engine;
import spaxel.entity.Entity;
import spaxel.factories.entities.ProjectileIndustry;
import voide.math.VectorD;
import spaxel.engine.Resources;

/**
 * Created by theod on 4-7-2017.
 */
public class ClusterMissileDeathComponent extends DeathHandler {
    private static final int MISSILE_SPLIT = 6;

    public ClusterMissileDeathComponent() {
        super(DeathType.CLUSTER_MISSILE);
    }

    public void die(Entity entity) {
        TransformationStorage pc = (TransformationStorage) entity.getComponent(ComponentType.TRANSFORMATION);
        ProjectileIndustry pri = (ProjectileIndustry) Resources.get().getIndustryMap()
                .get("cluster_shrapnel_projectile_industry");
        double rot = 0;
        for (int i = 0; i < MISSILE_SPLIT; i++) {
            Entity projectile = pri.produce(pc.copy(), entity.getParent());
            MoveStorage pmc = (MoveStorage) projectile.getComponent(ComponentType.MOVE);
            double dx = Math.sin(rot) * pmc.getSpeed();
            double dy = Math.cos(rot) * pmc.getSpeed();
            projectile.addComponent(new ChangeStorage(new VectorD(dx, dy), 0, 0));
            entity.getStream().addEntity(projectile);
            rot += Constants.FULL_CIRCLE / MISSILE_SPLIT;
        }
    }
}
