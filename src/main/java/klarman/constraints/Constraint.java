package klarman.constraints;

public class Constraint {

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

    //TO BE READ AS: "(lhs) operator (rhs)"


    public Constraint(ConstraintExpression lhs, String operator, ConstraintExpression rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
        this.operator = operator;
    }

    @Override
    public String toString() {
        return lhs.toString() + " " + operator + " " + rhs.toString();
    }
}
