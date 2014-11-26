package tk.manf.acalc.rpn;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import tk.manf.acalc.api.Token;
import tk.manf.acalc.api.TokenType;

public class RPN {
    public double solve(String rng) {
        final StringBuilder sb = new StringBuilder(rng);
        final Deque<Double> stack = new LinkedList<>();
        Token t;
        while (sb.length() > 0) {
            t = parse(sb);
            switch (t.getType()) {
                case NUMBER:
                    stack.addLast(t.asDouble());
                    break;
                case OPERATOR:
                    double b = stack.removeLast();
                    double a = stack.removeLast();
                    stack.addLast(t.asOperator().mix(a, b));
                    break;
                default:
                    throw new IllegalArgumentException("Undefined Expression: " + t.getExpression());
            }
        }
        if(stack.size() != 1) {
            throw new IllegalArgumentException("Missing Expression!");
        }
        return stack.getLast();
    }

    private Token parse(StringBuilder sb) {
        final int i = indexOf(sb, " ");
        // parse token
        final String token = sb.substring(0, i);
        // delete parsed tokens
        sb.delete(0, i);
        
        // skip whitespace
        if(token.length() == 0 ){
            return parse(sb);
        }
        return new Token(TokenType.of(token.charAt(0)), token.trim());
    }

    private int indexOf(StringBuilder sb, CharSequence c) {
        final int o = sb.indexOf(" ");
        if (o == -1) {
            return sb.length();
        }
        return o + 1;
    }
}