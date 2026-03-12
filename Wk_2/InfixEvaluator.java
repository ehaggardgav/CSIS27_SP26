import java.util.Scanner;
import java.util.Stack;

public class InfixEvaluator {

    // Main method to test evaluator with user input
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter an infix expression (e.g., 3 + 4 * (2 - 1)):");
        String expression = scanner.nextLine();

        try {
            int result = evaluateInfix(expression);
            System.out.println("Result: " + result);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        scanner.close();
    }

    /**
     * Evaluates a valid infix expression using two stacks: operands and operators
     * @param expr Infix expression string
     * @return evaluated integer result
     */
    public static int evaluateInfix(String expr) {
        if (expr == null || expr.isEmpty()) {
            throw new IllegalArgumentException("Expression cannot be null or empty");
        }

        Stack<Integer> operandStack = new Stack<>();
        Stack<Character> operatorStack = new Stack<>();

        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);

            if (c == ' ') continue; // ignore spaces

            if (Character.isDigit(c)) {
                // Handle multi-digit numbers
                int num = 0;
                while (i < expr.length() && Character.isDigit(expr.charAt(i))) {
                    num = num * 10 + (expr.charAt(i) - '0');
                    i++;
                }
                operandStack.push(num);
                i--; // adjust for extra increment in while
            } else if (c == '(') {
                operatorStack.push(c); // push '(' to operator stack
            } else if (c == ')') {
                // Pop operators until '('
                while (!operatorStack.isEmpty() && operatorStack.peek() != '(') {
                    applyOperator(operandStack, operatorStack);
                }
                if (operatorStack.isEmpty()) {
                    throw new IllegalArgumentException("Mismatched parentheses");
                }
                operatorStack.pop(); // remove '('
            } else if (isOperator(c)) {
                // While top operator has higher or equal precedence, apply it
                while (!operatorStack.isEmpty() && precedence(operatorStack.peek()) >= precedence(c)) {
                    applyOperator(operandStack, operatorStack);
                }
                operatorStack.push(c);
            } else {
                throw new IllegalArgumentException("Invalid character in expression: " + c);
            }
        }

        // Apply remaining operators
        while (!operatorStack.isEmpty()) {
            if (operatorStack.peek() == '(' || operatorStack.peek() == ')') {
                throw new IllegalArgumentException("Mismatched parentheses");
            }
            applyOperator(operandStack, operatorStack);
        }

        if (operandStack.size() != 1) {
            throw new IllegalArgumentException("Invalid expression: too many operands");
        }

        return operandStack.pop();
    }

    // Apply top operator to top two operands
    private static void applyOperator(Stack<Integer> operands, Stack<Character> operators) {
        if (operands.size() < 2) {
            throw new IllegalArgumentException("Not enough operands for operation");
        }
        int b = operands.pop();
        int a = operands.pop();
        char op = operators.pop();

        switch (op) {
            case '+': operands.push(a + b); break;
            case '-': operands.push(a - b); break;
            case '*': operands.push(a * b); break;
            case '/': 
                if (b == 0) throw new IllegalArgumentException("Division by zero");
                operands.push(a / b); 
                break;
            default: throw new IllegalArgumentException("Unknown operator: " + op);
        }
    }

    // Check if character is an operator
    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    // Return operator precedence
    private static int precedence(char op) {
        switch (op) {
            case '+': case '-': return 1;
            case '*': case '/': return 2;
            default: return 0;
        }
    }
}
