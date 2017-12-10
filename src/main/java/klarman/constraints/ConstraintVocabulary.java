package klarman.constraints;

import java.util.HashMap;
import java.util.Map;

public final class ConstraintVocabulary {

    public static final String EQ = "=";
    public static final String LEQ = "<=";
    public static final String GEQ = ">=";
    public static final String LESS = "<";
    public static final String GREATER = ">";

    private static final Map<String, String> inverse = new HashMap<String, String>() {{
       put(EQ, EQ);
       put(LEQ, GEQ);
       put(GEQ, LEQ);
       put(LESS, GREATER);
       put(GREATER, LESS);
    }};

    public static String inv(String op) {
        return inverse.get(op);
    }

}
