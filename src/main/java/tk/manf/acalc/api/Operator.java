package tk.manf.acalc.api;

public interface Operator {
    public String getIdent();
    public int getPrecedence();
    public double mix(double a, double b);
    public boolean isLeftAssociative();
}
