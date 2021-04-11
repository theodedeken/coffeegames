package spaxel.ui.elements.logic;

import spaxel.ui.elements.Element;

/**
 * Implement an onClick behavior on an ui Element.
 * 
 * @see spaxel.ui.elements.Element
 */
public class OnClickLogic implements Logic {
    /**
     * Executes the method specified in the onClick property.
     * 
     * @param element The element with the onClick property.
     */
    public void execute(Element element) {
        if ("true".equals(element.getStyle().getProperty("visible", element.getState()))
                && element.getState().isRelease()) {
            String method = element.getStyle().getProperty("onClick", element.getState());
            String controller = element.getController();
            if (method != null && controller != null) {
                UIUtil.invokeClickMethod(controller, method);
            }
        }
    }
}
