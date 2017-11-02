package array;

import java.util.HashMap;

public class TwoSum {

	public int[] twoSum(int[] numbers, int target) {
		//Use HashMap to store the target value.
		//Time complexity of this solution: O(n).
			HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
			int[] result = new int[2];
		 
			for (int i = 0; i < numbers.length; i++) {
				if (map.containsKey(numbers[i])) {
					int index = map.get(numbers[i]);
					result[0] = index+1 ;
					result[1] = i+1;
					break;
				} else {
					map.put(target - numbers[i], i);
				}
			}
		 
			return result;
	}

	//Given an array of integers that is already sorted in ascending order
	 public int[] twoSumSorted(int[] numbers, int target) {
		 int left = 0, right = numbers.length - 1;
		 while (left < right) {
			 int sum = numbers[left] + numbers[right];
			 if (sum > target) {
				 right --;
			 } else if (sum < target) {
				 left ++;
			 } else {
				 return new int[]{left + 1, right + 1};
			 }
		 }
		 return null;
	 }
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

}