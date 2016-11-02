package LinkedList;

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
    
    
    
    public ListNode addTwoNumbersRecursive(ListNode l1, ListNode l2) {
        return addTwoNumbers(l1,l2,0); 
    }
    

    public ListNode addTwoNumbersWhileLoop(ListNode l1, ListNode l2) {

        if(l1 == null && l2 == null) return null;
        if(l1==null) return l2;
        if(l2==null) return l1;
       ListNode p1=l1;
       ListNode p2=l2;
       int carry=0;
       ListNode prel3=null;
       ListNode newHead=null;
      
       while(p1!=null && p2!=null){
           int sum=(p1.val+p2.val+carry)%10;
           carry = (p1.val+p2.val+carry)/10;
           ListNode l3=new ListNode(sum);
           if(prel3!=null){
               prel3.next=l3;
               prel3=l3;
           }else{
               prel3=l3;
               newHead=l3;
           }
           p1=p1.next;
           p2=p2.next;
           
       }
       
       while(p1!=null){
           int sum=(p1.val+carry)%10;
           carry = (p1.val+carry)/10;
           ListNode l3=new ListNode(sum);
           
               prel3.next=l3;
               prel3=l3;
           
           p1=p1.next;
       }
       
       while(p2!=null){
           int sum=(p2.val+carry)%10;
           carry = (p2.val+carry)/10;
           ListNode l3=new ListNode(sum);
          
               prel3.next=l3;
               prel3=l3;
          
           p2=p2.next;
       }
       
       if(carry==1){
           prel3.next=new ListNode(1);
       }
       else prel3.next=null;
       
       return newHead;
       
   
    }
    

}