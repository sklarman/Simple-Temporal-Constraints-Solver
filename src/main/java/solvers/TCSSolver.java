package solvers;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solver;
import org.chocosolver.solver.exception.ContradictionException;
import org.chocosolver.solver.variables.IntVar;

public abstract class TCSSolver {

 //   public void chocoSolver() {

//            System.out.println("ChocoSolver test");
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
//            try {
//               Solver solver =  model.getSolver();
//               solver.propagate();
//            } catch (ContradictionException e) {
//                e.printStackTrace();
//            }

  //  }
}
