import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class StringProcessor {

    /**
     * Checks if the provided password is strong.
     * A strong password must contain at least one uppercase letter,
     * one lowercase letter, one digit, and one special symbol.
     */
    public boolean isStrongPassword(String password) {
        if (password == null || password.length() < 8) {
            return false;
        }

        boolean Uppercase = false;
        boolean Lowercase = false;
        boolean Digit = false;
        boolean Special = false;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                Uppercase = true;
            } else if (Character.isLowerCase(c)) {
                Lowercase = true;
            } else if (Character.isDigit(c)) {
                Digit = true;
            } else if (!Character.isLetterOrDigit(c)) {
                Special = true;
            }
        }

        return Uppercase && Lowercase && Digit && Special;
    }

    /** Counts the number of digits in the given sentence. */
    public int calculateDigits(String sentence) {
        int counter = 0;

        for (char c : sentence.toCharArray()) {
            if (Character.isDigit(c)) {
                counter++;
            }
        }

        return counter;
    }

    /** Calculates the number of words in the given sentence.
      * Words are considered separated by spaces. */
    public int calculateWords(String sentence) {
        if (sentence == null || sentence.trim().isEmpty()) {
            return 0;
        }

        // Split the sentence into words based on spaces
        String[] words = sentence.trim().split("\\s+");
        return words.length;
    }

    /** Given a string containing an arithmetic expression, calculates and returns the result.
      * The expression can contain numbers, arithmetic operations (+, -, *, /), and brackets. */
    public double calculateExpression(String expression) {
        return evaluate(expression);
    }

    // Helper method to evaluate a simple arithmetic expression with brackets
    private double evaluate(String expr) {
        List<Double> values = new ArrayList<>();
        List<Character> ops = new ArrayList<>();

        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);
            if (c == ' ') continue;

            if (Character.isDigit(c) || c == '.') {
                StringBuilder sb = new StringBuilder();
                while (i < expr.length() && (Character.isDigit(expr.charAt(i)) || expr.charAt(i) == '.'))
                    sb.append(expr.charAt(i++));
                i--; // Move back since for-loop increments i
                values.add(Double.parseDouble(sb.toString()));
            } else if (c == '(') {
                ops.add(c);
            } else if (c == ')') {
                while (ops.get(ops.size() - 1) != '(')
                    values.add(applyOp(ops.remove(ops.size() - 1), values.remove(values.size() - 1), values.remove(values.size() - 1)));
                ops.remove(ops.size() - 1); // Remove '('
            } else if ("+-*/".indexOf(c) >= 0) {
                while (!ops.isEmpty() && hasPrecedence(c, ops.get(ops.size() - 1)))
                    values.add(applyOp(ops.remove(ops.size() - 1), values.remove(values.size() - 1), values.remove(values.size() - 1)));
                ops.add(c);
            }
        }

        while (!ops.isEmpty())
            values.add(applyOp(ops.remove(ops.size() - 1), values.remove(values.size() - 1), values.remove(values.size() - 1)));

        return values.get(0); // Final result
    }

    // Method to check operator precedence
    private boolean hasPrecedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')') {
            return false;
        }
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-')) {
            return false;
        }
        return true;
    }

    // Method to apply an operator to two operands
    private double applyOp(char op, double b, double a) {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0) {
                    throw new UnsupportedOperationException("Cannot divide by zero");
                }
                return a / b;
        }
        return 0;
    }
}
