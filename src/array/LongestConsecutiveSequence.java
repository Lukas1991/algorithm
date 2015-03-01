package array;

import java.util.HashSet;
import java.util.Set;

public class LongestConsecutiveSequence {

/** 
 * Longest Consecutive Sequence 
 *  
 * Given an unsorted array of integers, find the length of the longest 
 * consecutive elements sequence. 
 *  
 * For example, Given [100, 4, 200, 1, 3, 2], The longest consecutive elements 
 * sequence is [1, 2, 3, 4]. Return its length: 4. 
 *  
 * Your algorithm should run in O(n) complexity. 
 *  
 */  
	public int longestConsecutive(int[] num) {
       // int runningtime = 0;
		
		if(num.length == 0) return 0;
		
		Set<Integer> set =new HashSet<Integer>();
		for(int element : num){
			set.add(element);
		}
		
		int max = 1;
		for(int element : num){
			int count = 1;
			int left = element-1;
			int right = element+1;
			
			while(set.contains(left)){
				count++;
				set.remove(left); //decrease time complexity for later elements
				left--;
				//runningtime++;
			}
			while(set.contains(right)){
				count++;
				set.remove(right);
				right++;
				//runningtime++;
			}
			
			max = Math.max(max, count);
			
		}
		
		//System.out.println("runningtime: "+runningtime);
		return max;
				
    }

	/**
	 * sort requires O(nlogn) time, have to increase the space complexity to achieve O(n) in time. 
	 * use a HashSet or Hashtable
	 * set.remove(left); set.remove(right); //decrease time complexity for later elements. 
	 * each elements in the longest consecutive sequence will have the same count.
	 * 
	 */
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] num = {1,2,4,5,3};
		
		LongestConsecutiveSequence obj = new LongestConsecutiveSequence();
		System.out.println(obj.longestConsecutive(num));
		
		
	}

}
