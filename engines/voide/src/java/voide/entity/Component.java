package voide.entity;

import java.lang.reflect.Field;

import voide.debug.Representable;
import voide.debug.RepresentationBuilder;

public abstract class Component implements Representable {
    private ComponentType type;

    /**
     * Create a new component of the specified type
     * 
     * @param type the type of the component
     */
    public Component(ComponentType type) {
        this.type = type;
    }

    /**
     * Return a copy of this component.
     * 
     * @return The copied component
     */
    public abstract Component copy();

    public ComponentType getType() {
        return type;
    }

    public String repr() {
        return String.format("%s { %s }", getClass().getName(), type.id());
    }

    public String fullRepr() {
        RepresentationBuilder builder = new RepresentationBuilder(getClass().getName());
        Field[] fields = getClass().getDeclaredFields();
        for (Field f : fields) {
            try {
                builder.field(f.getName(), f.get(this));
            } catch (IllegalAccessException e) {
                continue;
            }
        }
        return builder.build();
    }
}
