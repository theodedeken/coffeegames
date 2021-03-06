package spaxel.entity.behaviour.ai;

import spaxel.entity.SpaxelComponent;
import spaxel.entity.storage.cooldown.CooldownStorage;
import spaxel.entity.storage.health.HealthStorage;
import spaxel.entity.storage.projectile.ProjectileStorage;
import spaxel.entity.storage.shield.ShieldStorage;
import voide.entity.Entity;

/**
 * Created by theod on 11-7-2017.
 */
public class AntiShieldItemHandler extends ShieldHandler {

    private static final int HEAL_DIVISION = 4;

    public AntiShieldItemHandler() {
        super(AIType.ANTI_SHIELD);
    }

    public void handleProjectile(Entity entity, Entity projectile) {
        // TODO ((RenderComponent)
        // effect.getComponent(SpaxelComponent.RENDER)).setVisible(true);
        Entity parent = entity.getParent();
        ProjectileStorage phc = (ProjectileStorage) projectile.getComponent(
            SpaxelComponent.PROJECTILE
        );
        HealthStorage hc = (HealthStorage) parent.getComponent(
            SpaxelComponent.HEALTH
        );
        ShieldStorage shldStore = (ShieldStorage) entity.getComponent(
            SpaxelComponent.SHIELD
        );

        if (phc.getDamage() < shldStore.getCurrentCapacity()) {
            shldStore.subCapacity(phc.getDamage());
            int healthGain = phc.getDamage() / HEAL_DIVISION;
            hc.setCurrentHealth(
                hc.getCurrentHealth() + healthGain > hc.getMaxHealth()
                    ? hc.getMaxHealth()
                    : hc.getCurrentHealth() + healthGain
            );
            projectile.destroy();
        } else {
            CooldownStorage cc = (CooldownStorage) entity.getComponent(
                SpaxelComponent.COOLDOWN
            );
            int healthGain =
                (phc.getDamage() - shldStore.getCurrentCapacity()) /
                HEAL_DIVISION;
            hc.setCurrentHealth(
                hc.getCurrentHealth() + healthGain > hc.getMaxHealth()
                    ? hc.getMaxHealth()
                    : hc.getCurrentHealth() + healthGain
            );
            phc.subDamage(shldStore.getCurrentCapacity());
            shldStore.resetCapacity();
            cc.startCooldown();
            // TODO ((RenderBehaviour)
            // effect.getComponent(SpaxelComponent.RENDER)).setVisible(false);
        }
    }
}
