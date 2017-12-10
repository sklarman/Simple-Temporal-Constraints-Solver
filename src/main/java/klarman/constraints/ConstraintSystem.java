package klarman.constraints;

import klarman.ontology.TimeInterval;
import java.util.HashSet;
import java.util.Set;

import static klarman.constraints.TCSConstraintVocabulary.LEQ;

public class TCSConstraintSystem {

    public Set<TCSConstraint> getConstraints() {
        return constraints;
    }

    public Set<String> getVars() {
        return vars;
    }

    private Set<TCSConstraint> constraints;

    private Set<String> vars;

    public TCSConstraintSystem() {

        this.constraints = new HashSet<>();
        this.vars = new HashSet<>();

    }

    public void addConstraint(String lvar, long lconst, String operator, String rvar, long rconst) {
        TCSExpression lhs = new TCSExpression(lvar, lconst);
        TCSExpression rhs = new TCSExpression(rvar, rconst);
        TCSConstraint constraint = new TCSConstraint(lhs, operator, rhs);

        for (TCSConstraint existingConstraint : constraints) {
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
        for (TCSConstraint constriant : constraints) {
            output += constriant.toString() + "\n";
        }
        return output;
    }
}
