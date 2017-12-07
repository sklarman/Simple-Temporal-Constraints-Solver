package ontology;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OntologyTest {

    @Test
    public void createInstantTwiceCompareAliases(){
        Ontology ontology = new Ontology();

        TimeInstant instant1 = ontology.addInstant("http://test/instant");
        TimeInstant instant2 = ontology.addInstant("http://test/instant");

        assertTrue(instant1.getAlias().equals(instant2.getAlias()));
    }

    @Test
    public void createTwoInstantsCompareAliases(){
        Ontology ontology = new Ontology();

        TimeInstant instant1 = ontology.addInstant("http://test/instant1");
        TimeInstant instant2 = ontology.addInstant("http://test/instant2");

        assertTrue(!instant1.getAlias().equals(instant2.getAlias()));
    }

    @Test
    public void addEventCountVars(){
        Ontology ontology = new Ontology();

        TimeInterval interval = ontology.addInterval("http://test/interval");

        ontology.addEvent("http://test/event", interval);

        assertEquals(2, ontology.getEventVars().size());
    }

}