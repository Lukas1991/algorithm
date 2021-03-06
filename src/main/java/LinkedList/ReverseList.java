package LinkedList;

public class ReverseList {
//three pointer, previous, current, next
	public ListNode reverseList(ListNode head) {
        
        ListNode current=head;
        ListNode previous=null;
        
        while(current!=null){
			ListNode next=current.next;
        	current.next=previous;
        	previous=current;
        	current=next;       	       	
        }
        //return the first
		return previous;
		
    }

	/**
	 * recursive solution
	 * @param head
	 * @return
     */
	public ListNode reverseListRecursive(ListNode head) {
		return reverse(head, null);
	}

	private ListNode reverse(ListNode curr, ListNode newNext) {
		if (curr == null) return newNext;  //when curr is null, newNext is new Head

		ListNode next = curr.next;
		curr.next = newNext;
		return reverse(next, curr);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListNode tail=new ListNode(10,null);
		ListNode n3=new ListNode(3,tail);
		ListNode n2=new ListNode(2,n3);
		ListNode n1=new ListNode(1,n2);
		
		//iterate and print the list
		ListNode current=n1;
		while(current!=null){			
			System.out.println(current.val);
			current=current.next;
		}
		
		ReverseList rl=new ReverseList();
		ListNode first=rl.reverseList(n1);
		
		//iterate and print the list
		current=first;
		while(current!=null){			
			System.out.println(current.val);
			current=current.next;
		}
		
		

	}

}