package spaxel.entity.behaviour.hit;

import spaxel.entity.SpaxelComponent;
import spaxel.entity.industry.EffectIndustry;
import spaxel.entity.industry.SpawnerIndustry;
import spaxel.entity.storage.damage.Damage;
import spaxel.entity.storage.damage.DamageStorage;
import spaxel.entity.storage.projectile.ProjectileStorage;
import spaxel.entity.storage.renderable.RenderableStorage;
import spaxel.entity.storage.transformation.TransformationStorage;
import voide.entity.Entity;
import voide.entity.EntityIndustry;
import voide.graphics.renderable.Texture;
import voide.random.RandomTexture;

public abstract class ProjectileHandler extends HitHandler {
    public ProjectileHandler(HitType type) {
        super(type);
    }

    public abstract void payload(Entity entity, Entity victim, ProjectileStorage projStore);

    public void hit(Entity entity, Entity victim) {
        if (victim.hasComponent(SpaxelComponent.DAMAGE) && victim != entity.getParent()) {
            ProjectileStorage projStore = (ProjectileStorage) entity.getComponent(SpaxelComponent.PROJECTILE);
            dealDamage(victim, projStore.getDamage());
            addParticleSpawner(entity, victim, projStore.getOnHitSpawner(), projStore.getParticleSize());
            payload(entity, victim, projStore);
        }
    }

    public void dealDamage(Entity victim, int damage) {
        DamageStorage dc = (DamageStorage) victim.getComponent(SpaxelComponent.DAMAGE);
        dc.addDamage(new Damage(damage));
    }

    public void addParticleSpawner(Entity entity, Entity victim, EntityIndustry particleSpawner, int particleSize) {
        RenderableStorage rndrStore = (RenderableStorage) victim.getComponent(SpaxelComponent.RENDERABLE);
        SpawnerIndustry hpsi = (SpawnerIndustry) particleSpawner;
        RenderableStorage particle = new RenderableStorage(
                RandomTexture.getRandomPart((Texture) rndrStore.getRenderable(), particleSize, particleSize));
        entity.getStream().addEntity(hpsi
                .produce((TransformationStorage) entity.getComponent(SpaxelComponent.TRANSFORMATION).copy(), particle));
    }

    public void addEffect(Entity victim, EntityIndustry effectIndustry) {
        EffectIndustry efi = (EffectIndustry) effectIndustry;
        Entity effect = efi.produce();
        victim.addLink(effect);
        victim.getStream().addEntity(effect);
    }
}
