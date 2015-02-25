package LinkedList;

public class FindNthtoEnd {

	/**
	 * @param args
	 */
	public ListNode findNthFromEnd(ListNode head, int n) {
        if(n<=0 || head==null) return null;
        ListNode p1=head;
        ListNode p2=head;
        for(int i=0;i<n;i++){
        	if(p2==null){//p2 is the end, then not found, list size < n 
        		return null;
        	}
        	p2=p2.next;
        }
        
        //if p2 is null, then p1 is head, head is the one
        if(p2==null) return p1;
        
        while(p2.next!=null){
            p1=p1.next;p2=p2.next;
        }
        //p2 is the tail, p1.next is the one to delete
        ListNode p1Next=p1.next;
        
        return p1Next;

    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
