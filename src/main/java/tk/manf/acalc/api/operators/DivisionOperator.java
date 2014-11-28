package tk.manf.acalc.api.operators;

class DivisionOperator extends AbstractOperator {
    DivisionOperator() {
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