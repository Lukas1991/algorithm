package LinkedList;

public class RemoveNthFromEnd {

	/**
	 * Given a linked list, remove the nth node from the end of list and return its head.

For example,

   Given linked list: 1->2->3->4->5, and n = 2.

   After removing the second node from the end, the linked list becomes 1->2->3->5.

Note:
Given n will always be valid.
Try to do this in one pass. 
	 * 
	 * @param args
	 */
	public ListNode removeNthFromEnd(ListNode head, int n) {
        if(n<=0) return head;
        ListNode p1=head;
        ListNode p2=p1;;
        for(int i=0;i<n;i++){p2=p2.next;}
        //if p2 is null, then p1 is head, head is the one to delete
        if(p2==null) return p1.next;
        
        while(p2.next!=null){
            p1=p1.next;p2=p2.next;
        }
        //p2 is the tail, p1.next is the one to delete
        ListNode p1Next=p1.next;
        
        p1.next=p1Next.next;
        return head;

    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
