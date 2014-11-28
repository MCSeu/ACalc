package tk.manf.acalc.api.operators;

public class AdditionOperator extends AbstractOperator {
    public AdditionOperator() {
        super("+");
    }

    @Override
    public double mix(double a, double b) {
        return a + b;
    }

    @Override
    public int getPrecedence() {
        return 2;
    }
}