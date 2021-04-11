package spaxel.entity.behaviour.ai;

import spaxel.entity.ComponentType;
import spaxel.entity.storage.cooldown.CooldownStorage;
import spaxel.entity.storage.projectile.ProjectileStorage;
import spaxel.entity.storage.shield.ShieldStorage;
import spaxel.entity.storage.change.ChangeStorage;
import spaxel.entity.Entity;

/**
 * Created by theod on 11-7-2017.
 */
public class ForceShieldItemHandler extends ShieldHandler {
    public ForceShieldItemHandler() {
        super(AIType.FORCE_SHIELD);
    }

    public void handleProjectile(Entity entity, Entity projectile) {
        ProjectileStorage dmgStore =
                (ProjectileStorage) projectile.getComponent(ComponentType.PROJECTILE);
        ShieldStorage shldStore = (ShieldStorage) entity.getComponent(ComponentType.SHIELD);
        if (dmgStore.getDamage() < shldStore.getCurrentCapacity()) {
            shldStore.subCapacity(dmgStore.getDamage());
            ChangeStorage vc = (ChangeStorage) projectile.getComponent(ComponentType.CHANGE);
            vc.setPositionChange(vc.getPositionChange().multiplicate(-1));
        } else {
            CooldownStorage cc = (CooldownStorage) entity.getComponent(ComponentType.COOLDOWN);
            dmgStore.subDamage(shldStore.getCurrentCapacity());
            shldStore.resetCapacity();

            cc.startCooldown();
            // TODO ((RenderStorage) effect.getComponent(ComponentType.RENDER)).setVisible(false);
        }
    }
}
