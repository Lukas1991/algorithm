package list;

public class ReorderList {

	/**
	 * Given a singly linked list L: L0→L1→…→Ln-1→Ln,
		reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…

		You must do this in-place without altering the nodes' values.

		For example,
		Given {1,2,3,4}, reorder it to {1,4,2,3}.
	 * 
	 * @param head
	 */
	
	//1. break the list into two part, find the n/2, second of the list
	//2. reverse the tail
	//3. insert the noe in tail list to front list one by one
	
	public void reorderList(ListNode head) {
		 if(head==null || head.next==null) return;
		  
		 ListNode p1=head;
		 ListNode p2=head;
		 //find the middle node
		 while(p2!=null && p2.next!=null){
			 p1=p1.next;
			 p2=p2.next;
			 if(p2!=null) p2=p2.next;
		 }
		 
		 
		 //when p2 goes to tail, p1 is the middle node
		 
		 ListNode head2=reverseList(p1);
		 
		 //insert
		 p1=head;
		 p2=head2;
		 
		//iterate and print the list
			ListNode current=p1;
			while(current!=null){			
				System.out.println(current.val);
				current=current.next;
			}
			System.out.println("------------------------");
			current=p2;
			while(current!=null){			
				System.out.println(current.val);
				current=current.next;
			}
		 //
			
		 ListNode p1Next=head.next;
		 ListNode p2Next=head2.next;
		 
		 while(p1.next!=null && p2.next!=null){
			 p1Next=p1.next;
			 p2Next=p2.next;
			 
			 p1.next=p2;
			 p2.next=p1Next;
			 
			 p1=p1Next;
			 p2=p2Next;
		 }
		 
			
    }
	
	public ListNode reverseList(ListNode head) {
        if(head==null || head.next==null) return head;
        
        ListNode current=head;
        ListNode previous=null;
        ListNode next=current.next;
        
        while(current!=null){
        	
        	next=current.next;
        	current.next=previous;
        	previous=current;
        	current=next;       	       	
        }
        //return the first
		return previous;
		
    }
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//ListNode n7=new ListNode(7,null);
		ListNode n6=new ListNode(6,null);
		ListNode n5=new ListNode(5,n6);
		ListNode n4=new ListNode(4,n5);
		ListNode n3=new ListNode(3,n4);
		ListNode n2=new ListNode(2,n3);
		ListNode n1=new ListNode(1,n2);
		
		ReorderList rl=new ReorderList();
		rl.reorderList(n1);
		System.out.println("------------------------");
		//iterate and print the list
		ListNode current=n1;
		while(current!=null){			
			System.out.println(current.val);
			current=current.next;
		}
		
	}

}
