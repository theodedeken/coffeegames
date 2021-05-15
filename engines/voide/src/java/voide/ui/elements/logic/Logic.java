package voide.ui.elements.logic;

import voide.ui.elements.Element;

/**
 * Interface to implement logic on ui elements, such as onClick handlers, hover
 * actions and more
 */
public interface Logic {

    /**
     * Perform logic on a ui element.
     * 
     * @param element The element that gets updated
     */
    void execute(Element element);
}
