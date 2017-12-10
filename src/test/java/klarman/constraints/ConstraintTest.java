package klarman.constraints;

import org.junit.jupiter.api.Test;

import static klarman.constraints.ConstraintVocabulary.LEQ;
import static org.junit.jupiter.api.Assertions.*;

class ConstraintTest {

    ConstraintExpression lhs = new ConstraintExpression("x", 0);
    ConstraintExpression rhs = new ConstraintExpression("y", 1);
    Constraint constraint = new Constraint(lhs, LEQ, rhs);

    @Test
    public void createConstraintTestContent1() {

        assertEquals(LEQ, constraint.getOperator());
    }

    @Test
    public void createConstraintTestContent2() {

        assertEquals(0, constraint.getLhs().getConstant());

    }

    @Test
    public void createConstraintTestContent3() {

        assertEquals(1, constraint.getRhs().getConstant());

    }

    @Test
    public void createConstraintTestContent4() {

        assertEquals("x", constraint.getLhs().getVariable());

    }

    @Test
    public void createConstraintTestContent5() {

        assertEquals("y", constraint.getRhs().getVariable());

    }

}