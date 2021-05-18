package spaxel.entity.behaviour.render;

import spaxel.entity.SpaxelComponent;
import spaxel.entity.behaviour.render.renderers.Renderer;
import spaxel.entity.storage.render.RenderStorage;
import voide.entity.Component;
import voide.entity.Entity;
import voide.render.buffer.MasterBuffer;
import voide.render.buffer.RenderJob;

import java.util.List;
import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by theo on 4/06/17.
 */
public class RenderBehaviour extends Component {
    private List<Renderer> renderers;

    public RenderBehaviour() {
        super(SpaxelComponent.RENDER);
    }

    public RenderBehaviour(List<Renderer> renderers) {
        super(SpaxelComponent.RENDER);
        this.renderers = renderers;
    }

    public void render(Entity entity, MasterBuffer buffer) {
        RenderStorage rndrStore = (RenderStorage) entity.getComponent(SpaxelComponent.RENDER_STORE);

        if (rndrStore.isVisible()) {
            RenderJob renderJob = new RenderJob();
            for (Renderer r : renderers) {
                r.apply(renderJob, entity);
            }
            buffer.addNewRenderJob(rndrStore.getLayer(), renderJob);
        }
    }

    public List<Renderer> getRenderers() {
        return renderers;
    }

    public void setRenderers(List<Renderer> renderers) {
        this.renderers = renderers;
    }

    @JsonProperty("renderers")
    public void setRenderersRaw(List<String> renderers) {
        this.renderers = new ArrayList<>();
        for (String renderString : renderers) {
            this.renderers.add(Renderer.createRenderer(renderString));
        }
    }

    public void initRenderers(List<String> rendererNames) {
        renderers = new ArrayList<>();
        for (String name : rendererNames) {
            renderers.add(Renderer.createRenderer(name));
        }
    }

    public Component copy() {
        return new RenderBehaviour(new ArrayList<>(renderers));
    }
}
