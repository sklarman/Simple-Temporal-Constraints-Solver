package ontology;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Ontology {

    private Map<String, Event> events;
    private Map<String, TimeInstant> instants;
    private Map<String, TimeInterval> intervals;
    private Set<String> eventVars;

    public Map<String, Event> getEvents() {
        return events;
    }

    public Map<String, TimeInstant> getInstants() {
        return instants;
    }

    public Map<String, TimeInterval> getIntervals() {
        return intervals;
    }

    public Set<String> getEventVars() {
        return eventVars;
    }

    public Ontology() {
        this.events = new HashMap<>();
        this.instants = new HashMap<>();
        this.intervals = new HashMap<>();
        this.eventVars = new HashSet<>();
    }

    public TimeInstant addInstant(String id) {

        if (instants.containsKey(id)) {
            return instants.get(id);
        } else {
            TimeInstant instant = new TimeInstant(id);
            instants.put(id, instant);
            return instant;
        }
    }

    public TimeInterval addInterval(String id) {
        if (intervals.containsKey(id)) {
            return intervals.get(id);
        } else {
            TimeInterval interval = new TimeInterval(id);
            intervals.put(id, interval);
            return interval;
        }
    }

    public Event addEvent(String id, TimeInterval interval) {
        if (events.containsKey(id)) {
            return events.get(id);
        } else {
            Event event = new Event(id, interval);
            events.put(id, event);
            eventVars.add(event.getBegVar());
            eventVars.add(event.getEndVar());
            return event;
        }
    }

    @Override
    public String toString() {
        return "Ontology{" +
                "events=" + events +
                ", instants=" + instants +
                ", intervals=" + intervals +
                 ", eventVars=" + eventVars +
                '}';
    }
}
