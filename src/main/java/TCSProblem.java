import constraints.ConstraintSystem;
import ontology.Assertion;
import ontology.Ontology;
import ontology.TimeInstant;

import java.util.Set;

import static ontology.OntologyVocabulary.*;

public class TCSProblem {

    private ConstraintSystem constraintSystem;
    private Ontology ontology;
    private TemporalDataConverter converter;


    public TCSProblem(Set<Assertion> input, int precision) {

        this.constraintSystem = new ConstraintSystem();
        this.ontology = new Ontology();
        this.converter = new TemporalDataConverter(precision);

        for (Assertion assertion : input) {
            extractInformation(assertion);
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


        }

    }

    private void extract_TIME_XSD_YEAR(Assertion assertion) {
    }

    private void extract_TIME_XSD_DATE_TIME(Assertion assertion) {
    }

    private void extract_TIME_XSD_DATE(Assertion assertion) {
    }

    private void extract_TIME_XSD_DURATION(Assertion assertion) {
    }

    private void extract_TIME_XSD_YEAR_MONTH(Assertion assertion) {
    }

    private void extract_GRS_SPANS_TIME(Assertion assertion) {
    }

    private void extract_GRS_IN_TIME(Assertion assertion) {
    }

    private void extract_TIME_DURING(Assertion assertion) {
    }

    private void extract_TIME_BEGINNING(Assertion assertion) {
    }

    private void extract_TIME_END(Assertion assertion) {
    }

    private void extract_TIME_BEFORE(Assertion assertion) {
    }

    private void extract_TIME_AFTER(Assertion assertion) {
    }



}
