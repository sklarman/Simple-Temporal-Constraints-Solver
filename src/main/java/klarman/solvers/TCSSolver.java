package klarman.solvers;

import klarman.constraints.ConstraintSystem;

public abstract class TCSSolver {

     protected ConstraintSystem constraintSystem;

     public abstract boolean consistency();

}
