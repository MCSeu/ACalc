package tk.manf.acalc.lang;

import java.util.Iterator;
import tk.manf.acalc.lang.Token;
import tk.manf.acalc.lang.readers.TokenReader;

/**
 * Wraps an Expression into a Manager
 * @author Björn 'manf' Heinrichs
 */
public final class Expression implements Iterable<Token> {
    private final ExpressionType type;
    private final String expr;

    public Expression(ExpressionType type, String expr) {
        this.type = type;
        this.expr = expr;
    }

    public ExpressionType getType() {
        return type;
    }
    
    @Override
    public Iterator<Token> iterator() {
        return new TokenReader(expr);
    }
}