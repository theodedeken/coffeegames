package spaxel.entity.behaviour.ai;

import spaxel.Constants;
import spaxel.entity.ComponentType;
import spaxel.entity.storage.render.RenderStorage;
import spaxel.entity.storage.transformation.TransformationStorage;
import spaxel.engine.Engine;
import spaxel.entity.Entity;
import voide.math.LineSegment;
import voide.math.VectorD;

/**
 * Created by theo on 5/01/18.
 */
public class MarkerHandler extends AIHandler {
        private static final int MARKER_THRESHOLD = 50;
        private static final int MARKER_OFFSET = 20;

        public MarkerHandler() {
                super(AIType.MARKER);
        }

        public void execute(Entity entity) {
                Entity player = entity.getStream().getPlayer();
                TransformationStorage playerPos = (TransformationStorage) player
                                .getComponent(ComponentType.TRANSFORMATION);
                TransformationStorage parentPos = (TransformationStorage) entity.getParent()
                                .getComponent(ComponentType.TRANSFORMATION);
                RenderStorage mrc = (RenderStorage) entity.getComponent(ComponentType.RENDER_STORE);
                VectorD renderPos = parentPos.getPosition().sum(Engine.get().getGameState().getScreenOffset());
                if (renderPos.getValue(0) < -MARKER_THRESHOLD
                                || renderPos.getValue(0) > Constants.GAME_WIDTH + MARKER_THRESHOLD
                                || renderPos.getValue(1) < -MARKER_THRESHOLD
                                || renderPos.getValue(1) > Constants.GAME_HEIGHT + MARKER_THRESHOLD) {
                        VectorD intersect = getIntersection(new VectorD(MARKER_OFFSET, MARKER_OFFSET),
                                        new VectorD(Constants.GAME_WIDTH - MARKER_OFFSET,
                                                        Constants.GAME_HEIGHT - MARKER_OFFSET),
                                        playerPos.getPosition().sum(Engine.get().getGameState().getScreenOffset()),
                                        renderPos);

                        if (intersect != null) {
                                mrc.setVisible(true);
                                VectorD diff = parentPos.getPosition().diff(playerPos.getPosition());
                                double rot = Math.atan2(diff.getValue(0), diff.getValue(1));
                                TransformationStorage mpc = (TransformationStorage) entity
                                                .getComponent(ComponentType.TRANSFORMATION);
                                mpc.setPosition(intersect);
                                mpc.setRotation(rot);
                        }
                } else {
                        mrc.setVisible(false);
                }
        }

        private static VectorD getIntersection(VectorD leftTop, VectorD rightBot, VectorD playerPos, VectorD enemyPos) {
                LineSegment toEnemy = new LineSegment(playerPos, enemyPos);
                LineSegment a = new LineSegment(leftTop, new VectorD(leftTop.getValue(0), rightBot.getValue(1)));
                LineSegment b = new LineSegment(new VectorD(leftTop.getValue(0), rightBot.getValue(1)), rightBot);
                LineSegment c = new LineSegment(rightBot, new VectorD(rightBot.getValue(0), leftTop.getValue(1)));
                LineSegment d = new LineSegment(new VectorD(rightBot.getValue(0), leftTop.getValue(1)), leftTop);
                LineSegment[] borders = { a, b, c, d };
                for (LineSegment border : borders) {
                        VectorD intersect = toEnemy.intersection(border);
                        if (intersect != null) {
                                return intersect;
                        }

                }
                return null;
        }
}
