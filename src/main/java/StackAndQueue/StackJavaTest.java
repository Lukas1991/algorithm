package StackAndQueue;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;



public class StackJavaTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Stack stack = new Stack();
		stack.push(1);
		stack.push(2);

		if(!stack.empty()){
			int tmp= (int) stack.pop();
			System.out.println(tmp);			
		}
		System.out.println(stack.size());
		
		
		Queue q =  new LinkedList();
		q.add(1);
		q.poll();
		
		q.size();
		
		LinkedList list =  new LinkedList();
		
		HashMap map = new HashMap();
		int key=1,value=1;
		map.put(key, value);
		map.containsKey(key);
		map.get(key);
		
		
		String StringThatCouldBeANumberOrNot = "26263Hello"; //will throw exception
		String StringThatCouldBeANumberOrNot2 = "26263"; //will not throw exception
		try {
		      int foo = Integer.parseInt(StringThatCouldBeANumberOrNot);
		} catch (NumberFormatException e) {
		      //Will Throw exception!
		      //do something! anything to handle the exception.
		}

		
		
		
	}

}
