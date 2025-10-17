package voide.graphics.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import voide.graphics.load.Image;
import voide.graphics.load.ImagePart;
import voide.math.VectorD;
import voide.resources.Resources;

public class ImagePacker {

    // texture packing
    private static final int DIM_BASE = 2;
    private static final int SIZE_4 = 4;
    private static final int SIZE_3 = 3;
    private static final int SIZE_2 = 2;

    public ImagePacker() {}

    private int fullSize(Map<Integer, List<ImageNode>> nodes) {
        return nodes
            .values()
            .stream()
            .map(List::size)
            .reduce(0, (Integer accSize, Integer size) -> accSize += size);
    }

    public PackingResult pack(Map<String, Image> images) {
        Map<Integer, List<ImageNode>> nodes = new HashMap<>();

        for (Entry<String, Image> entry : images.entrySet()) {
            ImageNode node = new ImageNode(entry.getKey(), entry.getValue());
            nodes.putIfAbsent(node.getDim(), new ArrayList<>());

            nodes.get(node.getDim()).add(node);
        }
        int currentDim = DIM_BASE;
        while (this.fullSize(nodes) > 1) {
            if (nodes.containsKey(currentDim)) {
                List<ImageNode> dimNodes = nodes.get(currentDim);
                int size = dimNodes.size() > SIZE_3 ? SIZE_4 : dimNodes.size();
                ImageNode newNode = new ImageNode(currentDim * DIM_BASE);
                switch (size) {
                    case SIZE_4:
                        newNode.setTopLeft(
                            dimNodes.remove(dimNodes.size() - 1)
                        );
                    case SIZE_3:
                        newNode.setTopRight(
                            dimNodes.remove(dimNodes.size() - 1)
                        );
                    case SIZE_2:
                        newNode.setBotLeft(
                            dimNodes.remove(dimNodes.size() - 1)
                        );
                    case 1:
                        newNode.setBotRight(
                            dimNodes.remove(dimNodes.size() - 1)
                        );
                    default:
                        break;
                }
                nodes.putIfAbsent(currentDim * DIM_BASE, new ArrayList<>());
                nodes.get(currentDim * DIM_BASE).add(newNode);
                if (dimNodes.isEmpty()) {
                    nodes.remove(currentDim);
                }
            } else {
                currentDim *= DIM_BASE;
            }
        }
        ImageNode root = nodes.get(currentDim * DIM_BASE).get(0);

        Image result = new Image(
            new VectorD(root.getDim(), root.getDim()),
            root.loadImageTree()
        );

        String name = "voide.packed_image";

        Resources.get().addResource(name, result);

        return new PackingResult(result, root.toImageParts(name));
    }

    public class PackingResult {

        public Image rootImage;
        public Map<String, ImagePart> parts;

        public PackingResult(Image rootTexture, Map<String, ImagePart> parts) {
            this.rootImage = rootTexture;
            this.parts = parts;
        }
    }
}
