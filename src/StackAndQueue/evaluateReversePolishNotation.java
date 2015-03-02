package StackAndQueue;

import java.util.Stack;

public class evaluateReversePolishNotation {

	/**postfix expression convert to infis expression
	 * use a stack
	 * when meet a number, push it into the stack.
	 * when meet an operator, pop two numbers from stack, do the calculation, push back the result 
	 * @param args
	 */
	public int evalRPN(String[] tokens) {
		int result = 0;
		String operators = "+-*/";
		
		Stack<String> stack = new Stack<String>();
		
		for(String str: tokens){
			//if number, push
			if(!operators.contains(str)){
				stack.push(str);
			}else{
				//else calulate top 2 numbers, and push result back
				int a = Integer.valueOf(stack.pop());
				int b = Integer.valueOf(stack.pop());
				
				switch(str){
				case "+":
					stack.push(String.valueOf(a+b));
					break;
				case "-":
					stack.push(String.valueOf(b-a));
					break;
				case "*":
					stack.push(String.valueOf(a*b));
					break;
				case "/":
					stack.push(String.valueOf(b/a));
					break;
				}
								
			}
			
			
		}

		result = Integer.valueOf(stack.pop());
		return result;
		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
