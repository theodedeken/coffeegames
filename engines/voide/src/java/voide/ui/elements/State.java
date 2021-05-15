package voide.ui.elements;

import java.util.List;
import java.util.ArrayList;

/**
 * Represents the internal state of an ui Element.
 */
public class State {
    private boolean hover;
    private boolean click;
    private boolean release;

    private boolean init;

    /**
     * Create an new State object.
     */
    public State() {
        super();
    }

    /**
     * Reset the state
     */
    public void reset() {
        hover = false;
        click = false;
        release = false;
    }

    /**
     * @return the hover
     */
    public boolean isHover() {
        return hover;
    }

    /**
     * @param hover the hover to set
     */
    public void setHover(boolean hover) {
        this.hover = hover;
    }

    /**
     * @return the click
     */
    public boolean isClick() {
        return click;
    }

    /**
     * @param click the click to set
     */
    public void setClick(boolean click) {
        this.click = click;
    }

    /**
     * @return the release
     */
    public boolean isRelease() {
        return release;
    }

    /**
     * @param release the release to set
     */
    public void setRelease(boolean release) {
        this.release = release;
    }

    /**
     * @return the init
     */
    public boolean isInit() {
        return init;
    }

    /**
     * @param init the init to set
     */
    public void setInit(boolean init) {
        this.init = init;
    }

    public List<String> getModifiers() {
        List<String> modifiers = new ArrayList<>();
        if (click) {
            modifiers.add("click");
        }
        if (hover) {
            modifiers.add("hover");
        }

        return modifiers;
    }

}
