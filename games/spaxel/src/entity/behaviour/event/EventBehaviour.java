package spaxel.entity.behaviour.event;

import java.util.EnumMap;
import java.util.Map;
import spaxel.entity.Behaviour;
import spaxel.entity.ComponentType;
import spaxel.entity.storage.event.EventStorage;
import spaxel.entity.Entity;

public class EventBehaviour extends Behaviour {
    private Map<Event, EventHandler> handlers;

    public EventBehaviour() {
        super(ComponentType.EVENT);
    }

    public EventBehaviour(Map<Event, EventHandler> handlers) {
        super(ComponentType.EVENT);
        this.handlers = handlers;
    }

    public void execute(Entity entity) {
        EventStorage evStore = (EventStorage) entity.getComponent(ComponentType.EVENT_STORE);
        for (Event event : evStore.getEvents()) {
            if (handlers.containsKey(event)) {
                handlers.get(event).activate(entity);
            }
        }
        evStore.clear();
    }

    public EventBehaviour copy() {
        return new EventBehaviour(new EnumMap<>(handlers));
    }
}
