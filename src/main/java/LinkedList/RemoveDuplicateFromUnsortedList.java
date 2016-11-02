package LinkedList;

import java.util.HashSet;
import java.util.Set;


/**
 * remove duplicates from an unsorted linked list
 * follow up
 * how would you solve this problem if a temporary buffer is not allowed?
 *
 */
public class RemoveDuplicateFromUnsortedList {

	//using a set
	public static ListNode removeDuplicate(ListNode head){
		if(head==null || head.next==null) return head;
		
		ListNode previous=head;
		ListNode current=previous.next;
		Set set=new HashSet();
		set.add(previous.val);
		
		while(current!=null){
			if(set.contains(current.val)){
				//remove it
				previous.next=current.next;
				current=current.next;
				
			}else{
				set.add(current.val);
				previous=current;
				current=current.next;
			}
			
		}
				
		return head;
	}
	
	//use a inner loop runner run from head to the one before current
	public static ListNode removeDuplicateNoBuffer(ListNode head){
		if(head==null || head.next==null) return head;
		ListNode previous=head;
		ListNode current=previous.next;
		
		while(current!=null){
			//check current==nodes before current
			boolean find=false;
			ListNode p=head;
			while(p!=current){
				if(p.val!=current.val){
					p=p.next;
				}else{
					find=true;
					break;
				}
			}
			
			if(find){
				previous.next=current.next;
				current=current.next;
			}else{
				previous=current;
				current=current.next;
			}
			
		}
				
		return head;
	}
	
	public static void main(String[] args) {
		ListNode n5=new ListNode(3,null);
		ListNode n4=new ListNode(2,n5);
		ListNode n3=new ListNode(1,n4);
		ListNode n2=new ListNode(2,n3);
		ListNode n1=new ListNode(1,n2);
		n1.printList(n1);
		System.out.println("-----------------");
		
		//ListNode head=removeDuplicate(n1);
		ListNode head=removeDuplicateNoBuffer(n1);
		head.printList(head);
		
	}
	
}