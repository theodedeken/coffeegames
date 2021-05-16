package spaxel.entity.behaviour.event;

import spaxel.entity.SpaxelComponent;
import spaxel.entity.industry.ProjectileIndustry;
import spaxel.entity.storage.cooldown.CooldownStorage;
import spaxel.entity.storage.move.MoveStorage;
import spaxel.entity.storage.shoot.ShootStorage;
import spaxel.entity.storage.transformation.TransformationStorage;
import spaxel.entity.storage.stack.StackStorage;
import spaxel.entity.storage.change.ChangeStorage;
import voide.entity.Entity;
import voide.math.VectorD;

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
        CooldownStorage cdStore = (CooldownStorage) entity.getComponent(SpaxelComponent.COOLDOWN);
        if (cdStore.getCurrentCooldown() == 0) {
            Entity parent = entity.getParent();
            TransformationStorage pc = (TransformationStorage) parent.getComponent(SpaxelComponent.TRANSFORMATION);
            StackStorage sc = (StackStorage) entity.getComponent(SpaxelComponent.STACK);
            ShootStorage shtStore = (ShootStorage) entity.getComponent(SpaxelComponent.SHOOT);
            ProjectileIndustry pri = shtStore.getProjectileFactory();

            double offset = (sc.getStacks() - 1) * -RADIAL_STEP;
            for (int i = 0; i <= (sc.getStacks() - 1); i++) {
                Entity projectile = pri.produce((TransformationStorage) pc.copy(), parent);
                MoveStorage pmc = (MoveStorage) projectile.getComponent(SpaxelComponent.MOVE);
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
