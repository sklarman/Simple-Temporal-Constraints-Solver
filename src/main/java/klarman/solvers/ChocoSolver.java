package klarman.solvers;

import klarman.constraints.ConstraintSystem;
import klarman.constraints.LinearConstraint;
import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solver;
import org.chocosolver.solver.variables.IntVar;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ChocoSolver extends TCSSolver {

    Model model;

    Map<String, IntVar> varMap;

    public ChocoSolver(ConstraintSystem constraintSystem) {

        this.constraintSystem = constraintSystem;
        this.varMap = new HashMap<>();

        convertToChoco();

    }

    //Choco requires imposes Integer bounds on the variable values.
    //This might lead to problems with input constants being of type long.

    private void convertToChoco() {

        this.model = new Model();

        Set<String> vars = constraintSystem.getVars();

        IntVar[] chocoVars = new IntVar[vars.size()];

        int i = 0;

        for (String var : vars) {
            chocoVars[i] = model.intVar(var, IntVar.MIN_INT_BOUND, IntVar.MAX_INT_BOUND);
            varMap.put(var, chocoVars[i]);
            i++;
        }


        for (LinearConstraint constraint : constraintSystem.getConstraints()) {


        }


//        model.arithm(x1, "<", x2).post();
//        model.arithm(x2, "<", x3).post();
//        model.arithm(x3, "<", x1).post();

    }

    public boolean consistency() {

        Solver solver =  model.getSolver();

        return solver.solve();
    }
}
