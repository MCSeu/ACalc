package tk.manf.acalc;

import tk.manf.acalc.api.Token;
import tk.manf.acalc.api.TokenType;
import java.util.NoSuchElementException;
import tk.manf.acalc.api.Expression;
import tk.manf.acalc.api.Operator;
import tk.manf.acalc.api.readers.TokenReader;
import tk.manf.acalc.collection.Stack;

public class ACalc {
    private final StringBuilder output;
    private final Stack stack;
    
    public ACalc() {
        this.output = new StringBuilder();
        this.stack = new Stack();
    }

    public static void main(String[] args) {
        final String expr = "(5+5) * 4 + 4";
        
        final String rpn = new ACalc().parse(expr);
        System.out.println("Parsing: " + expr);
        System.out.println("Parsed: '" + rpn + "'");
        System.out.println("Solved: '" + RPNCalculator.solve(rpn) + "'");
        
        System.out.println("Reading " + expr);
        final Expression expression = new Expression(expr);
        for(Token token: expression) {
            if(token.getType() == TokenType.COMMENT) {
                continue; 
            }
            System.out.print(token.getExpression() + " ");
        }
        System.out.println("\n");
    }

    public String parse(String expr) {
        // clear stuff
        stack.clear();
        output.delete(0, output.length());
        
        final TokenReader r = new TokenReader(expr);
        while (r.hasNext()) {
            final Token token = r.next();
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
        final Token b = stack.top();
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
}