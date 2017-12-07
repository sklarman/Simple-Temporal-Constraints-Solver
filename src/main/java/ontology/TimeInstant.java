package ontology;

public class TimeInstant {

    private String id;
    private String alias;
    private String var;

    public String getId() {
        return id;
    }

    public String getAlias() {
        return alias;
    }

    public String getVar() {
        return var;
    }

    public TimeInstant(String id) {
        this.id = id;
        this.alias = "ins_" + id.hashCode();
        this.var = alias;
    }

    @Override
    public String toString() {
        return "ontology.TimeInterval{" +
                "id='" + id + '\'' +
                ", alias='" + alias + '\'' +
                ", var='" + var + '\'' +
                '}';
    }

}
