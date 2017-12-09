package klarman.ontology;

public class TimeInstant  {

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

    public String begVar() {
        return var;
    }

    public String endVar() {
        return var;
    }

    public TimeInstant(String id) {
        this.id = id;
        this.alias = "v" + id.hashCode();
        this.var = alias;
    }

    @Override
    public String toString() {
        return "TimeInterval{" +
                "id='" + id + '\'' +
                ", alias='" + alias + '\'' +
                ", var='" + var + '\'' +
                "}\n";
    }

}
