package spaxel.graphics.buffer;

import spaxel.graphics.postprocess.FBO;
import java.util.EnumMap;

/**
 * Holds an FBO for each RenderLayer
 * 
 * Created by theod on 18-9-2017.
 */
public class LayerFBO {
    private EnumMap<RenderLayer, FBO> fbos;

    /**
     * Create a new LayerFBO
     */
    public LayerFBO() {
        fbos = new EnumMap<>(RenderLayer.class);
        for (RenderLayer layer : RenderLayer.values()) {
            fbos.put(layer, new FBO());
        }
    }

    /**
     * Get the FBO of the corresponding layer
     * 
     * @param layer the layer to get
     * 
     * @return the FBO for this layer
     */
    public FBO getFbo(RenderLayer layer) {
        return fbos.get(layer);
    }
}
