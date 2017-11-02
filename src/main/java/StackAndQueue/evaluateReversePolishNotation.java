package StackAndQueue;

import java.util.Stack;

public class evaluateReversePolishNotation {

	/**
	 * postfix expression convert to infis expression
	 * use a stack
	 * when meet a number, push it into the stack.
	 * when meet an operator, pop two numbers from stack, do the calculation, push back the result
	 */
	public int evalRPN(String[] tokens) {
		Stack<Integer> stack = new Stack<>();

		for (String token : tokens) {
			if (isOperator(token)) {
				int a = stack.pop();
				int b = stack.pop();
				stack.push(cal(b, a, token));
			} else {
				stack.push(Integer.valueOf(token));
			}
		}

		return stack.peek();
	}

	boolean isOperator(String s) {
		return s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/");
	}

	int cal(int a1, int a2, String s) {
		if (s.equals("+")) {
			return a1 + a2;
		} else if (s.equals("-")) {
			return a1 - a2;
		} else if (s.equals("*")) {
			return a1 * a2;
		} else {
			return a1 / a2;
		}
	}
	
	public static void main(String[] args) {

	}

}
