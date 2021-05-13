package spaxel.ui.elements.logic;

import voide.collision.HitShape;
import voide.collision.HitPoint;
import voide.input.MouseWrapper;
import voide.math.VectorD;
import voide.math.MatrixD;
import spaxel.ui.elements.Element;
import spaxel.ui.styles.Style;
import spaxel.util.MatrixUtil;
import java.util.Map;

/**
 * Logic to set the hover property of an element
 */
public class HoverLogic implements Logic {

    public void execute(Element element) {
        Style style = element.getStyle();
        if (style.contains("hit-shape", element.getState())) {
            HitShape hitbox = voide.resources.Resources.get()
                    .getResource(style.getProperty("hit-shape", element.getState()), HitShape.class);
            double x = Double.parseDouble(style.getProperty("x", element.getState()));
            double y = Double.parseDouble(style.getProperty("y", element.getState()));
            double rot = Double.parseDouble(style.getProperty("rot", element.getState()));
            double width = Double.parseDouble(style.getProperty("width", element.getState()));
            double height = Double.parseDouble(style.getProperty("height", element.getState()));

            MatrixD transform = MatrixUtil.getTransformationMatrix(new VectorD(x, y), rot, new VectorD(width, height));

            MouseWrapper mouse = element.getMouse();
            int mouseX = mouse.getX();
            int mouseY = mouse.getY();

            HitShape updated = hitbox.update(transform);
            boolean inside = updated
                    .collision(new HitShape(new HitPoint(new VectorD(new double[] { mouseX, mouseY, 0 }))));

            element.getState().setHover(inside);
        }

    }
}
