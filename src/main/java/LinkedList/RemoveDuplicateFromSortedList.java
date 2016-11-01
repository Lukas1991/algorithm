package LinkedList;

/**
 * Given a sorted linked list, delete all duplicates such that each element appear only once.
 * For example,
 * Given 1->1->2, return 1->2.
 * Given 1->1->2->3->3, return 1->2->3.
 *
 */
public class RemoveDuplicateFromSortedList {
	
	//since it's sorted, just compare current value with previous value
	public ListNode deleteDuplicates(ListNode head) {
			if (head==null || head.next==null) return head;
			ListNode previous=head;
			ListNode current=previous.next;
			while(current!=null){
				if(current.val==previous.val){
					previous.next=current.next;
					current=current.next;
				}else{
					previous=current;
					current=current.next;					
				}
				
			}
		
			return head;
	    }
}
