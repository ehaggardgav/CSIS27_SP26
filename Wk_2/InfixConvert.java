import java.util.Stack;
import java.util.Scanner;

public class InfixConvert {
    /**
     * Converts a valid infix expression to postfix using standard order of operations
     * @param infix The infix expression (non-null, non-empty)
     * @return Postfix expression as a string
     * @throws IllegalArgumentException if infix is null or empty
     */
    public static String convertToPostfix(String infix) {
        if (infix == null || infix.isEmpty()) {
            throw new IllegalArgumentException("Infix expression cannot be null or empty");
        }

        // Initialize stacks: one for operands and one for operators
        Stack<Integer> operandStack = new Stack<>();
        Stack<Character> operatorStack = new Stack<>();

        //for-loop to ignore spaces, and handle numbers with more than one digit, convert char to int
        for (int i = 0; i < expr.length(); i++) {  
            char c = expr.charAt(i);
            if (c == ' ') continue; 

            if (Character.isDigit(c)) {
                int num = 0;
                while (i < expr.length() && Character.isDigit(expr.charAt(i))) {
                    num = num * 10 + (expr.charAt(i) - '0');
                    i++;
                }
                operandStack.push(num);
                i--; 
            } 

            //handle left parentheses remove '(' if parenthesis in input are mismatched and ignore unexpected characters
            else if (c == '(') {
                operatorStack.push(c);
            } 
            else if (c == ')') {
                while (!operatorStack.isEmpty() && operatorStack.peek() != '(') {
                    applyOperator(operandStack, operatorStack);
                }
                if (operatorStack.isEmpty()) {
                    throw new IllegalArgumentException("Mismatched parentheses");
                }
                operatorStack.pop(); 
            } 
            else if (isOperator(c)) {
                while (!operatorStack.isEmpty() && precedence(operatorStack.peek()) >= precedence(c)) {
                    applyOperator(operandStack, operatorStack);
                }
                operatorStack.push(c);
            } 
            else {
                
            }
        }

        //apply any remaining operators and check again for mismatched parentheses
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

    

    
}
