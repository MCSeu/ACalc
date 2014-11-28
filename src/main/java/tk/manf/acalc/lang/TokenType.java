package tk.manf.acalc.lang;

public enum TokenType {
    NUMBER,
    OPERATOR,
    PARENTHESIS_OPEN, PARENTHESIS_CLOSED,
    CURLY_OPEN, CURLY_CLOSED,
    FUNCTION_NAME, 
    COMMENT;

    public static TokenType of(char c) {
        switch (c) {
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                return NUMBER;
            case '+':
            case '-':
            case '*':
            case ':':
                return OPERATOR;
            case '(': return PARENTHESIS_OPEN;
            case ')': return PARENTHESIS_CLOSED;
            case '[': return CURLY_OPEN;
            case ']': return CURLY_CLOSED;
            case ' ': return COMMENT;
        }
        if(Character.isLetter(c)) {
            // Assume Function
            return FUNCTION_NAME;
        }
        throw new IllegalArgumentException("Character " + c + " is unknown!");
    }
}