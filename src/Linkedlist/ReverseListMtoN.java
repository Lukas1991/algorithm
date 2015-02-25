package Linkedlist;

public class ReverseListMtoN {
/**
 * Reverse a linked list from position m to n. Do it in-place and in one-pass.

For example:
Given 1->2->3->4->5->NULL, m = 2 and n = 4,

return 1->4->3->2->5->NULL.

Note:
Given m, n satisfy the following condition:
1 ??? m ??? n ??? length of list.
 * 
 * @param args
 */
	//1. find m, beforeM
	//2. reverse till find n
	//3. beforeM.next is afterN
	
	
	public ListNode reverseBetween(ListNode head, int m, int n) {
        
		ListNode beforeM=null;
		ListNode current=head;
		int i=1;
		//find m
		while(i<m){
			beforeM=current;
			current=current.next;
			i++;
		}
		
		ListNode mNode=current;
		//current is m, reverse to n
		
		ListNode previous=null;
		ListNode next=null;
		
		while(i<n){			
			next=current.next;		
			current.next=previous;
			previous=current;
			current=next;
		
			i++;
					
		}
		//current is n
		
		ListNode afterN=current.next;
		if(beforeM!=null){  //when n=1 beforeM is null
			beforeM.next=current;
		}
		
		current.next=previous;	
		mNode.next=afterN;
		
		if(beforeM==null){  //when n=1
			return current;
		}
		return head;
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//ListNode n7=new ListNode(7,null);
		//ListNode n6=new ListNode(6,null);
		ListNode n5=new ListNode(5,null);
		ListNode n4=new ListNode(4,n5);
		ListNode n3=new ListNode(3,n4);
		ListNode n2=new ListNode(2,n3);
		ListNode n1=new ListNode(1,n2);
		
		ReverseListMtoN rl=new ReverseListMtoN();
		ListNode head=rl.reverseBetween(n1,2,4);
		System.out.println("------------------------");
		//iterate and print the list
		ListNode current=n1;
		while(current!=null){			
			System.out.println(current.val);
			current=current.next;
		}
				
	}
	

}
