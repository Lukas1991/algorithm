package StackQueue;


class QueueTest{
	Node first,last;
	void enqueue(int item){
		if(first!=null){
			Node x=new Node(item);
			first=x;
		}else{
			first=new Node(item);
		}
	}
	
	Node dequeue(Node n){
		if(last!=null){
			Node previous=new Node(1);
			//interate from first to find the previous
			Node toReturn=last;
			
			previous.next=null;
			last=previous;
			return toReturn;
		}
		return null;
	}
	
	
}