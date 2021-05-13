package spaxel.graphics.buffer;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import spaxel.graphics.texture.PackedTexture;
import voide.render.RenderJob;

/**
 * Buffers all render jobs
 * 
 * Created by theod on 15/10/2016.
 */
public class MasterBuffer {
    private EnumMap<RenderLayer, Map<Integer, List<RenderJob>>> layers;

    /**
     * Create a new MasterBuffer and initialize the layer map
     */
    public MasterBuffer(PackedTexture masterTexture) {
        layers = new EnumMap<>(RenderLayer.class);
        for (RenderLayer l : RenderLayer.values()) {
            Map<Integer, List<RenderJob>> data = new HashMap<>();
            data.put(0, new ArrayList<>());
            data.put(masterTexture.getID(), new ArrayList<>());

            layers.put(l, data);
        }
    }

    /**
     * Add a new Render job to the MasterBuffer
     * 
     * @param layer the layer to render the job on
     * @param job   the renderjob to render
     */
    public void addNewRenderJob(RenderLayer layer, RenderJob job) {
        layers.get(layer).get(job.getSpriteSheetID()).add(job);
    }

    /**
     * Clear all jobs from the buffer
     */
    public void clear() {
        for (Map<Integer, List<RenderJob>> layer : layers.values()) {
            for (List<RenderJob> datalist : layer.values()) {
                datalist.clear();
            }
        }

    }

    /**
     * Get all jobs for a layer from the masterbuffer
     * 
     * @param layer the layer to get
     * 
     * @return all jobs
     */
    public Map<Integer, List<RenderJob>> getJobs(RenderLayer layer) {
        return layers.get(layer);
    }

}
