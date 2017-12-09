package klarman.solvers;

import klarman.TCSProblem;
import klarman.constraints.TCSSolutionMap;
import klarman.ontology.Assertion;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ChocoSolverTest {

    @Test
    public void consistentProblem1() {
        List<Assertion> input = new ArrayList<Assertion>() {{
            add(new Assertion("http://test/interval1","http://www.w3.org/2006/time#hasXSDDuration", "P1D"));
           }};

        TCSProblem problem = null;
        try {
            problem = new TCSProblem(input);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //System.out.println(problem.getOntology().toString());
        //System.out.println(problem.getConstraintSystem().toString());

        ChocoSolver solver = new ChocoSolver(problem);
        assertTrue(solver.consistency());
    }

    @Test
    public void consistentProblem2() {
        List<Assertion> input = new ArrayList<Assertion>() {{
            add(new Assertion("http://test/interval","http://www.w3.org/2006/time#hasXSDDuration", "P1D"));
            add(new Assertion("http://test/interval","http://www.w3.org/2006/time#hasBeginning", "http://test/instant1"));
            add(new Assertion("http://test/interval","http://www.w3.org/2006/time#hasEnd", "http://test/instant2"));
            add(new Assertion("http://test/instant1","http://www.w3.org/2006/time#inXSDDate", "2017-01-01"));
            add(new Assertion("http://test/instant3","http://www.w3.org/2006/time#inXSDDate", "2017-01-02"));
        }};

        TCSProblem problem = null;
        try {
            problem = new TCSProblem(input);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //System.out.println(problem.getOntology().toString());
        //System.out.println(problem.getConstraintSystem().toString());

        ChocoSolver solver = new ChocoSolver(problem);
        assertTrue(solver.consistency());
    }

    @Test
    public void inconsistentProblem1() {
        List<Assertion> input = new ArrayList<Assertion>() {{
            add(new Assertion("http://test/interval","http://www.w3.org/2006/time#hasXSDDuration", "P1D"));
            add(new Assertion("http://test/interval","http://www.w3.org/2006/time#hasBeginning", "http://test/instant1"));
            add(new Assertion("http://test/interval","http://www.w3.org/2006/time#hasEnd", "http://test/instant2"));
            add(new Assertion("http://test/instant1","http://www.w3.org/2006/time#inXSDDateTime", "2017-01-01T12:00:00"));
            add(new Assertion("http://test/instant2","http://www.w3.org/2006/time#inXSDDateTime", "2017-01-02T12:00:01"));
        }};

        TCSProblem problem = null;
        try {
            problem = new TCSProblem(input, 2);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //System.out.println(problem.getOntology().toString());
        //System.out.println(problem.getConstraintSystem().toString());

        ChocoSolver solver = new ChocoSolver(problem);
        assertTrue(!solver.consistency());
    }

    @Test
    public void inconsistentProblem2() {
        List<Assertion> input = new ArrayList<Assertion>() {{
            add(new Assertion("http://test/interval1","http://www.w3.org/2006/time#hasXSDDuration", "P1D"));
            add(new Assertion("http://test/interval1","http://www.w3.org/2006/time#after", "http://test/interval2"));
            add(new Assertion("http://test/interval2","http://www.w3.org/2006/time#after", "http://test/interval3"));
            add(new Assertion("http://test/interval3","http://www.w3.org/2006/time#after", "http://test/interval1"));
        }};

        TCSProblem problem = null;
        try {
            problem = new TCSProblem(input, 3);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //System.out.println(problem.getOntology().toString());
        //System.out.println(problem.getConstraintSystem().toString());

        ChocoSolver solver = new ChocoSolver(problem);
        assertTrue(!solver.consistency());
    }

    @Test
    public void findSolutionForConsistentProblem() {
        List<Assertion> input = new ArrayList<Assertion>() {{
            add(new Assertion("http://test/event","http://granthika.co/grantha/story#spansTime", "http://test/interval"));
            add(new Assertion("http://test/interval","http://www.w3.org/2006/time#hasBeginning", "http://test/instant1"));
            add(new Assertion("http://test/instant1","http://www.w3.org/2006/time#inXSDDate", "2017-01-01"));
            add(new Assertion("http://test/interval","http://www.w3.org/2006/time#hasEnd", "http://test/instant2"));
            add(new Assertion("http://test/instant2","http://www.w3.org/2006/time#inXSDDate", "2017-01-02"));
        }};

        TCSProblem problem = null;
        try {
            problem = new TCSProblem(input, 3);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //System.out.println(problem.getOntology().toString());
        //System.out.println(problem.getConstraintSystem().toString());

        ChocoSolver solver = new ChocoSolver(problem);

        TCSSolutionMap solutionMap = solver.findSolution();

        //System.out.println(solutionMap.toString());

        assertEquals(412008, solutionMap.get("http://test/event").getBegVar());
        assertEquals(412032, solutionMap.get("http://test/event").getEndVar());
    }


    @Test
    public void findSolutionForInconsistentProblem() {
        List<Assertion> input = new ArrayList<Assertion>() {{
            add(new Assertion("http://test/interval1","http://www.w3.org/2006/time#hasXSDDuration", "P1D"));
            add(new Assertion("http://test/interval1","http://www.w3.org/2006/time#after", "http://test/interval2"));
            add(new Assertion("http://test/interval2","http://www.w3.org/2006/time#after", "http://test/interval3"));
            add(new Assertion("http://test/interval3","http://www.w3.org/2006/time#after", "http://test/interval1"));
        }};

        TCSProblem problem = null;
        try {
            problem = new TCSProblem(input, 3);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //System.out.println(problem.getOntology().toString());
        //System.out.println(problem.getConstraintSystem().toString());

        ChocoSolver solver = new ChocoSolver(problem);
        TCSSolutionMap solutionMap = solver.findSolution();

        assertEquals(0, solutionMap.size());
    }

}