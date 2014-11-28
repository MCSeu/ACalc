/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tk.manf.acalc;

import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import tk.manf.acalc.api.readers.TokenReader;

/**
 * Test to test TokenReader
 *
 * @author Björn 'manf' Heinrichs
 */
public class TokenReaderTest {
    private final Map<String, String> expressions;

    public TokenReaderTest() {
        this.expressions = new HashMap<>();
    }

    @Test
    public void test() {
        // Basic expression with spaces
        assertExpression("4 + 4");
        
        // Basic expression without spaces
        assertExpression("8 + 2");
        
        // Basic expression with different operator
        assertExpression("4 - 4");
        
        // Complex Statement
        assertExpression("(5 * 4 - 3) * 2 : 5");
        
    }
    
    private void assertExpression(String expression) {
        final TokenReader r = new TokenReader(expression);
        final StringBuilder sb = new StringBuilder();
        
        while(r.hasNext()) {
            // Append trimmed
            sb.append(r.next().getExpression().trim());
        }
        
        // Replace whitespace in expression
        assertEquals(expression.replace(" ", ""), sb.toString());
    }
}