package spaxel.system;

import java.util.Set;
import spaxel.Constants;
import spaxel.engine.Engine;
import spaxel.entity.EntityUtil;
import spaxel.entity.SpaxelComponent;
import spaxel.entity.behaviour.render.RenderBehaviour;
import spaxel.entity.storage.transformation.TransformationStorage;
import voide.entity.Entity;
import voide.graphics.renderable.Texture;
import voide.input.MouseWrapper;
import voide.math.VectorD;
import voide.render.MasterRenderer;
import voide.render.buffer.MasterBuffer;

/**
 * The RenderSystem is responsible for rendering each frame
 */
public class RenderSystem extends GameSystem {

    private static final double DIM_REDUCTION = 0.75;
    private static final double MOUSE_POS_REDUCTION = 0.5;

    private MasterBuffer bufferBuffer;

    private MasterRenderer master;

    /**
     * Create a new RenderSystem
     */
    public RenderSystem() {
        super(SystemType.RENDER);
        bufferBuffer =
            new MasterBuffer(
                voide.resources.Resources
                    .get()
                    .getResource("voide.packed_texture", Texture.class)
                    .getTextureId()
            );
        master =
            new MasterRenderer(Constants.GAME_WIDTH, Constants.GAME_HEIGHT);
    }

    /**
     * Update the mouse position and render a new frame
     */
    public void update() {
        MouseWrapper mouseWrapper = Engine.get().getMouseWrapper();
        bufferBuffer.clear();
        if (
            Engine.get().getEngineState() == Engine.EngineState.PLAY ||
            Engine.get().getEngineState() == Engine.EngineState.PAUSE
        ) {
            VectorD mousePos = new VectorD(
                mouseWrapper.getX(),
                mouseWrapper.getY()
            );
            VectorD difference = mousePos.diff(
                Engine.get().getGameState().getCursorFollow()
            );
            if (difference.length() > Constants.MOUSE_FOLLOW_CUTOFF) {
                difference =
                    difference.multiplicate(Constants.MOUSE_FOLLOW_MULTIPLIER);
            }
            Engine
                .get()
                .getGameState()
                .setCursorFollow(
                    Engine
                        .get()
                        .getGameState()
                        .getCursorFollow()
                        .sum(difference)
                );

            Entity player = EntityUtil.getPlayer(
                Engine.get().getEntityStream()
            );
            TransformationStorage playerPos = (TransformationStorage) player.getComponent(
                SpaxelComponent.TRANSFORMATION
            );

            Engine
                .get()
                .getGameState()
                .setScreenOffset(calculateScreenOffset(playerPos));
        }
        renderEntities();

        Engine.get().getCurrentUI().render(bufferBuffer);

        master.render(bufferBuffer);
    }

    /**
     * Calculate the screenOffset based on the player position
     */
    private static VectorD calculateScreenOffset(
        TransformationStorage playerPos
    ) {
        VectorD dim = new VectorD(Constants.GAME_WIDTH, Constants.GAME_HEIGHT);
        return dim
            .multiplicate(DIM_REDUCTION)
            .diff(
                Engine
                    .get()
                    .getGameState()
                    .getCursorFollow()
                    .multiplicate(MOUSE_POS_REDUCTION)
            )
            .diff(playerPos.getPosition());
    }

    /**
     * Render all entities with a Rendercomponent
     */
    public void renderEntities() {
        Set<Entity> toRender = Engine
            .get()
            .getEntityStream()
            .getEntitiesCopy(SpaxelComponent.RENDER);
        for (Entity ne : toRender) {
            ((RenderBehaviour) ne.getComponent(SpaxelComponent.RENDER)).render(
                    ne,
                    bufferBuffer
                );
        }
    }
}
