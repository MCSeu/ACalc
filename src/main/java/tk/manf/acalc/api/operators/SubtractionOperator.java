package tk.manf.acalc.api.operators;

class SubtractionOperator extends AbstractOperator {
    SubtractionOperator() {
        super("-");
    }

    @Override
    public double mix(double a, double b) {
        return a - b;
    }

    @Override
    public int getPrecedence() {
        return 2;
    }
}