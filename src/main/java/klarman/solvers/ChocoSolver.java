package klarman.solvers;

import klarman.TCSProblem;

public class ChocoSolver extends TCSSolver {


    public ChocoSolver(TCSProblem problem) {

        this.problem = problem;

    }

    public boolean consistency() {
//
//            System.out.println("klarman.solvers.ChocoSolver test");
//
//            Model model = new Model( "test");
//
//
//            IntVar x1 = model.intVar("x1", IntVar.MIN_INT_BOUND, IntVar.MAX_INT_BOUND);
//            IntVar x2 = model.intVar("x2", IntVar.MIN_INT_BOUND, IntVar.MAX_INT_BOUND);
//            IntVar x3 = model.intVar("x3", IntVar.MIN_INT_BOUND, IntVar.MAX_INT_BOUND);
//
//
//            model.arithm(x1, "<", x2).post();
//            model.arithm(x2, "<", x3).post();
//            model.arithm(x3, "<", x1).post();
//
//        Solver solver =  model.getSolver();
//
//          return solver.solve();
        return true;
    }
}
