package spaxel.ui.elements.logic;

import spaxel.ui.elements.Element;

/**
 * Logic to set the click property of an ui element
 */
public class ClickLogic implements Logic {

    public void execute(Element element) {
        element.getState().setClick(element.getState().isHover()
                && element.getMouse().getMouse1().isDown());
    }
}
