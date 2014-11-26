package tk.manf.acalc.api;

public class Token {
    private final TokenType type;
    private final String expr;

    public Token(TokenType type, Object expr) {
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