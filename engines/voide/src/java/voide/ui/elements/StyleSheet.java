package voide.ui.elements;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import voide.debug.RepresentationBuilder;
import voide.resources.Resource;

public class StyleSheet extends HashMap<String, HashMap<String, String>> implements Resource {
    private static final Logger LOGGER = Logger.getLogger(StyleSheet.class.getName());

    public void initialize() {
        LOGGER.log(Level.INFO, "Initialized " + repr());
    }

    public String repr() {
        return String.format("Stylesheet { %s styles }", size());
    }

    public String fullRepr() {
        RepresentationBuilder builder = new RepresentationBuilder(getClass().getName());
        for (Entry<String, HashMap<String, String>> entry : entrySet()) {
            builder.field(entry.getKey(), entry.getValue());
        }
        return builder.build();
    }

}
