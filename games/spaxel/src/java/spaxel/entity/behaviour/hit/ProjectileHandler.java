package spaxel.entity.behaviour.hit;

import spaxel.entity.ComponentType;
import spaxel.entity.storage.damage.Damage;
import spaxel.entity.storage.damage.DamageStorage;
import spaxel.entity.storage.projectile.ProjectileStorage;
import spaxel.entity.storage.renderable.RenderableStorage;
import spaxel.entity.storage.transformation.TransformationStorage;
import spaxel.engine.Engine;
import spaxel.engine.Resources;
import spaxel.entity.Entity;
import spaxel.factories.entities.EffectIndustry;
import spaxel.factories.entities.SpawnerIndustry;
import spaxel.util.SpriteDataUtil;
import voide.graphics.renderable.Texture;

public abstract class ProjectileHandler extends HitHandler {
    public ProjectileHandler(HitType type) {
        super(type);
    }

    public abstract void payload(Entity entity, Entity victim, ProjectileStorage projStore);

    public void hit(Entity entity, Entity victim) {
        if (victim.hasComponent(ComponentType.DAMAGE) && victim != entity.getParent()) {
            ProjectileStorage projStore = (ProjectileStorage) entity.getComponent(ComponentType.PROJECTILE);
            dealDamage(victim, projStore.getDamage());
            addParticleSpawner(entity, victim, projStore.getOnHitSpawner(), projStore.getParticleSize());
            payload(entity, victim, projStore);
        }
    }

    public void dealDamage(Entity victim, int damage) {
        DamageStorage dc = (DamageStorage) victim.getComponent(ComponentType.DAMAGE);
        dc.addDamage(new Damage(damage));
    }

    public void addParticleSpawner(Entity entity, Entity victim, String particleSpawner, int particleSize) {
        RenderableStorage rndrStore = (RenderableStorage) victim.getComponent(ComponentType.RENDERABLE);
        SpawnerIndustry hpsi = (SpawnerIndustry) Resources.get().getIndustryMap().get(particleSpawner);
        RenderableStorage particle = new RenderableStorage(
                SpriteDataUtil.getRandomPart((Texture) rndrStore.getRenderable(), particleSize, particleSize));
        entity.getStream().addEntity(hpsi
                .produce((TransformationStorage) entity.getComponent(ComponentType.TRANSFORMATION).copy(), particle));
    }

    public void addEffect(Entity victim, String effectIndustry) {
        EffectIndustry efi = (EffectIndustry) Resources.get().getIndustryMap().get(effectIndustry);
        Entity effect = efi.produce();
        victim.addLink(effect);
        victim.getStream().addEntity(effect);
    }
}
