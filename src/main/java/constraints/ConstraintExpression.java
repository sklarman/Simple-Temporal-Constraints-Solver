package constraints;

public class ConstraintExpression {

    public String getVariable() {
        return variable;
    }

    public long getConstant() {
        return constant;
    }

    private String variable;
    private long constant;

    //TO BE READ AS: "variable + constant"

    public ConstraintExpression(String variable, long constant) {
        this.variable = variable;
        this.constant = constant;
    }

    @Override
    public String toString() {
        return variable + " + " + constant;
    }
}
