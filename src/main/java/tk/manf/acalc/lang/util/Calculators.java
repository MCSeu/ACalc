package tk.manf.acalc.lang.util;

import tk.manf.acalc.api.Calculator;
import tk.manf.acalc.api.calculators.PostfixCalculator;
import tk.manf.acalc.lang.ExpressionType;
import tk.manf.util.AbstractFactory;

public class Calculators extends AbstractFactory<ExpressionType, Calculator> {
    private Calculators() {
        super("Calculator");
        modify().register(new PostfixCalculator());
    }

    public static Calculator resolve(ExpressionType type) {
        return instance.get(type);
    }
    
    public static UnsafeFactory<ExpressionType, Calculator> modify() {
        return instance.unsafe();
    }

    private static final Calculators instance = new Calculators();
}