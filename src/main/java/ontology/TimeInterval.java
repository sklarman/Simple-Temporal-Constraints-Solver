package ontology;

public class TimeInterval {

    private String id;
    private String alias;
    private String begVar;
    private String endVar;

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
        this.alias = "int_" + id.hashCode();
        this.begVar = alias + "-";
        this.endVar = alias + "+";
    }

    @Override
    public String toString() {
        return "ontology.TimeInterval{" +
                "id='" + id + '\'' +
                ", alias='" + alias + '\'' +
                ", begVar='" + begVar + '\'' +
                ", endVar='" + endVar + '\'' +
                '}';
    }
}
