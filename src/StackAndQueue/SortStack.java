package StackAndQueue;
import java.util.Iterator;
import java.util.Stack;



public class SortStack {

	/**
	 * @param args
	 */
	
	/*
	 * 3.6
	 * sort a stock in ascending order(with biggest items on top)
	 * 
	 * we pop an element t from s1, we compare t to the top of s2. if t>s2, we push t onto s2.
	 * Otherwise we push elements from s2 onto s1 until we find a valid place to insert t
	 * 
	 * O(N^2) time and O(N) space
	 * 
	 * */
	public static Stack sort(Stack<Integer> s1){
		Stack<Integer> s2= new Stack<Integer>();
		
		while(!s1.empty()){
			int tmp = s1.pop();
			while(!s2.empty() && s2.peek() > tmp){
				s1.push(s2.pop());
			}
			s2.push(tmp);
		}
		
		return s2;
		
		
		
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Stack<Integer> s1= new Stack<Integer>();
		s1.push(10);
		s1.push(5);
		s1.push(12);
		s1.push(8);
		s1.push(1);
		s1.push(3);
		
		
		Iterator it = s1.iterator();
		while(it.hasNext())
			System.out.println(it.next());
		
		Stack<Integer> s2= sort(s1);
		System.out.println("------------------");
		Iterator it2 = s2.iterator();
		while(it2.hasNext())
			System.out.println(it2.next());
			
			
		
		
		
		
		
	}

}
