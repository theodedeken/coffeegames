package spaxel.entity.storage.event;

import java.util.HashSet;
import java.util.Set;
import spaxel.entity.SpaxelComponent;
import spaxel.entity.behaviour.event.Event;
import voide.entity.Storage;

public class EventStorage extends Storage {

    private Set<Event> events;

    public EventStorage() {
        super(SpaxelComponent.EVENT_STORE);
    }

    public EventStorage(Set<Event> events) {
        super(SpaxelComponent.EVENT_STORE);
        this.events = events;
    }

    /**
     * @return the events
     */
    public Set<Event> getEvents() {
        return events;
    }

    public void addEvent(Event event) {
        events.add(event);
    }

    public void clear() {
        events.clear();
    }

    public EventStorage copy() {
        return new EventStorage(new HashSet<>(events));
    }
}
