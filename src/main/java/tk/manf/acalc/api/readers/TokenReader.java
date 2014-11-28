package tk.manf.acalc.api.readers;

import tk.manf.acalc.api.Token;
import tk.manf.acalc.api.TokenType;

public class TokenReader {
    private final String expression;
    private int index;

    public TokenReader(String expression) {
        this.expression = expression;
        this.index = 0;
    }
    
    public boolean hasNext() {
        return expression.length() > index;
    }
    
    public Token next() {
        TokenType type = null;
        char c;
        final int start = index;
        while (hasNext()) {
            c = expression.charAt(index);
            // Parse char
            final TokenType tar = TokenType.of(c);
            // Assign default value
            type = type == null ? tar : type;
            // Parse until we found a different token
            if (type != tar) {
                break;
            }
            // Increment index
            index++;
        }
        return new Token(type, expression.substring(start, index));
    }
}