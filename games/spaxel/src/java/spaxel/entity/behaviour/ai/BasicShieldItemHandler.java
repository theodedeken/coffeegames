package spaxel.entity.behaviour.ai;

import spaxel.entity.SpaxelComponent;
import spaxel.entity.storage.cooldown.CooldownStorage;
import spaxel.entity.storage.projectile.ProjectileStorage;
import spaxel.entity.storage.shield.ShieldStorage;
import voide.entity.Entity;

/**
 * Created by theo on 8/07/17.
 */
public class BasicShieldItemHandler extends ShieldHandler {

    public BasicShieldItemHandler() {
        super(AIType.BASIC_SHIELD);
    }

    public void handleProjectile(Entity entity, Entity projectile) {
        // TODO ((RenderComponent)
        // effect.getComponent(SpaxelComponent.RENDER)).setVisible(true);
        ProjectileStorage phc = (ProjectileStorage) projectile.getComponent(SpaxelComponent.PROJECTILE);
        ShieldStorage shldStore = (ShieldStorage) entity.getComponent(SpaxelComponent.SHIELD);

        if (phc.getDamage() < shldStore.getCurrentCapacity()) {
            shldStore.subCapacity(phc.getDamage());
            projectile.destroy();
        } else {
            CooldownStorage cc = (CooldownStorage) entity.getComponent(SpaxelComponent.COOLDOWN);
            phc.subDamage(shldStore.getCurrentCapacity());
            shldStore.resetCapacity();
            cc.startCooldown();
            // TODO ((RenderBehaviour)
            // effect.getComponent(SpaxelComponent.RENDER)).setVisible(false);
        }
    }
}
