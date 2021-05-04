package spaxel.entity.behaviour.event;

import spaxel.entity.ComponentType;
import spaxel.entity.storage.cooldown.CooldownStorage;
import spaxel.entity.storage.move.MoveStorage;
import spaxel.entity.storage.shoot.ShootStorage;
import spaxel.entity.storage.transformation.TransformationStorage;
import spaxel.entity.storage.stack.StackStorage;
import spaxel.entity.storage.change.ChangeStorage;
import spaxel.engine.Engine;
import spaxel.entity.Entity;
import spaxel.factories.entities.ProjectileIndustry;
import voide.math.VectorD;
import spaxel.engine.Resources;

/**
 * Created by theod on 28-6-2017.
 */
public class ShootHandler extends EventHandler {
    private static final double RADIAL_STEP = 0.05D;
    private static final double RADIAL_OFFSET = 2 * RADIAL_STEP;

    public ShootHandler() {
        super(EventHandlerType.SHOOT);
    }

    public void activate(Entity entity) {
        CooldownStorage cdStore = (CooldownStorage) entity.getComponent(ComponentType.COOLDOWN);
        if (cdStore.getCurrentCooldown() == 0) {
            Entity parent = entity.getParent();
            TransformationStorage pc = (TransformationStorage) parent.getComponent(ComponentType.TRANSFORMATION);
            StackStorage sc = (StackStorage) entity.getComponent(ComponentType.STACK);
            ShootStorage shtStore = (ShootStorage) entity.getComponent(ComponentType.SHOOT);
            ProjectileIndustry pri = (ProjectileIndustry) Resources.get().getIndustryMap()
                    .get(shtStore.getProjectileFactory());

            double offset = (sc.getStacks() - 1) * -RADIAL_STEP;
            for (int i = 0; i <= (sc.getStacks() - 1); i++) {
                Entity projectile = pri.produce((TransformationStorage) pc.copy(), parent);
                MoveStorage pmc = (MoveStorage) projectile.getComponent(ComponentType.MOVE);
                double dx = Math.sin(pc.getRotation() + offset) * pmc.getSpeed();
                double dy = Math.cos(pc.getRotation() + offset) * pmc.getSpeed();
                projectile.addComponent(new ChangeStorage(new VectorD(dx, dy), 0, 0));
                entity.getStream().addEntity(projectile);
                offset += RADIAL_OFFSET;
            }
            cdStore.startCooldown();
        }
    }
}
