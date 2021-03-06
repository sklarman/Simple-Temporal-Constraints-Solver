package klarman.ontology;

public class TimeInterval {

    protected String id;
    protected String alias;
    protected String begVar;
    protected String endVar;

    public TimeInterval() {
    }

    public String getId() {
        return id;
    }

    public String getBegVar() {
        return begVar;
    }

    public String getEndVar() {
        return endVar;
    }

    public String getAlias() {
        return alias;
    }

    public TimeInterval(String id) {
        this.id = id;
        this.alias = "v" + id.hashCode();
        this.begVar = alias + "-";
        this.endVar = alias + "+";
    }

    @Override
    public String toString() {
        return "TimeInterval{" +
                "id='" + id + '\'' +
                ", alias='" + alias + '\'' +
                ", begVar='" + begVar + '\'' +
                ", endVar='" + endVar + '\'' +
                "}\n";
    }
}
