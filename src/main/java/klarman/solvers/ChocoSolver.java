package klarman.solvers;

import klarman.TCSProblem;
import klarman.constraints.Constraint;
import klarman.TCSSolution;
import klarman.TCSSolutionMap;
import klarman.ontology.Event;
import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solution;
import org.chocosolver.solver.Solver;
import org.chocosolver.solver.variables.IntVar;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static klarman.constraints.ConstraintVocabulary.inv;

public class ChocoSolver extends TCSSolver {

    private Model model;
    private Map<String, IntVar> varMap;

    public ChocoSolver(TCSProblem problem) {

        super(problem);

        this.varMap = new HashMap<>();

        convertToChoco();

    }

    //Choco requires imposes Integer bounds on the variable values.
    //This might lead to problems with input constants being of type long.
    //It is recommended to keep the precision level on the level of days or hours (i.e. 3-4) when using this solver.

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

        for (Constraint constraint : constraintSystem.getConstraints()) {

            String var1 = constraint.getLhs().getVariable();
            String var2 = constraint.getRhs().getVariable();
            String op = constraint.getOperator();
            int const1 = (int) constraint.getLhs().getConstant();
            int const2 = (int) constraint.getRhs().getConstant();

            IntVar intVar1 = varMap.get(var1);
            IntVar intVar2 = varMap.get(var2);

            if (intVar1!=null && intVar2!= null) {
                model.arithm(intVar1, op, intVar2, "+", const2-const1).post();
            }
            if (intVar1!=null && intVar2== null) {
                model.arithm(intVar1, op, const2-const1).post();
            }
            if (intVar1==null && intVar2!= null) {
                model.arithm(intVar2, inv(op), const1-const2).post();
            }
        }
    }

    @Override
    public boolean consistency() {

        Solver solver =  model.getSolver();
        solver.reset();
        return solver.solve();
    }

    public TCSSolutionMap findSolution() {

        Solver solver =  model.getSolver();
        solver.reset();
        Solution solution = solver.findSolution();

        TCSSolutionMap eventSolutions = new TCSSolutionMap();

        Map<String, Event> events = ontology.getEvents();
        Set<String> eventIds = events.keySet();
        for (String eventId : eventIds) {
            IntVar var1 = varMap.get(events.get(eventId).getBegVar());
            IntVar var2 = varMap.get(events.get(eventId).getEndVar());
            eventSolutions.put(eventId, new TCSSolution(eventId, solution.getIntVal(var1), solution.getIntVal(var2), this.converter));
        }


        return eventSolutions ;
    }
}
