package StackAndQueue;

import java.util.LinkedList;
import java.util.Stack;

public class BasicCalculator {

    /**
     * 224. Basic Calculator.
     * Implement a basic calculator to evaluate a simple expression string.
     * The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .
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
    public int calculate2Stacks(String s) {
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

    /**
     * 227. Basic Calculator II.
     * The expression string contains only non-negative integers, +, -, *, / operators and empty spaces .
     * "3+2*2" = 7
     * " 3/2 " = 1
     * " 3+5 / 2 " = 5
     * LinkedList is double ended queue
     */
    public int calculate2(String s) {
        LinkedList<Character> ops = new LinkedList<>();
        LinkedList<Integer> values = new LinkedList<>();

        int i = 0;
        while (i < s.length()) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                String number = "" + c;
                i++;
                while (i < s.length() && Character.isDigit(s.charAt(i))) {
                    number += s.charAt(i);
                    i++;
                }

                values.addLast(Integer.valueOf(number));
            } else if (c == '+' || c == '-') {
                ops.addLast(c);
                i++;
            } else if (c == '*' || c == '/') {
                int previous = values.removeLast();
                String number = "";

                i++;
                while (i < s.length() && (s.charAt(i) == ' ' || Character.isDigit(s.charAt(i)))) {
                    if (Character.isDigit(s.charAt(i))) {
                        number += s.charAt(i);
                    }
                    i++;
                }

                int next = Integer.valueOf(number);

                if (c == '*') {
                    values.addLast(previous * next);
                } else {
                    values.addLast(previous / next);
                }
            } else {
                i++;
            }
        }


        int res = values.removeFirst();

        while (!ops.isEmpty()) {
            char c = ops.removeFirst();
            int value = values.removeFirst();
            if (c == '+') {
                res += value;
            } else {
                res -= value;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        BasicCalculator obj = new BasicCalculator();
        System.err.println(obj.calculate("(1+(4+5+2)-3)+(6+8)"));  //23
        System.err.println(obj.calculate(" 2-1 + 2 ")); //3

        System.err.println(obj.calculate2("3+2*2 - 4 / 2")); // 5
    }
}
