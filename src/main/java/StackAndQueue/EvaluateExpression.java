package StackAndQueue;

import java.util.Stack;

/**
 * Given an expression string array, return the final result of this expression.
 * The expression contains integer, +, -, *, /, (, ).
 */
public class EvaluateExpression {

    /**
     * Use two stacks, value stack and op stack
     *
     * if meet a number, push to values
     * if meet an operator, if ops.peek() has higher or same preference, calculate the ops in the stacks, and then push it.
     * if (, push
     * if ), calculate the ops in the stacks until meet (
     *
     * In the end, check if stack is empty
     */
    public int evaluate(String expression) {
        char[] arr = expression.toCharArray();

        Stack<Integer> values = new Stack<>();
        Stack<Character> ops = new Stack<>();

        for (int i = 0; i < arr.length; i++) {
            char c = arr[i];
            if (c == ' ')   continue;

            //number
            if (Character.isDigit(c)) {
                String value = "" + c;
                while (i+1 < arr.length && Character.isDigit(arr[i+1])) {
                    i++;
                    value += arr[i];
                }

                values.push(Integer.valueOf(value));
            } else if (c == '(') {
                ops.push(c);
            } else if (c == ')') {

                while (ops.peek() != '(') {
                    int newValue = cal(ops.pop(), values.pop(), values.pop());
                    values.push(newValue);
                }

                ops.pop(); //remove '('

            } else if (isOp(c)) {

                //if c is + or -, and top of ops is * or /
                while (!ops.isEmpty() && hasHigherOrSamePreference(c, ops.peek())) {
                    int newValue = cal(ops.pop(), values.pop(), values.pop());
                    values.push(newValue);
                }

                ops.push(c);
            }
        }

        // ops stack contains + or -
        while (!ops.isEmpty()) {
            int newValue = cal(ops.pop(), values.pop(), values.pop());
            values.push(newValue);
        }

        if (values.isEmpty()) {
            return 0;
        } else {
            return values.pop();
        }
    }

    private int cal(char c, int b, int a) {
        switch (c) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0) {
                    throw new UnsupportedOperationException("Cannot divide by Zero");
                } else {
                    return a / b;
                }
        }
        return 0;
    }

    private boolean isOp(char c) {
        return c == '*' || c == '/' || c == '+' || c == '-';
    }

    //op2 is at the top of stack, return true if op2 has higher or same preference. otherwise return false
    private boolean hasHigherOrSamePreference(char op1, char op2) {
        if ((op2 == '*' || op2 == '/') && (op1 == '+' || op1 == '-')) {
            return true;
        } else if ((op2 == '*' || op2 == '/') && (op1 == '*' || op1 == '/')) {
            return true;
        } else if ((op2 == '+' || op2 == '-') && (op1 == '+' || op1 == '-')) {
            return true;
        } else {
            return false;
        }

        //same logic as above
//        if (op2 == '(' || op2 == ')') {
//            return false;
//        }
//
//        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-')) {
//            return false;
//        } else {
//            return true;
//        }
    }

    public static void main(String[] args) {
        EvaluateExpression obj = new EvaluateExpression();
        int res = obj.evaluate("2 *6- (23+7) / (1+2)"); //2
        System.err.println(res);

        res = obj.evaluate("2 * 6 - ((2 + 3) * 7 - 2) / (1+2)"); //1
        System.err.println(res);

        res = obj.evaluate("(999/3/3/3) + (1+9/3)"); //1
        System.err.println(res);
    }
}
