package LinkedList;

public class HasCycle {

	/**
	 * @param args
	 */
	public boolean hasCycle(ListNode head) {
        if (head==null) return false;
        if(head.next==null) return false;
        
        
        ListNode p1=head;
        ListNode p2=head;
      //find meeting point in the circle
      		//p1 speed 1, p2 speed 2
        while(p2!=null){
            p1=p1.next;
            p2=p2.next;
            if(p2 == null) return false;
            else p2=p2.next;
            
            if(p2!=null && p1==p2) return true;
            
        }
        
        return false;       
    }
	
	public ListNode detectCycle(ListNode head) {
	       
        if (head==null) return null;
        if(head.next==null) return null;
        
        
        ListNode p1=head;
        ListNode p2=head;
        
        while(p2!=null){
            p1=p1.next;
            p2=p2.next;
            if(p2 == null) return null;
            else p2=p2.next;
            
            if(p2!=null && p1==p2) break;
            
        }
      //error check: if no loop, there will be no meeting point
        if(p2==null) return null;
      //move p1 to head, keep p2 at the meeting point. each are K steps to the loop start
      		//p1 p2 both speed 1, they will meet at the loop start
        p1=head;
        
        while(p1!=p2){
            p1=p1.next;
            p2=p2.next;
        }
        
        return p1;
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
