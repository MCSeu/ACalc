package tk.manf.acalc.api.operators;

public class DivisionOperator extends AbstractOperator {
    public DivisionOperator() {
        super(":");
    }

    @Override
    public double mix(double a, double b) {
        return a / b;
    }

    @Override
    public int getPrecedence() {
        return 3;
    }
}