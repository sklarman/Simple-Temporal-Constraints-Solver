package klarman;

import java.util.Collection;
import java.util.HashMap;

public class TCSSolutionMap extends HashMap<String, TCSSolution> {

    @Override
    public String toString() {
        Collection<TCSSolution> solutions = values();
        String result = "";
        for (TCSSolution solution : solutions) {
            result += solution.toString() + "\n";
        }
        return result;
    }
}
