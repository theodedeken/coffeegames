package voide.ui.elements;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents the styling applied to an ui Element
 */
public class Style {

    private static final List<String> STYLE_SPECIFIC = Arrays.asList(
        "onClick",
        "onUpdate",
        "onInit",
        "sprite",
        "text",
        "hitshape",
        "animation"
    );

    private Style parent;
    private Map<String, String> properties;

    private Map<String, String> defaults;

    /**
     * Create a new empty Style object
     */
    public Style() {
        this.properties = new HashMap<>();
        this.defaults = new HashMap<>();
        defaults.put("position", "absolute");
        defaults.put("rotation", "absolute");
        defaults.put("scale", "1");
        defaults.put("rot", "0");
        defaults.put("x", "640");
        defaults.put("y", "360");
        defaults.put("visible", "true");
        defaults.put("text-scale", "1");
    }

    public void setParent(Style parent) {
        this.parent = parent;
    }

    public Style getParent() {
        return parent;
    }

    /**
     * Get the property value for this key. If no value is found in this style,
     * continue searching in the parent style
     *
     * @param key the key of the property
     *
     * @return the value of the property
     */
    public String getProperty(String key, State currentState) {
        for (String mod : currentState.getModifiers()) {
            if (properties.containsKey(key + ":" + mod)) {
                return properties.get(key + ":" + mod);
            }
        }
        if (properties.containsKey(key)) {
            return properties.get(key);
        }
        String result = defaults.get(key);
        if (parent != null && !STYLE_SPECIFIC.contains(key)) {
            result = parent.getProperty(key, currentState);
        }
        return result;
    }

    /**
     * Checks if the style contains a certain property
     *
     * @param key the name of the property
     *
     * @return true if the style contains the property
     */
    public boolean contains(String key, State currentState) {
        for (String mod : currentState.getModifiers()) {
            if (properties.containsKey(key + ":" + mod)) {
                return true;
            }
        }
        if (properties.containsKey(key)) {
            return true;
        }
        boolean result = false;
        if (parent != null && !STYLE_SPECIFIC.contains(key)) {
            result = parent.contains(key, currentState);
        }
        return result;
    }

    /**
     * Merge this map of properties with the properties of this style using the
     * given modifier
     *
     * @param props the properties to merge
     * @param mod   the modifier to merge on
     */
    public void merge(Map<String, String> props, String mod) {
        if (props != null) {
            for (Map.Entry<String, String> entry : props.entrySet()) {
                properties.put(entry.getKey() + ":" + mod, entry.getValue());
            }
        }
    }

    /**
     * Merge this map of properties with the properties of this style
     *
     * @param props the properties to merge
     */
    public void merge(Map<String, String> props) {
        if (props != null) {
            properties.putAll(props);
        }
    }

    /**
     * Set a property of this style
     *
     * @param key   the key of the property
     * @param value the value to set
     */
    public void setProperty(String key, String value) {
        properties.put(key, value);
    }

    /**
     * Set a property of this style given the modifier
     *
     * @param key   the key of the property
     * @param value the value of the property
     * @param mod   the modifier of the property
     */
    public void setProperty(String key, String value, String mod) {
        properties.put(key + ":" + mod, value);
    }

    public Map<String, String> getProperties() {
        return properties;
    }
}
