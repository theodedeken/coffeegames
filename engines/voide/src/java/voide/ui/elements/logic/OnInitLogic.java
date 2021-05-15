package voide.ui.elements.logic;

import voide.ui.elements.Element;

/**
 * Implement an onInit behavior on an ui Element.
 * 
 * @see voide.ui.elements.Element
 */
public class OnInitLogic implements Logic {
    /**
     * Executes the method specified in the onInit property.
     * 
     * @param element The element with the onInit property.
     */
    public void execute(Element element) {
        if ("true".equals(element.getStyle().getProperty("visible", element.getState()))
                && !element.getState().isInit()) {
            String method = element.getStyle().getProperty("onInit", element.getState());
            String controller = element.getController();
            if (method != null && controller != null) {
                Element creation = UIUtil.invokeInitMethod(controller, method);

                element.addElement(creation);
                creation.initStyle(element.getStylesheets());
            }
            element.getState().setInit(true);
        }
    }
}
