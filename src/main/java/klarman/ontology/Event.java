package klarman.ontology;

public class Event {

    public String getId() {
        return id;
    }

    public String getBegVar() {
        return begVar;
    }

    public String getEndVar() {
        return endVar;
    }

    private String id;
    private String begVar;
    private String endVar;

    public Event(String id, String begVar, String endVar) {
        this.id= id;
        this.begVar = begVar;
        this.endVar = endVar;
    }

    public Event(String id, TimeInterval interval) {
        this.id= id;
        this.begVar = interval.getBegVar();
        this.endVar = interval.getEndVar();
    }

    @Override
    public String toString() {
        return "Event{" +
                "id='" + id + '\'' +
                ", begVar='" + begVar + '\'' +
                ", endVar='" + endVar + '\'' +
                "}\n";
    }
}
