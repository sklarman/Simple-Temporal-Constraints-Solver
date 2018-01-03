package klarman;

import klarman.time.TimeConverter;

public class TCSSolution {

    private String id;
    private long begVar;
    private long endVar;
    private String begVarXSD;
    private String endVarXSD;

    public String getId() { return id; }

    public long getBegVar() {
        return begVar;
    }

    public long getEndVar() {
        return endVar;
    }

    public String getBegVarXSD() {
        return begVarXSD;
    }

    public String getEndVarXSD() {
        return endVarXSD;
    }

    public TCSSolution(String id, long begVar, long endVar, TimeConverter converter) {
        this.id = id;
        this.begVar = begVar;
        this.endVar = endVar;
        this.begVarXSD = converter.getXSDDateTime(begVar);
        this.endVarXSD = converter.getXSDDateTime(endVar);
    }

    @Override
    public String toString() {
        return "TCSSolution{" +
                "id='" + id + '\'' +
                ", begVar=" + begVar +
                ", endVar=" + endVar +
                ", begVarXSD=" + begVarXSD +
                ", endVarXSD=" + endVarXSD +
                '}';
    }
}
