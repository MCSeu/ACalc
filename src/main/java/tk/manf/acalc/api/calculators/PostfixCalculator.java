package tk.manf.acalc.api.calculators;

import tk.manf.acalc.api.operators.Operators;
import tk.manf.acalc.lang.Token;
import tk.manf.acalc.lang.Expression;
import tk.manf.acalc.lang.ExpressionType;
import tk.manf.util.collection.Stack;

class PostfixCalculator extends AbstractCalculator {
    @Override
    public ExpressionType getIdent() {
        return ExpressionType.POSTFIX;
    }

    @Override
    protected double solve(Expression expr) {
        final Stack<Double> stack = new Stack<>();
        
        for(Token t: expr) {
            switch (t.getType()) {
                case NUMBER:
                    stack.add(t.asDouble());
                    break;
                case OPERATOR:
                    //TODO: Let Operator choose amount
                    double b = stack.remove();
                    double a = stack.remove();
                    stack.add(Operators.resolve(t).mix(a, b));
                    break;
                case COMMENT:
                    // Ignore Comments
                    continue;
                default:
                    throw new IllegalArgumentException("Undefined Expression: '" + t.getExpression() + "'(" + t.getType() + ")");
            }
        }

        // Ensure we have just one number left
        if (stack.size() != 1) {
            throw new IllegalArgumentException("Missing Expression!");
        }

        // remove what is left
        return stack.remove();
    }
}