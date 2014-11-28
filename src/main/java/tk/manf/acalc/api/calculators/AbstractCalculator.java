package tk.manf.acalc.api.calculators;

import tk.manf.acalc.api.Calculator;
import tk.manf.acalc.lang.Expression;

public abstract class AbstractCalculator implements Calculator {
    @Override
    public final double calc(Expression expr) {
        if(expr.getType() == getIdent()) {
            return solve(expr);
        }
        throw new IllegalArgumentException("Type mismatching. Expected " + getIdent().name() + " but " + expr.getType().name() + " given");
    }

    protected abstract double solve(Expression expr);
}