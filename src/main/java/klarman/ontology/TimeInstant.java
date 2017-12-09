package klarman.ontology;

public class TimeInstant extends TimeInterval {

    private String var;

    public String getVar() {
        return var;
    }

    public TimeInstant(String id) {
        this.id = id;
        this.alias = "v" + id.hashCode();
        this.var = alias;
        this.begVar = var;
        this.endVar = var;
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
