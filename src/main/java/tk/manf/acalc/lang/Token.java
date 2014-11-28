package tk.manf.acalc.lang;

import tk.manf.acalc.lang.math.Operator;
import tk.manf.acalc.api.operators.Operators;

public final class Token {
    private final TokenType type;
    private final String expr;

    public Token(TokenType type, Object expr) {
        if(type == null) {
            throw new IllegalArgumentException("Type may not be null");
        }
        this.type = type;
        this.expr = expr.toString();
    }

    public TokenType getType() {
        return type;
    }

    public String getExpression() {
        return expr;
    }
    
    public double asDouble() {
        return Double.parseDouble(getExpression());
    }
    
    @Override
    public String toString() {
        return "{" + expr + "}";
    }
}