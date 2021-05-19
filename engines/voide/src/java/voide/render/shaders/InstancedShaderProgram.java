package voide.render.shaders;

import static org.lwjgl.opengl.GL20.glGetUniformLocation;
import static org.lwjgl.opengl.GL20.glUniform1i;
import static org.lwjgl.opengl.GL20.glUniformMatrix4fv;

import voide.math.MatrixD;

/**
 * Shader program used to render instanced elements
 *
 * Created by theod on 16-7-2017.
 */
public class InstancedShaderProgram extends ShaderProgram {

    private int texSamplerLocation;
    private int projectionMatrixLocation;

    /**
     * Create a new Shaderprogram using the code of the given shaders
     *
     * @param vertexShader   the path to the vertexShader
     * @param fragmentShader the path to the fragmentShader
     */
    public InstancedShaderProgram(String vertexShader, String fragmentShader) {
        super(vertexShader, fragmentShader);
        texSamplerLocation = glGetUniformLocation(program, "tex_sampler");
        projectionMatrixLocation =
            glGetUniformLocation(program, "projection_matrix");
    }

    public void setTexSampler(int sampler) {
        glUniform1i(texSamplerLocation, sampler);
    }

    public void setProjectionMatrix(MatrixD projectionMatrix) {
        glUniformMatrix4fv(
            projectionMatrixLocation,
            false,
            projectionMatrix.toFloatBuffer()
        );
    }
}
