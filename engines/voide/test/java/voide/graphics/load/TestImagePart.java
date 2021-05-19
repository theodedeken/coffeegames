package voide.graphics.load;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import voide.math.VectorD;
import voide.resources.Resources;

public class TestImagePart {

    static Image root;
    static ImagePart part;
    static ImagePart subpart;

    @BeforeAll
    static void prepareResources() {
        root = new Image(new VectorD(100, 100), new int[100 * 100]);

        Resources.get().addResource("root", root);
        part = new ImagePart("root", new VectorD(20, 20), new VectorD(30, 30));
        Resources.get().addResource("part", part);
        subpart =
            new ImagePart("part", new VectorD(10, 10), new VectorD(10, 10));
        Resources.get().addResource("subpart", subpart);
    }

    @Test
    void TestTextureCoordinates() {
        VectorD rootOffset = root.getTextureOffset();
        VectorD rootShape = root.getTextureShape();
        assertVectorDEquals(rootOffset, new VectorD(0.0, 0.0));
        assertVectorDEquals(rootShape, new VectorD(1.0, 1.0));
        VectorD partOffset = part.getTextureOffset();
        VectorD partShape = part.getTextureShape();
        assertVectorDEquals(partOffset, new VectorD(0.2, 0.2));
        assertVectorDEquals(partShape, new VectorD(0.3, 0.3));
        VectorD subpartOffset = subpart.getTextureOffset();
        VectorD subpartShape = subpart.getTextureShape();
        assertVectorDEquals(subpartOffset, new VectorD(0.3, 0.3));
        assertVectorDEquals(subpartShape, new VectorD(0.1, 0.1));
    }

    void assertVectorDEquals(VectorD one, VectorD two) {
        assertEquals(one.getM(), two.getM());
        for (int i = 0; i < one.getM(); i++) {
            assertEquals(one.getValue(0), two.getValue(0), 1e-5);
        }
    }
}
