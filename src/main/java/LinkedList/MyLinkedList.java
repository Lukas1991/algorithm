package LinkedList;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

class Node{
	public int data;
	public Node next;
	public Node(int data){
		this.data=data;
	}
	public void displayNode(){
		System.out.println("{" + data + "} ");
	}
}
public class MyLinkedList {

	public Node first;
	public MyLinkedList(){
		first=null;
	}
	
	public Node getFirst(){
		if(first==null) throw new NoSuchElementException();
		return first;
	}
	
	public Node removeFirst(){
		if(first==null) throw new NoSuchElementException();
		Node temp=first;
		first=first.next;
		return temp;
	}
	public void addFirst(int idata){
		Node newnode=new Node(idata);
		newnode.data=idata;	
		newnode.next=first;
		first=newnode;
	}
//find a node
	public int Find (int key){
		boolean find=false;
		Node current=first;
		while(!find && current!=null){
			if(current.data==key) find=true;
			else current=current.next;
		}
		return current.data;
	}
	//delete a node
	public int Delete (int key){
		boolean find=false;
		Node current=first;
		Node previous=null;
		while(!find && current!=null){
			if(current.data==key){
				find=true;
				if(current==first) first=first.next;
				else 
					previous.next=current.next;
				
				return current.data;
			}else{
				previous=current;
				current=current.next;
				
			}
		}
		//not found
		System.out.println("not found");
		return -1;
		
	}
	public void Display(){
		Node current=first;
		while(current!=null){
			System.out.print(current.data+" ->");
			current=current.next;
		}
		System.out.println();
	}
	public void RemoveDuplicate(){

		Node current=first;
		Node previous=null;
		Set hs=new HashSet();
		
		while(current!=null){
			if(hs.contains(current.data)){
				//remove it
				previous.next=current.next;
				current=current.next;		
			}else{
				hs.add(current.data);
				previous=previous.next;
				current=current.next;
			}			
		}
	}
	public static void main(String[] args){
		
		/*LinkedList ll=new LinkedList();
		for(int i=0;i<10;i++) ll.addFirst(i);
		ll.Display();
		LinkedListTest rd=new LinkedListTest();
		rd.NthToLastBest(ll,2);*/
		
		//System.out.println(ll.Find(2));
		
		//System.out.println(ll.Delete(2)+"has been deleted");
				
		//rd.RemoveDuplicate(ll);
		//rd.RemoveDuplicateNoBuffer(ll);
		//System.out.println("after remove");
		//ll.Display();
		
		/*//Question 2.4 addByDigit
		LinkedList l1=new LinkedList();
		l1.addFirst(5);l1.addFirst(1);l1.addFirst(3);
		l1.Display();
		LinkedList l2=new LinkedList();
		l2.addFirst(2);l2.addFirst(9);l2.addFirst(5);l2.addFirst(3);
		l2.Display();
		LinkedListTest test=new LinkedListTest();
		test.addByDigit(l1, l2);*/
		
		
		
	}
}
