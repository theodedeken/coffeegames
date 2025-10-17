package voide.input;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import voide.debug.RepresentationBuilder;
import voide.resources.Resource;

//TODO change Key to string
public class KeyConfiguration
    extends HashMap<String, KeyState>
    implements Resource {

    private static final Logger LOGGER = Logger.getLogger(
        KeyConfiguration.class.getName()
    );

    public void initialize() {
        LOGGER.log(Level.INFO, "Initialized " + repr());
    }

    public String repr() {
        return String.format("KeyConfiguration { %d keybindings }", size());
    }

    public String fullRepr() {
        RepresentationBuilder builder = new RepresentationBuilder(
            getClass().getName()
        );
        for (Entry<String, KeyState> entry : entrySet()) {
            builder.field(entry.getKey(), entry.getValue());
        }
        return builder.build();
    }
}
