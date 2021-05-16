package voide.entity;

public abstract class Component {
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
}
