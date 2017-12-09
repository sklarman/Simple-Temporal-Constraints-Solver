package klarman;

import klarman.constraints.ConstraintSystem;
import klarman.ontology.*;
import klarman.time.TimeConverter;

import java.util.ArrayList;
import java.util.List;

import static klarman.constraints.ConstraintVocabulary.EQ;
import static klarman.constraints.ConstraintVocabulary.LEQ;
import static klarman.ontology.OntologyVocabulary.*;

public class TCSProblem {

    private ConstraintSystem constraintSystem;
    private Ontology ontology;
    private TimeConverter converter;
    private List<Assertion> input;

    public ConstraintSystem getConstraintSystem() {
        return constraintSystem;
    }

    public Ontology getOntology() {
        return ontology;
    }

    public TCSProblem(List<Assertion> input) {

        this.converter = new TimeConverter();
        constructProblem(input);
    }

    public TCSProblem(List<Assertion> input, int precision) {

        this.converter = new TimeConverter(precision);
        constructProblem(input);

    }

    private void constructProblem(List<Assertion> input) {

        this.constraintSystem = new ConstraintSystem();
        this.ontology = new Ontology();
        this.input = new ArrayList<>();
        this.input.addAll(input);

        while (!input.isEmpty()) {
            try {
                extractInformation(input.get(0));
            } catch (Exception e) {
                e.printStackTrace();
            }
            input.remove(0);
        }

    }


    private void extractInformation(Assertion assertion) throws Exception {

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
                throw new Exception("An unsupported predicate encountered: " + assertion.toString());
            }


        }

    }

    private void extract_TIME_XSD_YEAR(Assertion assertion) {
        TimeInstant instant = ontology.addInstant(assertion.getSubject());
        String year = assertion.getObject();

        long beginning = converter.getYearBeginning(year);
        long end = converter.getYearEnd(year);

        constraintSystem.addConstraint(instant.getVar(), 0, LEQ, "", end);
        constraintSystem.addConstraint("", beginning, LEQ, instant.getVar(), 0);
    }

    private void extract_TIME_XSD_YEAR_MONTH(Assertion assertion) {
        TimeInstant instant = ontology.addInstant(assertion.getSubject());
        String yearMonth = assertion.getObject();

        long beginning = converter.getYearMonthBeginning(yearMonth);
        long end = converter.getYearMonthEnd(yearMonth);

        constraintSystem.addConstraint(instant.getVar(), 0, LEQ, "", end);
        constraintSystem.addConstraint("", beginning, LEQ, instant.getVar(), 0);
    }

    private void extract_TIME_XSD_DATE(Assertion assertion) {
        TimeInstant instant = ontology.addInstant(assertion.getSubject());
        String date = assertion.getObject();

        long beginning = converter.getDateBeginning(date);
        long end = converter.getDateEnd(date);

        constraintSystem.addConstraint(instant.getVar(), 0, LEQ, "", end);
        constraintSystem.addConstraint("", beginning, LEQ, instant.getVar(), 0);
    }

    private void extract_TIME_XSD_DATE_TIME(Assertion assertion) {
        TimeInstant instant = ontology.addInstant(assertion.getSubject());
        String dateTime = assertion.getObject();

        long beginning = converter.getDateTimeBeginning(dateTime);
        long end = converter.getDateTimeEnd(dateTime);

        constraintSystem.addConstraint(instant.getVar(), 0, LEQ, "", end);
        constraintSystem.addConstraint("", beginning, LEQ, instant.getVar(), 0);
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

        input.add(input.size(), new Assertion(interval1.getId(), TIME_DURING, interval2.getId()));
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
