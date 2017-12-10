package klarman.solvers;

import klarman.TCSProblem;
import klarman.constraints.ConstraintSystem;
import klarman.ontology.Ontology;

public abstract class TCSSolver {

    protected ConstraintSystem constraintSystem;
    protected Ontology ontology;

    public abstract boolean consistency();

    public TCSSolver(TCSProblem problem) {

        this.constraintSystem = problem.getConstraintSystem();
        this.ontology = problem.getOntology();

    }

}
