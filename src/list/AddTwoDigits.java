package list;

public class AddTwoDigits {
	
	/**
	 * You are given two linked lists representing two non-negative numbers. 
	 * The digits are stored in reverse order and each of their nodes contain a single digit. 
	 * Add the two numbers and return it as a linked list.

	Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
	Output: 7 -> 0 -> 8
	 * */
	/**
	 * Use a flag to mark if previous sum is >= 10
		Handle the situation that one list is longer than the other 
	 */
	
    /**
     * Quesion

	What if the digits are stored in regular order instead of reversed order?

	Answer: We can simple reverse the list, calculate the result, and reverse the result.*/
	
	
	public ListNode addTwoNumbers(ListNode l1, ListNode l2, int carry) {
        
        if(l1 == null && l2 == null){
            if(carry == 0) 
                return null;
            else 
                return new ListNode(1); //{5}+{5}={0,1}
        }
        
        
        int value=carry;
        if(l1!=null){
            value=value+l1.val;
        }
        if(l2!=null){
            value=value+l2.val;
        }
        if(value>=10){
            value=value%10;
            carry=1;
        }else carry=0;
       
        ListNode l3=new ListNode(value);
       
        if(l1==null) 
        	l3.next=addTwoNumbers(null, l2.next, carry);
        else if(l2==null) 
        	l3.next=addTwoNumbers(l1.next,null, carry);
        else 
        	l3.next=addTwoNumbers(l1.next, l2.next, carry);
        
        return l3;
    }
    
    
    
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return addTwoNumbers(l1,l2,0); 
    }
    

}
