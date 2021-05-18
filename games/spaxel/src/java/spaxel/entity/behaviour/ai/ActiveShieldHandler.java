package spaxel.entity.behaviour.ai;

import spaxel.entity.SpaxelComponent;
import spaxel.entity.SpaxelEntity;
import spaxel.entity.storage.cooldown.CooldownStorage;
import spaxel.entity.storage.projectile.ProjectileStorage;
import spaxel.entity.storage.shield.ShieldStorage;
import voide.entity.Entity;

import java.util.Set;

/**
 * Created by theod on 11-7-2017.
 */
public class ActiveShieldHandler extends ShieldHandler {
    private static final int COOLDOWN_DIVISION = 4;

    public ActiveShieldHandler() {
        super(AIType.ACTIVE_SHIELD);
    }

    public void handleProjectile(Entity entity, Entity projectile) {
        // TODO ((RenderComponent)
        // effect.getComponent(SpaxelComponent.RENDER)).setVisible(true);
        Entity parent = entity.getParent();
        ShieldStorage shldStore = (ShieldStorage) entity.getComponent(SpaxelComponent.SHIELD);

        ProjectileStorage phc = (ProjectileStorage) projectile.getComponent(SpaxelComponent.PROJECTILE);
        if (phc.getDamage() < shldStore.getCurrentCapacity()) {
            shldStore.subCapacity(phc.getDamage());
            int cdReduction = phc.getDamage() / COOLDOWN_DIVISION;
            reduceCooldown(parent, cdReduction);
            projectile.destroy();
        } else {
            CooldownStorage cc = (CooldownStorage) entity.getComponent(SpaxelComponent.COOLDOWN);
            int cdReduction = (phc.getDamage() - shldStore.getCurrentCapacity()) / COOLDOWN_DIVISION;
            reduceCooldown(parent, cdReduction);
            phc.subDamage(shldStore.getCurrentCapacity());

            shldStore.resetCapacity();
            cc.startCooldown();
            // TODO ((RenderBehaviour)
            // effect.getComponent(SpaxelComponent.RENDER)).setVisible(false);
        }
    }

    private static void reduceCooldown(Entity parent, int cdReduction) {
        Set<Entity> items = parent.getLinks((e) -> e.getType() == SpaxelEntity.ITEM);
        for (Entity item : items) {
            CooldownStorage cc = (CooldownStorage) item.getComponent(SpaxelComponent.COOLDOWN);
            if (cc != null && cc.getCurrentCooldown() != 0) {
                cc.setCurrentCooldown(
                        cc.getCurrentCooldown() - cdReduction < 0 ? 0 : cc.getCurrentCooldown() - cdReduction);
            }
        }
    }
}
