import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class InfixEvaluatorMid {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter an infix expression:");
        String expression = scanner.nextLine();

        //try-catch method used here (to catch mismached parentheses, invalid characters and division by 0)
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

        // Use ArrayDeque instead of Stack
        Deque<Integer> operandStack = new ArrayDeque<>();
        Deque<Character> operatorStack = new ArrayDeque<>();

        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);

            if (c == ' ') continue;//ignore spaces

            if (Character.isDigit(c)) {//to handle multi-digit numbers
                int num = 0;
                while (i < expr.length() && Character.isDigit(expr.charAt(i))) {
                    num = num * 10 + (expr.charAt(i) - '0');
                    i++;
                }
                operandStack.push(num);
                i--;

            } else if (c == '(') { 
                operatorStack.push(c);//push '(' to operator stack

            } else if (c == ')') {
                while (!operatorStack.isEmpty() && operatorStack.peek() != '(') {//while-loop to pop operators until '('
                    applyOperator(operandStack, operatorStack);
                }
                if (operatorStack.isEmpty()) {
                    throw new IllegalArgumentException("Mismatched parentheses");
                }
                operatorStack.pop(); // remove '('

            } else if (isOperator(c)) {

                while (!operatorStack.isEmpty() //while-loop to handle precedence
                        && precedence(operatorStack.peek()) >= precedence(c)) {
                    applyOperator(operandStack, operatorStack);
                }
                operatorStack.push(c);

            } else {
                throw new IllegalArgumentException("Invalid character: " + c);
            }
        }

        while (!operatorStack.isEmpty()) { //now remaining operators
            if (operatorStack.peek() == '(') {
                throw new IllegalArgumentException("Mismatched parentheses");
            }
            applyOperator(operandStack, operatorStack);
        }

        if (operandStack.size() != 1) {
            throw new IllegalArgumentException("Invalid expression");
        }

        return operandStack.pop();
    }

    //helper 'applyOperator' applies top operator to top two operands
    private static void applyOperator(Deque<Integer> operands,
                                      Deque<Character> operators) {

        if (operands.size() < 2) {
            throw new IllegalArgumentException("Not enough operands");
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
            default:
                throw new IllegalArgumentException("Unknown operator: " + op);
        }
    }

    //boolean 'isOperator' checks if character is one of the valid operators: +, -, *, /
    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    private static int precedence(char op) { //now return operator precedence (higher number shows higher precedence)
        switch (op) {
            case '+': case '-': return 1;
            case '*': case '/': return 2;
            default: return 0;
        }
    }
}