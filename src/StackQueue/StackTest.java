package StackQueue;

class Node{
	
	int data;
	Node next=null;
	Node(int item){
		data=item;
	}
	
}

class StackTest {
	Node top;
	int pop(){
		if(top!=null){
			int item=top.data;
			top=top.next;
			return item;			
		}
		return (Integer) null;		
	}
	
	void push(int item){
		Node newNode=new Node(item);
		newNode.next=top;
		top=newNode;
	}
}
