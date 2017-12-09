package klarman.solvers;

import klarman.constraints.ConstraintSystem;
import klarman.ontology.Ontology;

public abstract class TCSSolver {

     protected ConstraintSystem constraintSystem;
     protected Ontology ontology;

     public abstract boolean consistency();

}
