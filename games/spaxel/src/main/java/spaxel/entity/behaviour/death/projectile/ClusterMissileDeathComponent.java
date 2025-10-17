package spaxel.entity.behaviour.death.projectile;

import spaxel.Constants;
import spaxel.entity.SpaxelComponent;
import spaxel.entity.behaviour.death.DeathHandler;
import spaxel.entity.behaviour.death.DeathType;
import spaxel.entity.industry.ProjectileIndustry;
import spaxel.entity.storage.change.ChangeStorage;
import spaxel.entity.storage.move.MoveStorage;
import spaxel.entity.storage.transformation.TransformationStorage;
import voide.entity.Entity;
import voide.math.VectorD;

/**
 * Created by theod on 4-7-2017.
 */
public class ClusterMissileDeathComponent extends DeathHandler {

    private static final int MISSILE_SPLIT = 6;

    public ClusterMissileDeathComponent() {
        super(DeathType.CLUSTER_MISSILE);
    }

    public void die(Entity entity) {
        TransformationStorage pc = (TransformationStorage) entity.getComponent(
            SpaxelComponent.TRANSFORMATION
        );
        ProjectileIndustry pri = voide.resources.Resources
            .get()
            .getResource(
                "cluster_shrapnel_projectile_industry",
                ProjectileIndustry.class
            );
        double rot = 0;
        for (int i = 0; i < MISSILE_SPLIT; i++) {
            Entity projectile = pri.produce(pc.copy(), entity.getParent());
            MoveStorage pmc = (MoveStorage) projectile.getComponent(
                SpaxelComponent.MOVE
            );
            double dx = Math.sin(rot) * pmc.getSpeed();
            double dy = Math.cos(rot) * pmc.getSpeed();
            projectile.addComponent(
                new ChangeStorage(new VectorD(dx, dy), 0, 0)
            );
            entity.getStream().addEntity(projectile);
            rot += Constants.FULL_CIRCLE / MISSILE_SPLIT;
        }
    }
}
