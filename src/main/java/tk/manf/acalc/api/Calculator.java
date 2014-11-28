package tk.manf.acalc.api;

import tk.manf.acalc.lang.Expression;
import tk.manf.acalc.lang.ExpressionType;
import tk.manf.util.Identifiable;

/**
 * Calculator to calculate any given Expression
 * @author Björn 'manf' Heinrichs
 */
public interface Calculator extends Identifiable<ExpressionType> {
    public double calc(Expression expr);
}