package tk.manf.acalc;

import tk.manf.acalc.lang.Token;
import tk.manf.acalc.lang.TokenType;
import java.util.NoSuchElementException;
import tk.manf.acalc.api.Calculator;
import tk.manf.acalc.api.calculators.Calculators;
import tk.manf.acalc.api.operators.Operators;
import tk.manf.acalc.lang.Expression;
import tk.manf.acalc.lang.math.Operator;
import tk.manf.acalc.lang.ExpressionType;
import tk.manf.acalc.lang.readers.TokenReader;
import tk.manf.util.collection.Stack;

public class ACalc {
    private final StringBuilder output;
    private final Stack<Token> stack;
    
    public ACalc() {
        this.output = new StringBuilder();
        this.stack = new Stack<>();
    }

    public static void main(String[] args) {
        final String expr = "(5+5) * 4 + 4";
        
        final String rpn = new ACalc().parse(expr);
        final Calculator calc = Calculators.resolve(ExpressionType.POSTFIX);
        
        System.out.println("Parsing: " + expr);
        System.out.println("Parsed: '" + rpn + "'");
        System.out.println("Solved: '" + calc.calc(new Expression(ExpressionType.POSTFIX, rpn)) + "'");
        
        System.out.println("Reading " + expr);
        final Expression expression = new Expression(ExpressionType.INTFIX, expr);
        for(Token token: expression) {
            if(token.getType() == TokenType.COMMENT) {
                continue; 
            }
            System.out.print(token.getExpression() + " ");
        }
        System.out.println();
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
            final Operator o1 = Operators.resolve(a);
            final Operator o2 = Operators.resolve(b);
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