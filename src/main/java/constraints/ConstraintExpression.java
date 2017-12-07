package constraints;

public class ConstraintExpression {

    public String getVariable() {
        return variable;
    }

    public Integer getConstant() {
        return constant;
    }

    private String variable;
    private Integer constant;

    public ConstraintExpression(String variable, Integer constant) {
        this.variable = variable;
        this.constant = constant;
    }

    @Override
    public String toString() {
        return "constraints.ConstraintExpression{" +
                "variable='" + variable + '\'' +
                ", constant=" + constant +
                '}';
    }
}
