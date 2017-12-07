package constraints;

public class LinearConstraint {

    public ConstraintExpression getLhs() {
        return lhs;
    }

    public ConstraintExpression getRhs() {
        return rhs;
    }

    public String getOperator() {
        return operator;
    }

    private ConstraintExpression lhs;
    private ConstraintExpression rhs;
    private String operator;

    public LinearConstraint(ConstraintExpression lhs, ConstraintExpression rhs, String operator) {
        this.lhs = lhs;
        this.rhs = rhs;
        this.operator = operator;
    }

    @Override
    public String toString() {
        return "constraints.LinearConstraint{" +
                "lhs=" + lhs +
                ", rhs=" + rhs +
                ", operator='" + operator + '\'' +
                '}';
    }
}
