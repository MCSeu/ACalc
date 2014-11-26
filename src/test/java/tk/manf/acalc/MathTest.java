package tk.manf.acalc;

import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;
import tk.manf.acalc.rpn.RPN;

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
    }

    @Test
    public void test() {
        final ACalc prepocessor = new ACalc();
        final RPN calculator = new RPN();
        for (Map.Entry<String, Double> e : expressions.entrySet()) {
            final String expression = prepocessor.parse(e.getKey());
            System.out.println(" !" + expression);
            assertEquals(e.getValue(), calculator.solve(expression), 0.0);
        }
    }

    private void assertCalculation(String expression, double result) {
        expressions.put(expression, result);
    }
}
