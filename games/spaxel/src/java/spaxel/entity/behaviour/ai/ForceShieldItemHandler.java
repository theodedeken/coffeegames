package spaxel.entity.behaviour.ai;

import spaxel.entity.SpaxelComponent;
import spaxel.entity.storage.change.ChangeStorage;
import spaxel.entity.storage.cooldown.CooldownStorage;
import spaxel.entity.storage.projectile.ProjectileStorage;
import spaxel.entity.storage.shield.ShieldStorage;
import voide.entity.Entity;

/**
 * Created by theod on 11-7-2017.
 */
public class ForceShieldItemHandler extends ShieldHandler {

    public ForceShieldItemHandler() {
        super(AIType.FORCE_SHIELD);
    }

    public void handleProjectile(Entity entity, Entity projectile) {
        ProjectileStorage dmgStore = (ProjectileStorage) projectile.getComponent(
            SpaxelComponent.PROJECTILE
        );
        ShieldStorage shldStore = (ShieldStorage) entity.getComponent(
            SpaxelComponent.SHIELD
        );
        if (dmgStore.getDamage() < shldStore.getCurrentCapacity()) {
            shldStore.subCapacity(dmgStore.getDamage());
            ChangeStorage vc = (ChangeStorage) projectile.getComponent(
                SpaxelComponent.CHANGE
            );
            vc.setPositionChange(vc.getPositionChange().multiplicate(-1));
        } else {
            CooldownStorage cc = (CooldownStorage) entity.getComponent(
                SpaxelComponent.COOLDOWN
            );
            dmgStore.subDamage(shldStore.getCurrentCapacity());
            shldStore.resetCapacity();

            cc.startCooldown();
            // TODO ((RenderStorage)
            // effect.getComponent(SpaxelComponent.RENDER)).setVisible(false);
        }
    }
}
