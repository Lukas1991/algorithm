package BitManipulation;

import java.util.HashMap;
import java.util.Map;

public class SingleNumber {

	//Given an array of integers, every element appears twice except for one. Find that single one.
	//Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
	//XOR will return 1 only on two different bits. So if two numbers are the same, XOR will return 0. Finally only one number left.
	 public int singleNumberTwice(int[] A) {
		 int x=0;
		
		 for(int a:A){
			 x=x^a;
		 }
		 
		 return x;
	 }
	//Given an array of integers, every element appears three times except for one. Find that single one.
	// Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
	 //http://oj.leetcode.com/discuss/857/constant-space-solution
	 /**
	  * If you sum the ith bit of all numbers and mod 3, it must be either 0 or 1 due to the constraint of this problem where each number must appear either three times or once. 
	  * This will be the ith bit of that "single number".
	  */
	 
	 public int singleNumberThreeTimes(int[] A){	        
		 int count[] = new int[32];
		 int n=A.length;
	        int result = 0;
	        for (int i = 0; i < 32; i++) {
	            for (int j = 0; j < n; j++) {
	            	
	            	
	                if ( ((A[j] >> i) & 1)==1) {
	                    count[i]++;
	                }
	            }
	            result |= ((count[i] % 3) << i);
	        }
	        return result;
	  
	 }
	
	 /**
	  * We can improve this based on the previous solution using three bitmask variables:
	  * ones as a bitmask to represent the ith bit had appeared once.
	  * twos as a bitmask to represent the ith bit had appeared twice.
	  * threes as a bitmask to represent the ith bit had appeared three times.
	  * When the ith bit had appeared for the third time, clear the ith bit of both ones and twos to 0. The final answer will be the value of ones.
	  */
	 public int singleNumberThreeTimesNB(int[] A){
		 int ones = 0, twos = 0, threes = 0;
		    for (int i = 0; i < A.length; i++) {
		        twos |= ones & A[i];   //use the previous ones 
		        ones ^= A[i];			//then update ones
		        threes = ones & twos;		
		        ones &= ~threes;        //for any bit in threes is 1, clear that bit to 0 in ones
		        twos &= ~threes;
		    }
		    return ones;
	 }
	
	 public int singleNumberThreeTimesExceptOneOccurOnce(int[] A){

		 int count[] = new int[32];
		 int n=A.length;
	        int result = 0;
	        for (int i = 0; i < 32; i++) {
	            for (int j = 0; j < n; j++) {
	            		            	
	                if ( ((A[j] >> i) & 1)==1) {
	                    count[i]++;
	                }
	            }
	            count[i]=count[i]%3;
	            
	            
	        }
	        int mask=1;
	        for (int i = 0; i < 5; i++) {
	        	if(count[i]!=mask){
	        		count[i]=0;
	        	}else{
	        		count[i]=1;
	        	}
	        	//count[i]=count[i]/mask;
	        	result |= ((count[i]) << i);
	        	//System.out.println(count[i]);
	        }
	        return result;
	 
	 
	 
	 }
	 
	 public int singleNumberThreeTimesExceptOneOccurTwice(int[] A){

		 int count[] = new int[32];
		 int n=A.length;
	        int result = 0;
	        for (int i = 0; i < 32; i++) {
	            for (int j = 0; j < n; j++) {
	            		            	
	                if ( ((A[j] >> i) & 1)==1) {
	                    count[i]++;
	                }
	            }
	            count[i]=count[i]%3;
	        }
	        int mask=2;
	        for (int i = 0; i < 5; i++) {
	        	if(count[i]!=mask){
	        		count[i]=0;
	        	}else{
	        		count[i]=1;
	        	}
	        	//count[i]=count[i]/mask;
	        	result |= ((count[i]) << i);
	        	//System.out.println(count[i]);
	        }
	        return result;
	 
	 
	 }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a[]={1,3,3,4,1,1};
		SingleNumber sn=new SingleNumber();
		int result1=sn.singleNumberThreeTimesExceptOneOccurOnce(a);
		System.out.println(result1);
		int result2=sn.singleNumberThreeTimesExceptOneOccurTwice(a);
		System.out.println(result2);

		
	}

}
