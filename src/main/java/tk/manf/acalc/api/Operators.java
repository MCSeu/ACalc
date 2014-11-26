package tk.manf.acalc.api;

import java.util.HashMap;
import java.util.Map;
import tk.manf.acalc.api.operators.AdditionOperator;
import tk.manf.acalc.api.operators.DivisionOperator;
import tk.manf.acalc.api.operators.MultiplicationOperator;
import tk.manf.acalc.api.operators.SubtractionOperator;

public final class Operators {
    private static final Map<String, Operator> handlers;

    static {
        handlers = new HashMap<>();
        // Basic Math
        register(new AdditionOperator());
        register(new SubtractionOperator());
        register(new MultiplicationOperator());
        register(new DivisionOperator());
    }
    
    public static void register(Operator op) {
        handlers.put(op.getIdent(), op);
    }
    
    public static Operator get(String op) {
        if (!handlers.containsKey(op)) {
            throw new IllegalArgumentException("Unknown Operator " + op);
        }
        return handlers.get(op);
    }
}