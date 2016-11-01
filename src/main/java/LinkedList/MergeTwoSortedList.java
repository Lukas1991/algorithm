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
		 }
		 			
		 ListNode paddingListNode=new ListNode(0);
		 ListNode current=paddingListNode;
				 
		 while(l1 !=null && l2 !=null){
			 
			 if(l1.val<=l2.val){
				 current.next=l1;				 
				 l1=l1.next;
			 }else{
				 current.next=l2;
				 l2=l2.next;
			 }
			 current=current.next;
		 }
		 
		 if(l1 ==null) current.next=l2;
		 if(l2 ==null) current.next=l1;
		 		 
		 return paddingListNode.next;
	    }
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
