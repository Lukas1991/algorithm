package StackQueue;

import java.util.Stack;

public class QueueByTwoStacks<T> {

	/**Question 3.5
	 * @param args
	 * 
	 * use s2 to reverse the order of the elements, when pop, pop from s2.
	 * we don't need to move everything from s1 to s2 every time. Leave the old elements in s2,
	 * push the new element into s1. when s2 is empty, we'll then transfer all the elements from s1 to s2
	 */
	Stack<T> s1,s2;
	public QueueByTwoStacks(){
		s1=new Stack<T>();
		s2=new Stack<T>();
	}
	
	public int size(){
		return s1.size()+s2.size();
	}
	
	public void add(T value){
		s1.push(value);
	}
	
	public T peek(){
		if(!s2.isEmpty()) return s2.peek();
		else{
			while(!s1.isEmpty()) s2.push(s1.pop());
			return s2.peek();
		}		
	}
	
	public T remove(){
		if(!s2.isEmpty()){
			return s2.pop();
		}else{
			while(!s1.isEmpty()){
				T value=s1.pop();
				s2.push(value);
			System.out.println("pop "+value+" from s1 to s2");	
			}
			return s2.pop();
		}
	}
	
	public static void main(String[] args) {
		QueueByTwoStacks<Integer> test=new QueueByTwoStacks();
		test.add(1);test.add(2);test.add(3);
		System.out.println(test.remove());
		test.add(4);
		//test.remove();test.remove();
		System.out.println(test.remove());
		System.out.println(test.remove());
		System.out.println(test.remove());

	}

}
