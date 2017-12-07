package constraints;

import java.util.Set;

public class ConstraintSystem {

    public Set<LinearConstraint> getConstraints() {
        return constraints;
    }

    public Set<String> getVars() {
        return vars;
    }

    private Set<LinearConstraint> constraints;
    private Set<String> vars;

    @Override
    public String toString() {
        return "constraints.ConstraintSystem{" +
                "constraints=" + constraints.toString() +
                ", vars=" + vars +
                '}';
    }
}
