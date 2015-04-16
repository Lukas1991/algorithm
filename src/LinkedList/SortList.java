package LinkedList;

public class SortList {

	/**
	 * 1. Break the list to two in the middle
	 * 2. Recursively sort the two sub lists
	 * 3. Merge the two sub lists
	 * Time O(nlogn), constant space
	 * @param args
	 */
	 public ListNode sortList(ListNode head) {
	        if(head == null || head.next == null){
	            return head;
	        }
	        ListNode p1Pre=head;
	        ListNode p1=head, p2=head;
	        while(p2!=null && p2.next !=null){
	            p1Pre = p1;
	            p1 = p1.next;
	            p2 = p2.next.next;
	        }
	        
	        p1Pre.next=null;
	        ListNode l = sortList(head);
	        ListNode r = sortList(p1);
	        
	        return mergeTwoLists(l,r);
	        
	    }
	    
	    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
	        
	        if(l1==null && l2==null){
				 return null;
			 }else if(l1==null){
				 return l2;
			 }else if(l2==null){
				 return l1;
			 }
			 		
			
			 ListNode paddingListNode=new ListNode(0);
			 ListNode current=paddingListNode;
			 ListNode l1Pnt=l1;
			 ListNode l2Pnt=l2;
					 
			 while(l1Pnt !=null && l2Pnt !=null){
				 
				 if(l1Pnt.val<=l2Pnt.val){
					 current.next=l1Pnt;
					 current=current.next;
					 l1Pnt=l1Pnt.next;
				 }else{
					 current.next=l2Pnt;
					 current=current.next;
					 l2Pnt=l2Pnt.next;
				 }
				 
			 }
			 
			 if(l1Pnt ==null) current.next=l2Pnt;
			 if(l2Pnt ==null) current.next=l1Pnt;
			 		 
			 return paddingListNode.next;
		    }
		
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
