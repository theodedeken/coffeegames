package voide.input;

import static org.lwjgl.glfw.GLFW.*;

/**
 * Represents the state of a single key
 * 
 * Created by theod on 6-11-2016.
 */
public class KeyState {
    private boolean down;
    private boolean beenDown;
    private int code;

    /**
     * Create a new KeyState
     */
    public KeyState() {
        super();
    }

    /**
     * Updates the state of the key
     * 
     * @param window the window in which the key lives
     */
    public void update(long window) {
        setState(glfwGetKey(window, code) == GLFW_PRESS);
    }

    public void setState(boolean down) {
        beenDown = this.down;
        this.down = down;
    }

    public boolean isDown() {
        return down;
    }

    /**
     * Get the value of beenDown
     * 
     * @return the value of beenDown
     */
    public boolean hasBeenDown() {
        return beenDown;
    }

    public boolean isRelease() {
        return beenDown && !down;
    }

    public boolean isPress() {
        return !beenDown && down;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
