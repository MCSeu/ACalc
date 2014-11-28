package tk.manf.acalc.api.calculators;

import tk.manf.acalc.api.Calculator;
import tk.manf.acalc.lang.ExpressionType;
import tk.manf.util.AbstractFactory;

public final class Calculators extends AbstractFactory<ExpressionType, Calculator> {
    private Calculators() {
        super("Calculator");
        unsafe().register(new PostfixCalculator());
    }

    public static Calculator resolve(ExpressionType type) {
        return instance.get(type);
    }
    
    public static UnsafeFactory<ExpressionType, Calculator> modify() {
        return instance.unsafe();
    }

    private static final Calculators instance = new Calculators();
}