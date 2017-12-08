package constraints;

import ontology.TimeInterval;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

import static constraints.ConstraintVocabulary.LEQ;

public class ConstraintSystem {

    public Set<LinearConstraint> getConstraints() {
        return constraints;
    }

    public Set<String> getVars() {
        return vars;
    }

    private Set<LinearConstraint> constraints;

    private Set<String> vars;

    public ConstraintSystem() {

        this.constraints = new HashSet<>();
        this.vars = new HashSet<>();

    }

    public void addConstraint(String lvar, long lconst, String operator, String rvar, long rconst) {
        ConstraintExpression lhs = new ConstraintExpression(lvar, lconst);
        ConstraintExpression rhs = new ConstraintExpression(rvar, rconst);
        LinearConstraint constraint = new LinearConstraint(lhs, operator, rhs);

        for (LinearConstraint existingConstraint : constraints) {
            if (    existingConstraint.getLhs().getVariable().equals(lvar) &&
                    existingConstraint.getRhs().getVariable().equals(rvar) &&
                    existingConstraint.getLhs().getConstant()==lconst &&
                    existingConstraint.getRhs().getConstant()==rconst) {
                return;
            }
        }

        this.constraints.add(constraint);
    }

    public void addInterval(TimeInterval interval) {
        addConstraint(interval.getBegVar(), 0, LEQ, interval.getEndVar(), 0);
    }

    @Override
    public String toString() {
        String output = "";
        for (LinearConstraint constriant : constraints) {
            output += constriant.toString() + "\n";
        }
        return output;
    }
}
