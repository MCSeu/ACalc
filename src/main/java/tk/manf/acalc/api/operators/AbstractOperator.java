package tk.manf.acalc.api.operators;

import tk.manf.acalc.api.Operator;

public abstract class AbstractOperator implements Operator {
    private final String ident;

    protected AbstractOperator(String ident) {
        this.ident = ident;
    }
    
    @Override
    public final String getIdent() {
        return ident;
    }

    @Override
    public boolean isLeftAssociative() {
        return true;
    }
}