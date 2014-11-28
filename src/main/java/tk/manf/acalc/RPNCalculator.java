package tk.manf.acalc;

import java.util.Deque;
import java.util.LinkedList;
import tk.manf.acalc.api.Token;
import tk.manf.acalc.api.readers.TokenReader;

//TODO: Use TokenReader
public class RPNCalculator {
    public static double solve(String rng) {
        final Deque<Double> stack = new LinkedList<>();
        final TokenReader reader = new TokenReader(rng);
        
        while (reader.hasNext()) {
            final Token t = reader.next();
            switch (t.getType()) {
                case NUMBER:
                    stack.addLast(t.asDouble());
                    break;
                case OPERATOR:
                    double b = stack.removeLast();
                    double a = stack.removeLast();
                    stack.addLast(t.asOperator().mix(a, b));
                    break;
                case COMMENT:
                    // Ignore Comments
                    continue;
                default:
                    throw new IllegalArgumentException("Undefined Expression: '" + t.getExpression() + "'(" + t.getType() + ")");
            }
        }
        if (stack.size() != 1) {
            throw new IllegalArgumentException("Missing Expression!");
        }
        return stack.getLast();
    }
}