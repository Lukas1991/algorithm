package LinkedList;

public class ReverseList {
//three pointer, previous, current, next
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