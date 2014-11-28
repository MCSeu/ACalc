package tk.manf.acalc;

import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import tk.manf.acalc.lang.Expression;
import tk.manf.acalc.lang.ExpressionType;

import static org.junit.Assert.*;
import tk.manf.acalc.api.Calculator;
import tk.manf.acalc.api.calculators.Calculators;
/**
 * Test to test basic expressions
 *
 * @author Björn 'manf' Heinrichs
 */
public class MathTest {
    private final Map<String, Double> expressions;

    public MathTest() {
        this.expressions = new HashMap<>();
    }

    @Before
    public void init() {
        // basic +
        assertCalculation("5+4", 5 + 4);
        // basic + with spaces
        assertCalculation("5 + 4", 5 + 4);
        
        // basic -
        assertCalculation("5-4", 5 - 4);
        // basic - with spaces
        assertCalculation("5 - 4", 5 - 4);
        
        // basic *
        assertCalculation("5*4", 5 * 4);
        // basic * with spaces
        assertCalculation("5 * 4", 5 * 4);
        
        // basic -
        assertCalculation("5:4", 5.0 / 4); // Use Double precision here
        // basic - with spaces
        assertCalculation("5 : 4", 5.0 / 4); // Use Double precision here
        
        // Brackets and misc operators
        assertCalculation("(5+5) * 4", (5 + 5) * 4);
        
        // Preserve precedence
        assertCalculation("(5+5) * 4 + 4", (5+5) * 4 + 4);
    }

    public void toDo() {
        // Parenthesis without content
        // assertError("() 4 + 4"); 
        // assertError("* * 4"); // TODO: Throw human readable error
    }
    
    @Test
    public void test() {
        final ACalc prepocessor = new ACalc();
        for (Map.Entry<String, Double> e : expressions.entrySet()) {
            final Expression expression = new Expression(ExpressionType.POSTFIX, prepocessor.parse(e.getKey()));
            
            // Resolve Calculator based on the expression and it's type
            final Calculator calc = Calculators.resolve(expression.getType());
            assertEquals(e.getValue(), calc.calc(expression), 0.0);
        }
    }
    
    private void assertCalculation(String expression, double result) {
        expressions.put(expression, result);
    }
}