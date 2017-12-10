package klarman.solvers;

import klarman.TCSProblem;
import klarman.constraints.ConstraintSystem;
import klarman.ontology.Ontology;
import org.chocosolver.solver.variables.IntVar;

import java.util.HashMap;
import java.util.Map;

public abstract class TCSSolver {

    protected ConstraintSystem constraintSystem;
    protected Ontology ontology;
    protected Map<String, IntVar> varMap;

    public abstract boolean consistency();

    public TCSSolver(TCSProblem problem) {

        this.constraintSystem = problem.getConstraintSystem();
        this.ontology = problem.getOntology();
        this.varMap = new HashMap<>();
    }

}
