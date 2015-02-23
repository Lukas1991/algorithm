package array;

import java.util.HashMap;
import java.util.Map;

public class SingleNumber {

	//Given an array of integers, every element appears twice except for one. Find that single one.
	//Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
	 public int singleNumberTwice(int[] A) {
		 int x=0;
		
		 for(int a:A){
			 x=x^a;
		 }
		 
		 return x;
	 }
	//Given an array of integers, every element appears three times except for one. Find that single one.
	// Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
	 public int singleNumberThreeTimes(int[] A){
		 Map<Integer,Integer> data=new HashMap<Integer, Integer>();
		 for(int i:A){
	           int value=data.containsKey(i)?data.get(i)+1:1;
	           data.put(i,value);
	       }
		  for(Integer key:data.keySet()){
	           if(data.get(key)!=3){
	               return key;
	           }
	       }
	       
	       return 0;
	 }
	
	 //http://oj.leetcode.com/discuss/857/constant-space-solution
	
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
