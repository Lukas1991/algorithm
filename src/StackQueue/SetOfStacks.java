package StackQueue;

import java.util.ArrayList;



public class SetOfStacks {

	/**Question 3.3
	 * a list of stacks
	 * @param args
	 */
	ArrayList<Stack> stacks=new ArrayList();
	public int capacity;
	public SetOfStacks(int capacity){
		this.capacity=capacity;
	}
	
	public Stack getLastStack(){
		if(stacks.size()==0) return null;
		int lastindex=stacks.size()-1;
		return stacks.get(lastindex);
	}
	
	public void push(int v){
		Stack last=getLastStack();
		if (last !=null && !last.isAtCapacity()){
			last.push(v);
		}else{
			//create a new stack
			Stack newStack=new Stack(capacity);
			newStack.push(v);
			stacks.add(newStack);
		}
	}
	
	public int pop(){
		int lastindex=stacks.size()-1;
		Stack last=getLastStack();
		int v=last.pop();
		if(last.size==0) stacks.remove(lastindex);
		return v;
	}
	
	public int popAt(int index){
		return leftShift(index,true);
	}
	
	public int leftShift(int index, boolean removeTop){
		Stack stack=stacks.get(index);
		int removed_item;
		if(removeTop) removed_item=stack.pop(); //if it's the stack to pop
		else removed_item=stack.removeBottom(); //else remove bottom
		//rollover
		if(stack.isEmpty()){
			stacks.remove(index);
		}else if(index<stacks.size()-1){
			int v=leftShift(index+1,false);
			stack.push(v);
		}
				
		return removed_item;
		
	}
	
	public static void main(String[] args) {
		

	}

}

class Stack{
	private int capacity;
	public Node top, bottom;
	public int size=0;
	
	public Stack(int capacity){
		this.capacity=capacity;
	}
	public boolean isAtCapacity(){
		return capacity==size;
	}
	public void join(Node above, Node below){
		if(below!=null) below.above=above;
		if(above!=null) above.below=below;
	}
	public boolean push(int v){
		if(size>=capacity) return false;
		size++;
		Node n=new Node(v);
		if(size==1) bottom=n;
		join(n,top);
		top=n;
		return true;
	}
	public int pop(){
		Node tmp=top;
		top=top.below;
		size--;
		return tmp.value;
	}
	public boolean isEmpty(){return size==0;}
	public int removeBottom(){
		Node b=bottom;
		bottom=bottom.above;
		if(bottom!=null) bottom.below=null;
		size--;
		return b.value;
		
	}
	
	class Node{
		int value;
		Node above;
		Node below;
		Node(int v){
			value=v;
		}
	}
}

