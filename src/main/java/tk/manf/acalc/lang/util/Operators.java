package tk.manf.acalc.lang.util;

import tk.manf.acalc.lang.math.Operator;
import tk.manf.acalc.api.operators.AdditionOperator;
import tk.manf.acalc.api.operators.DivisionOperator;
import tk.manf.acalc.api.operators.MultiplicationOperator;
import tk.manf.acalc.api.operators.SubtractionOperator;
import tk.manf.util.AbstractFactory;

public final class Operators extends AbstractFactory<String, Operator> {
    private Operators() {
        super("Operator");
        // Basic Math
        unsafe().register(new AdditionOperator())
                .register(new SubtractionOperator())
                .register(new MultiplicationOperator())
                .register(new DivisionOperator());
    }

    public static UnsafeFactory<String, Operator> modify() {
        return instance.unsafe();
    }
    
    public static Operator resolve(String op) {
        return instance.get(op);
    }
    
    private static final Operators instance = new Operators();
}