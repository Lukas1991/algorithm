package LinkedList;

public class RotateRight {
/**
 * Given a list, rotate the list to the right by k places, where k is non-negative.

For example:
Given 1->2->3->4->5->NULL and k = 2,
return 4->5->1->2->3->NULL.
 * 
 * @param args
 */
	
	 public ListNode rotateRight(ListNode head, int n) {
	        //find length
	        //find length-n
	        //beforeN.next=null	
		 //current is newHead
		 	//find tail
	        //tail.next=head
	        //return newHead
		 
		 //special cases: (n==0),(n>len, n=n%len), (n%len==0),(len==1)
		 	if(n==0) return head;
	        if(head==null) return head;
	        
		 	int len=0;
	        ListNode current=head;
	        while(current!=null){
	            len++;
	            current=current.next;
	        }
	        //len is 5
	        if(len==1) return head;
	        if(n%len==0) return head;
	        n=n%len;
	        
	        ListNode beforeN=null;
	        int i=0;
	        current=head;
	        while(i<len-n){
	            
	            if(i==len-n-1){
	                beforeN=current;
	            }
	            current=current.next;
	            i++;
	        }
	        //current is 4
	        if(beforeN==null){
	            return head;
	        }
	        beforeN.next=null;
	        ListNode newHead=current;
	       
	        while(current.next!=null){
	             current=current.next;
	        }
	       
	        //find tail
	        ListNode tail=current;
	        tail.next=head;
	        return newHead;
	        
	        
	        
	        
	        
	    }
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListNode n5=new ListNode(5,null);
		ListNode n4=new ListNode(4,n5);
		ListNode n3=new ListNode(3,n4);
		ListNode n2=new ListNode(2,n3);
		ListNode n1=new ListNode(1,n2);
		
		RotateRight rl=new RotateRight();
		ListNode head=rl.rotateRight(n1,2);
		System.out.println("------------------------");
		//iterate and print the list
		ListNode current=head;
		while(current!=null){			
			System.out.println(current.val);
			current=current.next;
		}
	}

}
