package tk.manf.acalc.api;

import tk.manf.acalc.util.Operators;

public class Token {
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
    
    public Operator asOperator() {
        return Operators.get(expr);
    }
    
    @Override
    public String toString() {
        return "{" + expr + "}";
    }
}