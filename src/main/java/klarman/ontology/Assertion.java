package klarman.ontology;

public class Assertion {

    public String getSubject() {
        return subject;
    }

    public String getPredicate() {
        return predicate;
    }

    public String getObject() {
        return object;
    }

    private String subject;
    private String predicate;
    private String object;

    public Assertion(String subject, String predicate, String object) {
        this.subject = subject;
        this.predicate = predicate;
        this.object = object;
    }

    @Override
    public String toString() {
        return "(" + subject + ", " + predicate + ", " + object + ")";
    }
}
