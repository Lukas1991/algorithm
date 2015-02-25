package LinkedList;

public class MergeTwoSortedList {

	/**
	 * Merge two sorted linked lists and return it as a new list. 
	 * The new list should be made by splicing together the nodes of the first two lists.
	 * @param l1
	 * @param l2
	 * @return
	 */
	
	/*
	 * The key to solve the problem is defining a fake head. 
	 * Then compare the first elements from each list. Add the smaller one to the merged list. 
	 * Finally, when one of them is empty, simply append it to the merged list, since it is already sorted.
	 * */
	
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
