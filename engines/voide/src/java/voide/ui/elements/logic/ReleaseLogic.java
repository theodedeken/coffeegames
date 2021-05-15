package voide.ui.elements.logic;

import voide.ui.elements.Element;

/**
 * Logic to set the release property of an ui element
 */
public class ReleaseLogic implements Logic {
    public void execute(Element element) {
        element.getState().setRelease(element.getState().isHover() && element.getMouse().getMouse1().isRelease());
    }
}
