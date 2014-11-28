package tk.manf.acalc.api;

import java.util.Iterator;
import tk.manf.acalc.api.Token;
import tk.manf.acalc.api.readers.TokenReader;

/**
 * Wraps an Expression into a Manager
 * @author Björn 'manf' Heinrichs
 */
public class Expression implements Iterable<Token> {
    private final String expr;

    public Expression(String expr) {
        this.expr = expr;
    }

    @Override
    public Iterator<Token> iterator() {
        return new TokenReader(expr);
    }
}