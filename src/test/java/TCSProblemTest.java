import klarman.TCSProblem;
import klarman.ontology.Assertion;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TCSProblemTest {

    @Test
    public void confirmCardinalityOfConstraintSystem(){


        List<Assertion> input = new ArrayList<Assertion>() {{
            add(new Assertion("http://test/interval1","http://www.w3.org/2006/time#hasXSDDuration", "P1D"));
            add(new Assertion("http://test/instant1","http://www.w3.org/2006/time#inXSDDate", "2017-01-01"));
            add(new Assertion("http://test/instant2","http://www.w3.org/2006/time#inXSDDateTime", "2017-10-10T12:00:00"));
            add(new Assertion("http://test/instant3","http://www.w3.org/2006/time#inXSDgYear", "2017"));
            add(new Assertion("http://test/instant4","http://www.w3.org/2006/time#inXSDgYearMonth", "2017-10"));
            add(new Assertion("http://test/event1","http://granthika.co/grantha/story#spansTime", "http://test/interval2"));
            add(new Assertion("http://test/event2","http://granthika.co/grantha/story#inTime", "http://test/interval3"));
            add(new Assertion("http://test/interval4","http://www.w3.org/2006/time#intervalDuring", "http://test/interval5"));
            add(new Assertion("http://test/interval6","http://www.w3.org/2006/time#hasBeginning", "http://test/instant5"));
            add(new Assertion("http://test/interval7","http://www.w3.org/2006/time#hasEnd", "http://test/instant6"));
            add(new Assertion("http://test/interval8","http://www.w3.org/2006/time#before", "http://test/interval9"));
            add(new Assertion("http://test/interval10","http://www.w3.org/2006/time#after", "http://test/interval11"));
        }};

        TCSProblem problem = new TCSProblem(input);

        System.out.println(problem.getOntology().toString());
        System.out.println();
        System.out.println(problem.getConstraintSystem().toString());

        assertEquals(27, problem.getConstraintSystem().getConstraints().size());

    }


    @Test
    public void countVarsInSystem(){


        List<Assertion> input = new ArrayList<Assertion>() {{
            add(new Assertion("http://test/interval1","http://www.w3.org/2006/klarman.time#hasXSDDuration", "P1D"));
            add(new Assertion("http://test/event2","http://granthika.co/grantha/story#inTime", "http://test/interval3"));
        }};

        TCSProblem problem = new TCSProblem(input);

        assertEquals(6, problem.getConstraintSystem().getVars().size());
    }

}