package spaxel.entity.behaviour.render.renderers;

import spaxel.entity.ComponentType;
import spaxel.entity.storage.age.AgeStorage;
import spaxel.entity.Entity;
import voide.math.VectorD;
import voide.render.RenderJob;

/**
 * Created by theod on 25-9-2017.
 */
public class ShipFragmentRenderer extends Renderer {
    private static final double FACTOR_MULT = 2.0;

    public void apply(RenderJob data, Entity entity) {
        AgeStorage ac = (AgeStorage) entity.getComponent(ComponentType.AGE);

        double factor = ac.getLife() * FACTOR_MULT / ac.getMaxLife();

        data.applyScale(new VectorD(factor, factor));
    }

}
