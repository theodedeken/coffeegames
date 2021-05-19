package spaxel.entity;

import voide.entity.Component;
import voide.entity.ComponentType;
import voide.entity.Entity;

public abstract class Behaviour extends Component {

    public Behaviour(ComponentType type) {
        super(type);
    }

    public abstract void execute(Entity parent);
}
