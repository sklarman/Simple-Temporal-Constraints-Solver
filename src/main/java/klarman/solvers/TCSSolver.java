package klarman.solvers;

import klarman.constraints.ConstraintSystem;

public abstract class TCSSolver {

     ConstraintSystem constraintSystem = null;

     public abstract boolean consistency();

}
