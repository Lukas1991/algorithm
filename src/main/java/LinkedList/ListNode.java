package LinkedList;

public class ListNode {

	public int val;
	public ListNode next;
	
	public ListNode(int x){
		val=x;
		next=null;
	}
	
	ListNode(int x,ListNode n){
		val=x;
		next=n;
	}
	
	public void printList(ListNode head){
		while(head!=null){
			System.out.println(head.val);
			head=head.next;
		}
	} 
	
}