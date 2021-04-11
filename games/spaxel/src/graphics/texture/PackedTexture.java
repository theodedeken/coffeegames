package spaxel.graphics.texture;

import spaxel.math.VectorD;
import spaxel.util.TextureUtil;
import spaxel.graphics.buffer.RenderJob;
import spaxel.Constants;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
/**
 * Represents a texture tree packed together
 */
public class PackedTexture extends Renderable {
    private TextureNode root;
    private int id;

    private int fullSize(Map<Integer, List<TextureNode>> nodes) {
        return nodes.values().stream().map(List::size).reduce(0,
                (Integer accSize, Integer size) -> accSize += size);
    }

    /**
     * Create a new PackedTexture with the given tree root
     * 
     * @param root the root of the tree
     */
    public PackedTexture(Iterable<Texture> textures) {
        Map<Integer, List<TextureNode>> nodes = new HashMap<>();

        for (Texture texture : textures) {
            TextureNode node = new TextureNode(texture);
            nodes.putIfAbsent(node.getDim(), new ArrayList<>());

            nodes.get(node.getDim()).add(node);
        }
        int currentDim = Constants.DIM_BASE;
        while (this.fullSize(nodes) > 1) {
            if (nodes.containsKey(currentDim)) {
                List<TextureNode> dimNodes = nodes.get(currentDim);
                int size = dimNodes.size() > Constants.SIZE_3 ? Constants.SIZE_4 : dimNodes.size();
                TextureNode newNode = new TextureNode(currentDim * Constants.DIM_BASE);
                switch (size) {
                    case Constants.SIZE_4:
                        newNode.setTopLeft(dimNodes.remove(dimNodes.size() - 1));
                    case Constants.SIZE_3:
                        newNode.setTopRight(dimNodes.remove(dimNodes.size() - 1));
                    case Constants.SIZE_2:
                        newNode.setBotLeft(dimNodes.remove(dimNodes.size() - 1));
                    case 1:
                        newNode.setBotRight(dimNodes.remove(dimNodes.size() - 1));

                    default:
                        break;
                }
                nodes.putIfAbsent(currentDim * Constants.DIM_BASE, new ArrayList<>());
                nodes.get(currentDim * Constants.DIM_BASE).add(newNode);
                if (dimNodes.isEmpty()) {
                    nodes.remove(currentDim);
                }
            } else {
                currentDim *= Constants.DIM_BASE;
            }
        }
        this.root = nodes.get(currentDim * Constants.DIM_BASE).get(0);
    }

    /**
     * Initializes the coordinates for all the members of the tree
     */
    public void initializeCoordinates() {
        root.initializeCoordinates(getDim(), id);
    }

    /**
     * Load the data of this texture tree and generate a new texture with it.
     */
    public void load() {
        this.id = TextureUtil.createGPUTexture(root.getDim(), root.getDim(),
                root.loadTextureTree());
        this.dim = new VectorD(root.getDim(), root.getDim());
    }

    public int getID() {
        return id;
    }

    public void apply(RenderJob data) {
        data.setTexOffset(new float[] {0.0F, 0.0F, 1.0F, 1.0F});
        data.setSpriteSheetID(id);
        data.applyScale(dim);
    }

}
