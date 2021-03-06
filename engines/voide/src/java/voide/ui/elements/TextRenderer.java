package voide.ui.elements;

import java.util.ArrayList;
import java.util.List;
import voide.graphics.renderable.Renderable;
import voide.math.VectorD;
import voide.render.buffer.MasterBuffer;
import voide.render.buffer.RenderJob;
import voide.render.buffer.RenderLayer;

/**
 * Provides methods for rendering text
 */
public class TextRenderer {

    private static final String SPACE = " ";
    private static final String NEWLINE = "\\\\";
    private static final String L_BRACKET = "{";
    private static final String R_BRACKET = "}";

    private static final int SPACING = 10;
    private static final int TWO = 2;
    private static final int NEWLINE_OFFSET = 16;

    public TextRenderer() {}

    /**
     * Render the text according to the given style
     *
     * @param position the position of the text
     * @param style    the style of the text
     * @param buffer   the master buffer of the game
     */
    public void renderText(
        VectorD position,
        Style style,
        State currentState,
        MasterBuffer buffer
    ) {
        String text = style.getProperty("text", currentState);
        double scale = Double.parseDouble(
            style.getProperty("text-scale", currentState)
        );
        boolean alignLeft =
            "left".equals(style.getProperty("align", currentState));

        String[] lines = text.split(NEWLINE);
        VectorD offset = new VectorD(0, 0);
        for (String line : lines) {
            List<Character> characters = getCharacters(line);
            if (!alignLeft) {
                offset.setValue(0, calculateOffset(characters) * scale);
            }

            for (Character character : characters) {
                VectorD charOffset = offset.sum(
                    new VectorD(character.getWidth() * scale / TWO, 0)
                );
                character.render(position.sum(charOffset), scale, buffer);
                offset.setValue(
                    0,
                    offset.getValue(0) + character.getWidth() * scale
                );
            }
            offset.setValue(1, offset.getValue(1) - NEWLINE_OFFSET * scale);
        }
    }

    private List<Character> getCharacters(String line) {
        List<Character> characters = new ArrayList<>();
        int i = 0;
        voide.resources.Resources resources = voide.resources.Resources.get();
        while (i < line.length()) {
            String c = line.substring(i, i + 1);
            if (c.equals(L_BRACKET)) {
                int start = i + 1;
                while (!c.equals(R_BRACKET)) {
                    i++;
                    c = line.substring(i, i + 1);
                }
                characters.add(
                    new Character(
                        resources.getResource(
                            line.substring(start, i),
                            Renderable.class
                        )
                    )
                );
            } else if (c.equals(SPACE)) {
                characters.add(new Character(SPACING));
            } else {
                characters.add(
                    new Character(
                        resources.getResource("texture." + c, Renderable.class)
                    )
                );
            }
            i++;
        }
        return characters;
    }

    private double calculateOffset(List<Character> line) {
        return -calculateLineWidth(line) / TWO;
    }

    private double calculateLineWidth(List<Character> line) {
        return line
            .stream()
            .map(Character::getWidth)
            .reduce(0., (Double accWidth, Double width) -> accWidth += width);
    }

    private class Character {

        private double width;
        private Renderable sprite;

        public Character(Renderable sprite) {
            this.sprite = sprite;
            this.width = sprite.getShape().getValue(0);
        }

        public Character(double width) {
            this.width = width;
        }

        public double getWidth() {
            return width;
        }

        /**
         * Renders this character at the specified position with the specified scale
         *
         * @param position the position of the character
         * @param scale    the scale of the character
         * @param buffer   the master buffer of the game
         */
        public void render(
            VectorD position,
            double scale,
            MasterBuffer buffer
        ) {
            if (sprite != null) {
                RenderJob data = new RenderJob();
                data.applyTranslation(position);
                data.applyRot(0);
                data.applyScale(scale);
                sprite.apply(data);
                buffer.addNewRenderJob(RenderLayer.UI, data);
            }
        }
    }
}
