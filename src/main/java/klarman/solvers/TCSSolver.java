package klarman.solvers;

import klarman.TCSProblem;
import klarman.constraints.ConstraintSystem;
import klarman.ontology.Ontology;
import klarman.time.TimeConverter;

public abstract class TCSSolver {

    protected ConstraintSystem constraintSystem;
    protected Ontology ontology;
    protected TimeConverter converter;

    public abstract boolean consistency();

    public TCSSolver(TCSProblem problem) {

        this.constraintSystem = problem.getConstraintSystem();
        this.ontology = problem.getOntology();
        this.converter = problem.getConverter();

    }

}
