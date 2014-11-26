package tk.manf.acalc;

import tk.manf.acalc.api.Token;
import tk.manf.acalc.api.TokenType;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;
import tk.manf.acalc.api.Operator;
import tk.manf.acalc.rpn.RPN;

public class ACalc {
    private final StringBuilder output;
    private final Queue<Token> stack;

    public ACalc() {
        this.output = new StringBuilder();
        this.stack = new LinkedList<>();
    }

    public static void main(String[] args) {
        final String expr = "(5+5) * 4";
        final String rpn = new ACalc().parse(new StringBuilder(expr));
        System.out.println("Parsed: '" + rpn + "'");
        System.out.println("Solved: '" + new RPN().solve(rpn) + "'");
    }

    public String parse(String expr) {
        return parse(new StringBuilder(expr));
    }
    
    private String parse(StringBuilder expr) {
        // clear stuff
        stack.clear();
        output.delete(0, output.length());
        
        Token token;

        // TODO: create token Parser
        while (expr.length() > 0) {
            token = readToken(expr);
            switch (token.getType()) {
                case NUMBER:
                    append(token);
                    break;
                case OPERATOR:
                    handleOperator(token);
                    break;
                case PARENTHESIS_OPEN:
                    stack.add(token);
                    break;
                case PARENTHESIS_CLOSED:
                    handleClosedParenthesis();
                    break;
                case COMMENT:
                    // Ignore
                    continue;
                default:
                    throw new IllegalArgumentException("Unknown Token Type " + token.getType());
            }
        }
        finishStack();
        return output.toString().trim();
    }

    private void finishStack() {
        for (Token t : stack) {
            append(t);
        }
    }

    private void handleClosedParenthesis() {
        try {
            while (true) {
                final Token top = stack.remove();
                if (top.getType() == TokenType.PARENTHESIS_OPEN) {
                    break;
                }
                append(top);
            }
            // TODO: handle functions
        } catch (NoSuchElementException ex) {
            throw new IllegalArgumentException("Mismatched Parentheses!");
        }
    }

    private void handleOperator(Token a) {
        final Token b = stack.peek();
        if (b != null && b.getType() == TokenType.OPERATOR) {
            final Operator o1 = a.asOperator();
            final Operator o2 = b.asOperator();
            // O1 is left asscociative => B
            // O1 < O2 => C
            // 01 = O3 => A
            // => (B(C v A) v !B(C)
            // A B C | E
            // 0 0 0 | 0 
            // 0 0 1 | 1 => C 
            // 0 1 0 | 0
            // 0 1 1 | 1 => C
            // 1 0 0 | 0
            // 1 0 1 | 1 => C
            // 1 1 0 | 1 => A and B
            // 1 1 1 | 1 => C
            
            if (o1.getPrecedence() < o2.getPrecedence() || (o1.isLeftAssociative() && o1.getPrecedence() == o2.getPrecedence())) {
                // Append top of stack
                append(b);
                stack.remove();
            }
        }
        stack.add(a);
    }

    private void append(Token t) {
        output.append(t.getExpression()).append(" ");
    }

    private Token readToken(StringBuilder txt) {
        StringBuilder sb = new StringBuilder();
        TokenType type = null;
        char c = ' ';
        for (int i = 0; i < txt.length(); i++) {
            c = txt.charAt(i);
            if (type == null) {
                type = TokenType.of(c);
            } else if (type != TokenType.of(c)) {
                // Delete already parsed token from InputStream
                txt.delete(0, i);
                // Return parsed Token
                return new Token(type, sb.toString());
            }
            sb.append(c);
        }
        // we could not determin characters type
        txt.deleteCharAt(0);
        return new Token(type, c + "");
    }
}