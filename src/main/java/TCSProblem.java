import constraints.ConstraintSystem;
import ontology.*;

import java.util.Set;

import static constraints.ConstraintVocabulary.EQ;
import static constraints.ConstraintVocabulary.LEQ;
import static ontology.OntologyVocabulary.*;

public class TCSProblem {

    private ConstraintSystem constraintSystem;
    private Ontology ontology;
    private TemporalDataConverter converter;
    private Set<Assertion> input;

    public ConstraintSystem getConstraintSystem() {
        return constraintSystem;
    }

    public Ontology getOntology() {
        return ontology;
    }

    public TCSProblem(Set<Assertion> input) {

        this.converter = new TemporalDataConverter();

        constructProblem(input);
    }

    public TCSProblem(Set<Assertion> input, int precision) {

        this.converter = new TemporalDataConverter(precision);

    }

    private void constructProblem(Set<Assertion> input) {

        this.constraintSystem = new ConstraintSystem();
        this.ontology = new Ontology();
        this.input = input;

        for (Assertion assertion : input) {
            extractInformation(assertion);
            input.remove(input);
        }

    }


    private void extractInformation(Assertion assertion) {

        switch (assertion.getPredicate()) {

            case TIME_XSD_DURATION: {
                extract_TIME_XSD_DURATION(assertion);
                break;
            }
            case TIME_XSD_DATE: {
                extract_TIME_XSD_DATE(assertion);
                break;
            }
            case TIME_XSD_DATE_TIME: {
                extract_TIME_XSD_DATE_TIME(assertion);
                break;
            }
            case TIME_XSD_YEAR: {
                extract_TIME_XSD_YEAR(assertion);
                break;
            }
            case TIME_XSD_YEAR_MONTH: {
                extract_TIME_XSD_YEAR_MONTH(assertion);
                break;
            }
            case GRS_SPANS_TIME: {
                extract_GRS_SPANS_TIME(assertion);
                break;
            }
            case GRS_IN_TIME: {
                extract_GRS_IN_TIME(assertion);
                break;
            }
            case TIME_DURING: {
                extract_TIME_DURING(assertion);
                break;
            }
            case TIME_BEGINNING: {
                extract_TIME_BEGINNING(assertion);
                break;
            }
            case TIME_END: {
                extract_TIME_END(assertion);
                break;
            }
            case TIME_BEFORE: {
                extract_TIME_BEFORE(assertion);
                break;
            }
            case TIME_AFTER: {
                extract_TIME_AFTER(assertion);
                break;
            }
            default: {
                return;
            }


        }

    }

    private void extract_TIME_XSD_YEAR(Assertion assertion) {
        TimeInstant instant = ontology.addInstant(assertion.getSubject());
        String year = assertion.getObject();

        long beginning = converter.getYearBeginning(year);
        long end = converter.getYearEnd(year);

        constraintSystem.addConstraint(instant.getVar(), 0, LEQ, null, end);
        constraintSystem.addConstraint(null, beginning, LEQ, instant.getVar(), 0);
    }

    private void extract_TIME_XSD_YEAR_MONTH(Assertion assertion) {
        TimeInstant instant = ontology.addInstant(assertion.getSubject());
        String yearMonth = assertion.getObject();

        long beginning = converter.getYearBeginning(yearMonth);
        long end = converter.getYearEnd(yearMonth);

        constraintSystem.addConstraint(instant.getVar(), 0, LEQ, null, end);
        constraintSystem.addConstraint(null, beginning, LEQ, instant.getVar(), 0);
    }

    private void extract_TIME_XSD_DATE(Assertion assertion) {
        TimeInstant instant = ontology.addInstant(assertion.getSubject());
        String date = assertion.getObject();

        long beginning = converter.getYearBeginning(date);
        long end = converter.getYearEnd(date);

        constraintSystem.addConstraint(instant.getVar(), 0, LEQ, null, end);
        constraintSystem.addConstraint(null, beginning, LEQ, instant.getVar(), 0);
    }

    private void extract_TIME_XSD_DATE_TIME(Assertion assertion) {
        TimeInstant instant = ontology.addInstant(assertion.getSubject());
        String dateTime = assertion.getObject();

        long beginning = converter.getYearBeginning(dateTime);
        long end = converter.getYearEnd(dateTime);

        constraintSystem.addConstraint(instant.getVar(), 0, LEQ, null, end);
        constraintSystem.addConstraint(null, beginning, LEQ, instant.getVar(), 0);
    }

    private void extract_TIME_XSD_DURATION(Assertion assertion) {
        TimeInterval interval = ontology.addInterval(assertion.getSubject());
        String durationStr = assertion.getObject();

        long duration = converter.getDuration(durationStr);

        constraintSystem.addInterval(interval);
        constraintSystem.addConstraint(interval.getBegVar(), duration, EQ, interval.getEndVar(), 0);
    }

    private void extract_GRS_SPANS_TIME(Assertion assertion) {
        TimeInterval interval = ontology.addInterval(assertion.getObject());
        ontology.addEvent(assertion.getSubject(), interval);

        constraintSystem.addInterval(interval);
    }

    private void extract_GRS_IN_TIME(Assertion assertion) {
        TimeInterval interval1 = ontology.addInterval();
        TimeInterval interval2 = ontology.addInterval(assertion.getObject());
        ontology.addEvent(assertion.getSubject(), interval1);

        constraintSystem.addInterval(interval1);
        constraintSystem.addInterval(interval2);

        input.add(new Assertion(interval1.getId(), TIME_DURING, interval2.getId()));
    }

    private void extract_TIME_DURING(Assertion assertion) {
        TimeInterval interval1 = ontology.addInterval(assertion.getSubject());
        TimeInterval interval2 = ontology.addInterval(assertion.getObject());

        constraintSystem.addInterval(interval1);
        constraintSystem.addInterval(interval2);

        constraintSystem.addConstraint(interval2.getBegVar(), 0, LEQ, interval1.getBegVar(), 0);
        constraintSystem.addConstraint(interval1.getEndVar(), 0, LEQ, interval2.getEndVar(), 0);
    }

    private void extract_TIME_BEFORE(Assertion assertion) {
        TimeInterval interval1 = ontology.addInterval(assertion.getSubject());
        TimeInterval interval2 = ontology.addInterval(assertion.getObject());

        constraintSystem.addInterval(interval1);
        constraintSystem.addInterval(interval2);

        constraintSystem.addConstraint(interval1.getEndVar(), 0, LEQ, interval2.getBegVar(), 0);
    }

    private void extract_TIME_AFTER(Assertion assertion) {
        TimeInterval interval1 = ontology.addInterval(assertion.getSubject());
        TimeInterval interval2 = ontology.addInterval(assertion.getObject());

        constraintSystem.addInterval(interval1);
        constraintSystem.addInterval(interval2);

        constraintSystem.addConstraint(interval2.getBegVar(), 0, LEQ, interval1.getEndVar(), 0);

    }

    private void extract_TIME_BEGINNING(Assertion assertion) {
        TimeInterval interval = ontology.addInterval(assertion.getSubject());
        TimeInstant instant = ontology.addInstant(assertion.getObject());

        constraintSystem.addInterval(interval);

        constraintSystem.addConstraint(interval.getBegVar(), 0, EQ, instant.getVar(), 0);
    }

    private void extract_TIME_END(Assertion assertion) {
        TimeInterval interval = ontology.addInterval(assertion.getSubject());
        TimeInstant instant = ontology.addInstant(assertion.getObject());

        constraintSystem.addInterval(interval);

        constraintSystem.addConstraint(interval.getEndVar(), 0, EQ, instant.getVar(), 0);
    }





}
