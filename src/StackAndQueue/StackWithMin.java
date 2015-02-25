package StackAndQueue;
import java.util.Stack;



public class StackWithMin extends Stack<Integer>{

	/**
	 * @param args
	 */
	Stack<Integer> s2;
	
	public StackWithMin(){
		s2 = new Stack<Integer>();
	}
	
	public void push(int val){
		if(val <= min())
			s2.push(val);
		super.push(val);
		
	}
	
	public int min(){
		if(s2.empty())
			return Integer.MIN_VALUE;
		else
			return s2.peek();
		
		
	}
	
	public Integer pop(){
		int val = super.pop();
		if(val == min())
			s2.pop();
		
		return val;
		
	}
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
