package klarman.constraints;

import klarman.ontology.TimeInterval;
import org.junit.jupiter.api.Test;

import static klarman.constraints.ConstraintVocabulary.LEQ;
import static org.junit.jupiter.api.Assertions.*;

class ConstraintSystemTest {

    @Test
    public void addAnInterval(){
        ConstraintSystem system = new ConstraintSystem();
        TimeInterval interval = new TimeInterval("http://test/interval");

        system.addInterval(interval);
        assertEquals(1, system.getConstraints().size());
    }

    @Test
    public void addIntervalTwice(){
        ConstraintSystem system = new ConstraintSystem();
        TimeInterval interval1 = new TimeInterval("http://test/interval");
        TimeInterval interval2 = new TimeInterval("http://test/interval");

        system.addInterval(interval1);
        system.addInterval(interval2);
        assertEquals(1, system.getConstraints().size());
    }

    @Test
    public void addTwoIntervals(){
        ConstraintSystem system = new ConstraintSystem();
        TimeInterval interval1 = new TimeInterval("http://test/interval1");
        TimeInterval interval2 = new TimeInterval("http://test/interval2");

        system.addInterval(interval1);
        system.addInterval(interval2);
        assertEquals(2, system.getConstraints().size());
    }

    @Test
    public void addConstraint(){
        ConstraintSystem system = new ConstraintSystem();

        system.addConstraint("x", 0, LEQ,"y", 1);

        assertEquals(1, system.getConstraints().size());
    }

    @Test
    public void addConstraintTwice(){
        ConstraintSystem system = new ConstraintSystem();

        system.addConstraint("x", 0, LEQ,"y", 1);
        system.addConstraint("x", 0, LEQ,"y", 1);

        assertEquals(1, system.getConstraints().size());
    }

}