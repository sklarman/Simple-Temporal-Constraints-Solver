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
            if (!var.equals("")) {
                varMap.put(var, chocoVars[i]);
            }
            i++;
        }


        for (LinearConstraint constraint : constraintSystem.getConstraints()) {

            String var1 = constraint.getLhs().getVariable();
            String var2 = constraint.getRhs().getVariable();
            int const1 = (int) constraint.getLhs().getConstant();
            int const2 = (int) constraint.getRhs().getConstant();

            IntVar intVar1 = varMap.get(var1);
            IntVar intVar2 = varMap.get(var2);

//            model.arithm(intVar1, "+", intVar2, "<",).post();
//            model.arithm(x, "<", y, "+", 5).post();
//            model.arithm(x2, "<", x3).post();

//            412008 <= ins_-1014269315
//            int_-903589529- <= int_-903589529+
//            int_-903589535- + 24 = int_-903589535+
//            int_-903589530- = ins_-1014269313
//            ins_-1014269317 <= 412032

        }

    }

    @Override
    public boolean consistency() {

        Solver solver =  model.getSolver();

        return solver.solve();
    }
}
