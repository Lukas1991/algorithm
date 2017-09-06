package StackAndQueue;

import java.util.Stack;

public class BasicCalculator {

    /**
     * Implement a basic calculator to evaluate a simple expression string.
     * " 2-1 + 2 " = 3
     * "(1+(4+5+2)-3)+(6+8)" = 23
     */
    //1 stack
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();

        int tmp = 0;
        int preNumber = 0;
        int preSign = 1;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                continue;
            } else if (c == '(') {
                stack.push(tmp);
                stack.push(preSign);
                tmp = 0;
                preNumber = 0;
                preSign = 1;
            } else if (c == '+') {
                tmp = tmp + preSign * preNumber;
                preNumber = 0;
                preSign = 1;
            } else if (c == '-') {
                tmp = tmp + preSign * preNumber;
                preNumber = 0;
                preSign = -1;
            } else if (Character.isDigit(c)) {
                preNumber = preNumber * 10 + c - '0';
            } else if (c == ')') {
                tmp = tmp + preSign * preNumber;
                preNumber = 0;

                int sign = stack.pop();
                tmp = tmp * sign;
                tmp = tmp + stack.pop();
            }
        }

        if (preNumber != 0) {
            return tmp + preSign * preNumber;
        } else {
            return tmp;
        }
    }

    //2 stacks
    public int calculate2(String s) {
        Stack<Character> opStack = new Stack<>();
        Stack<Integer> valueStack = new Stack<>();
        int i = 0;

        while (i < s.length()) {
            char c = s.charAt(i);
            if (c == ' ') {
                i++;
                continue;
            } else if (c == '(' || c == '+' || c == '-') {
                opStack.push(c);
                i++;
            } else if (Character.isDigit(c)) {
                String numbers = c + "";
                i++;
                while (i < s.length() && Character.isDigit(s.charAt(i))) {
                    numbers = numbers + s.charAt(i);
                    i++;
                }

                valueStack.push(Integer.valueOf(numbers));
            } else if (c == ')') {
                int tmp = calculateOneLevel(opStack, valueStack);
                valueStack.push(tmp);
                i++;
            }
        }

        if (opStack.isEmpty()) {
            return valueStack.pop();
        } else {
            return calculateOneLevel(opStack, valueStack);
        }
    }

    private int calculateOneLevel(Stack<Character> opStack, Stack<Integer> valueStack) {
        Stack<Character> ops = new Stack<>();
        Stack<Integer> values = new Stack<>();

        values.push(valueStack.pop());

        while(!opStack.isEmpty() && opStack.peek() != '(') {
            ops.push(opStack.pop());
            values.push(valueStack.pop());
        }

        if (!opStack.isEmpty()) {
            opStack.pop(); //remove '('
        }

        int tmp = values.pop();
        while (!values.isEmpty()) {
            char sign = ops.pop();
            if (sign == '+') {
                tmp = tmp + values.pop();
            } else {
                tmp = tmp - values.pop();
            }
        }
        return tmp;
    }

    public static void main(String[] args) {
        BasicCalculator obj = new BasicCalculator();
        System.err.println(obj.calculate("(1+(4+5+2)-3)+(6+8)"));  //23
        System.err.println(obj.calculate(" 2-1 + 2 ")); //3
    }
}
