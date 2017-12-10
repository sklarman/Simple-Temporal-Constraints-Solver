package klarman.constraints;

public class TCSExpression {

    public String getVariable() {
        return variable;
    }

    public long getConstant() {
        return constant;
    }

    private String variable;
    private long constant;

    //TO BE READ AS: "variable + constant"

    public TCSExpression(String variable, long constant) {
        this.variable = variable;
        this.constant = constant;
    }

    @Override
    public String toString() {
        if (variable.equals("")) {
            return String.valueOf(constant);
        }
        if (constant == 0) {
            return variable ;
        }
        return variable + " + " + constant;
    }
}
