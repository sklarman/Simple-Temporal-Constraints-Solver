package klarman.constraints;

import klarman.ontology.TimeInterval;
import java.util.HashSet;
import java.util.Set;

import static klarman.constraints.ConstraintVocabulary.LEQ;

public class ConstraintSystem {

    public Set<Constraint> getConstraints() {
        return constraints;
    }

    public Set<String> getVars() {
        return vars;
    }

    private Set<Constraint> constraints;

    private Set<String> vars;

    public ConstraintSystem() {

        this.constraints = new HashSet<>();
        this.vars = new HashSet<>();

    }

    public void addConstraint(String lvar, long lconst, String operator, String rvar, long rconst) {
        ConstraintExpression lhs = new ConstraintExpression(lvar, lconst);
        ConstraintExpression rhs = new ConstraintExpression(rvar, rconst);
        Constraint constraint = new Constraint(lhs, operator, rhs);

        for (Constraint existingConstraint : constraints) {
            if (    existingConstraint.getLhs().getVariable().equals(lvar) &&
                    existingConstraint.getRhs().getVariable().equals(rvar) &&
                    existingConstraint.getLhs().getConstant()==lconst &&
                    existingConstraint.getRhs().getConstant()==rconst) {
                return;
            }
        }

        this.constraints.add(constraint);
        if (!lhs.getVariable().equals("")) { vars.add(lhs.getVariable()); }
        if (!rhs.getVariable().equals("")) { vars.add(rhs.getVariable()); }
    }

    public void addInterval(TimeInterval interval) {
        addConstraint(interval.getBegVar(), 0, LEQ, interval.getEndVar(), 0);
    }

    @Override
    public String toString() {
        String output = "";
        for (Constraint constriant : constraints) {
            output += constriant.toString() + "\n";
        }
        return output;
    }
}
