package klarman.constraints;

public class TCSSolution {

    private String id;
    private long begVar;
    private long endVar;

    public String getId() {
        return id;
    }

    public long getBegVar() {
        return begVar;
    }

    public long getEndVar() {
        return endVar;
    }

    public TCSSolution(String id, long begVar, long endVar) {
        this.id = id;
        this.begVar = begVar;
        this.endVar = endVar;
    }

    @Override
    public String toString() {
        return "TCSSolution{" +
                "id='" + id + '\'' +
                ", begVar=" + begVar +
                ", endVar=" + endVar +
                '}';
    }
}
