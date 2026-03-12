import java.util.Scanner;
import java.util.Stack;

public class InfixEvaluator {

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
     * Method 'evaluateInfix' evaluates valid infix expressions using two stacks: operands and operators
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

            if (c == ' ') continue; //ignore spaces

            if (Character.isDigit(c)) { //to handle multi-digit numbers
                int num = 0;
                while (i < expr.length() && Character.isDigit(expr.charAt(i))) {
                    num = num * 10 + (expr.charAt(i) - '0');
                    i++;
                }
                operandStack.push(num);
                i--; 
            } else if (c == '(') {
                operatorStack.push(c); //push '(' to operator stack
            } else if (c == ')') {
                while (!operatorStack.isEmpty() && operatorStack.peek() != '(') { //while-loop to pop operators until '('
                    applyOperator(operandStack, operatorStack);
                }
                if (operatorStack.isEmpty()) {
                    throw new IllegalArgumentException("Mismatched parentheses");
                }
                operatorStack.pop(); //now remove '('
            } else if (isOperator(c)) {
                //while-loop to handle precedence
                while (!operatorStack.isEmpty() && precedence(operatorStack.peek()) >= precedence(c)) {
                    applyOperator(operandStack, operatorStack);
                }
                operatorStack.push(c);
            } else {
                throw new IllegalArgumentException("Invalid character in expression: " + c);
            }
        }

        //now remaining operators
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

    //Helper 'applyOperator' applies top operator to top two operands
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

    //Boolean 'isOperator' checks if character is an operator
    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    private static int precedence(char op) { //now return operator precedence
        switch (op) {
            case '+': case '-': return 1;
            case '*': case '/': return 2;
            default: return 0;
        }
    }
}
