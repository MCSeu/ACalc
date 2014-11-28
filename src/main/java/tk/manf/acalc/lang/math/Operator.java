package tk.manf.acalc.lang.math;

import tk.manf.util.Identifiable;

public interface Operator extends Identifiable<String>{
    public int getPrecedence();
    public double mix(double a, double b);
    public boolean isLeftAssociative();
}