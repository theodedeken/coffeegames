package voide.ui.elements.logic;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Logger;

import voide.ui.elements.Element;

import java.util.logging.Level;

/**
 * Provides functions for invoking methods in the ui Elements
 */
public final class UIUtil {
    // The logger to log errors in the dynamic execution of methods
    private static final Logger LOGGER = Logger.getLogger(UIUtil.class.getName());

    private UIUtil() {
    }

    /**
     * Invoke an onClick method
     * 
     * @param controller the name of the class where the method is specified
     * @param method     the name of the method
     */
    public static void invokeClickMethod(String controller, String method) {
        try {
            Method m = Class.forName(controller).getMethod(method);
            m.invoke(null);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException
                | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
        }
    }

    /**
     * Invoke an onUpdate method
     * 
     * @param controller the name of the class where the method is specified
     * @param method     the name of the method
     * @param element    the element that called the method
     */
    public static void invokeUpdateMethod(String controller, String method, Element element) {
        try {
            Method m = Class.forName(controller).getMethod(method, Element.class);
            m.invoke(null, element);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException
                | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
        }
    }

    /**
     * Invoke an onInit method
     * 
     * @param controller the name of the class where the method is specified
     * @param method     the name of the method
     * 
     * @return the created element
     */
    public static Element invokeInitMethod(String controller, String method) {
        try {
            Method m = Class.forName(controller).getMethod(method);
            return (Element) m.invoke(null);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException
                | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
            return null;
        }
    }
}
