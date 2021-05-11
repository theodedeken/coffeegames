package spaxel.ui.elements.logic;

import spaxel.ui.elements.Element;

/**
 * Logic to execute a method every update of an ui element
 */
public class OnUpdateLogic implements Logic {
    public void execute(Element element) {
        String method = element.getStyle().getProperty("onUpdate", element.getState());
        String controller = element.getController();
        if (method != null && controller != null) {
            UIUtil.invokeUpdateMethod(controller, method, element);
        }
    }
}
