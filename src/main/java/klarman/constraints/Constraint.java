package klarman.constraints;

public class TCSConstraint {

    public TCSExpression getLhs() {
        return lhs;
    }

    public TCSExpression getRhs() {
        return rhs;
    }

    public String getOperator() {
        return operator;
    }

    private TCSExpression lhs;
    private TCSExpression rhs;
    private String operator;

    //TO BE READ AS: "(lhs) operator (rhs)"


    public TCSConstraint(TCSExpression lhs, String operator, TCSExpression rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
        this.operator = operator;
    }

    @Override
    public String toString() {
        return lhs.toString() + " " + operator + " " + rhs.toString();
    }
}
