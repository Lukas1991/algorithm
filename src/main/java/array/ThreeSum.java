package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ThreeSum {

	/**
	 * Given an array S of n integers, are there elements a, b, c in S such that
	 * a + b + c = 0? Find all unique triplets in the array which gives the sum
	 * of zero.
	 * 
	 * Note: Elements in a triplet (a,b,c) must be in non-descending order. (ie,
	 * a ≤ b ≤ c) //sorted The solution set must not contain duplicate triplets.
	 * //no duplicate
	 * 
	 * For example, given array S = {-1 0 1 2 -1 -4},
	 * 
	 * A solution set is: (-1, 0, 1) (-1, -1, 2)
	 *
	 */

	 public List<List<Integer>> threeSum(int[] num) {
		//A better solution is using two pointers instead of one. This makes time complexity of O(n^2).

		//To avoid duplicate, we can take advantage of sorted arrays, i.e., move pointers by >1 to use same element only once.
		 List<List<Integer>> result = new ArrayList<List<Integer>>();
		 
			if (num.length < 3)
				return result;
		 
			// sort array
			Arrays.sort(num);
		 
			for (int i = 0; i < num.length - 2; i++) {
				// //avoid duplicate solutions
				if (i == 0 || num[i] > num[i - 1]) {
		 
					int start = i + 1;
					int end = num.length - 1;
		 
					while (start < end) {
						
						if(num[i]+num[start]+num[end] < 0){
	                        start ++;
	                    }else if(num[i]+num[start]+num[end] > 0){
	                        end --;
	                    }else{ //==0
	                        List<Integer> temp = new ArrayList<Integer>();
	                        temp.add(num[i]);
	                        temp.add(num[start]);
	                        temp.add(num[end]);
	                        result.add(temp);
	                        
	                        start++;
	                        end--;
	                      //avoid duplicate solutions
	                        while(start<end && num[start]==num[start-1]){
	                            start++;
	                        }
	                         while(start<end && num[end]==num[end+1]){
	                            end--;
	                        }
	                    }
						
					}
		 
				}
			}
		 
			return result;
		    }
	
	 /**
	 * For example, given array S = {-1 2 1 -4}, and target = 1.
	 * The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
	 */
	 public int threeSumClosest(int[] num, int target) {
	        int min = Integer.MAX_VALUE;
	        if (num.length < 3)
				return 0;
		    int result=0;
			// sort array
			Arrays.sort(num);
		 
			for (int i = 0; i < num.length - 2; i++) {
				int start = i+1;
				int end = num.length -1;
				
				while(start < end){
					int sum = num[i]+num[start]+num[end];
					int diff = Math.abs(sum-target);
					if(diff == 0) return sum;
					
					if(diff < min){
						min=diff;
						result = sum;
					}
					if(sum < target){
						start++;
					}else{
						end--;
					}
										
				}
			}
	        
			return result;
	    }

	/**
	 * nums[i] + nums[j] + nums[k] < target.
	 * For example, given nums = [-2, 0, 1, 3], and target = 2.
	 * Return 2. Because there are two triplets which sums are less than 2:
	 * [-2, 0, 1]
	 * [-2, 0, 3]
	 * @return
     */
	public int threeSumSmaller(int[] nums, int target) {
		int count = 0;
		if (nums.length < 3) return 0;

		Arrays.sort(nums);
		for (int i = 0; i < nums.length - 2; i++) {
			int j = i + 1;
			int k = nums.length - 1;
			while (j < k) {
				if (nums[i] + nums[j] + nums[k] < target) {
					count = count + (k - j); //因为数组排序了以后，如果加上num[k]小于目标值的话，那么加上一个更小的数必定也会小于目标值
					j++;
				} else {
					k--;
				}
			}
		}

		return count;
	}

	 /**
	  * O(n^3)
	  */
	 public ArrayList<ArrayList<Integer>> fourSum(int[] num, int target) {
	        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
	        if (num.length <4) return res;
	        Arrays.sort(num);
	        for (int i = 0; i < num.length-3; i++) {
	            if (i > 0 && num[i-1] == num[i]) continue;
	            for (int j = i+1; j < num.length-2; j++) {
	                if (j>i+1 && num[j-1] == num[j]) continue;
	                int start = j+1;
	                int end = num.length-1;
	                while (start < end) {
	                    if (num[i] + num[j] + num[start] + num[end] < target) {
	                        start++;
	                    } else if (num[i] + num[j] + num[start] + num[end] > target) {
	                        end--;
	                    } else {
	                        ArrayList<Integer> list = new ArrayList<Integer>();
	                        list.add(num[i]);
	                        list.add(num[j]);
	                        list.add(num[start]);
	                        list.add(num[end]);
	                        res.add(list);
	                        
	                        do { start++; } while (start < end && num[start-1] == num[start]);
	                        do { end--; } while (start < end && num[end+1] == num[end]);
	                    }
	                }
	            }
	        }
	        return res;
	    }
	 
	// O(n^2)
	public ArrayList<ArrayList<Integer>> threeSumOld(int[] num) {
		int[] numbers = Arrays.copyOf(num, num.length);

		ArrayList<ArrayList<Integer>> threeResult = new ArrayList<ArrayList<Integer>>();
		if (num == null || num.length < 3)
			return threeResult;

		Arrays.sort(numbers);

		for (int i = 0; i < numbers.length; i++) {

			if (i > 0 && numbers[i] == numbers[i - 1]) {
				continue;
			}

			int target = 0 - numbers[i];
			int[] tail = Arrays.copyOfRange(numbers, i + 1, numbers.length);

			ArrayList<ArrayList<Integer>> twoResult = twoSum2(tail, target);

			for (ArrayList<Integer> entry : twoResult) {
				ArrayList<Integer> threeSet = new ArrayList<Integer>();
				threeSet.add(numbers[i]);
				threeSet.add(entry.get(0));
				threeSet.add(entry.get(1));
				Collections.sort(threeSet);
				threeResult.add(threeSet);
			}

		}

		return threeResult;

	}

	// using two pointers, sort array first, O(n)
	public ArrayList<ArrayList<Integer>> twoSum2(int[] numbers, int target) {
		// input numbers already sorted
		// if not, Collections.sort(numbers);
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		if (numbers.length < 2)
			return result;

		int i = 0, j = numbers.length - 1;

		while (i < j) {
			int twoSum = numbers[i] + numbers[j];

			if (twoSum == target) {
				ArrayList<Integer> twoSet = new ArrayList<Integer>();
				twoSet.add(numbers[i]);
				twoSet.add(numbers[j]);
				Collections.sort(twoSet);
				result.add(twoSet);
				while (i < j && numbers[i] == numbers[i + 1]) {
					i++;
				}
				while (i < j && numbers[j] == numbers[j - 1]) {
					j--;
				}

				i++;
				j--;

			} else if (twoSum < target) {
				i++;
			} else if (twoSum > target) {
				j--;
			}

		}

		return result;

	}

	// using Map<number, count>
	// using Map
	public ArrayList<ArrayList<Integer>> twoSum(int[] numbers, int target) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		if (numbers.length < 2)
			return result;

		Map<Integer, Integer> numberMap = new HashMap<Integer, Integer>();

		for (int i = 0; i < numbers.length; i++) {
			int count = 1;
			if (numberMap.containsKey(numbers[i])) {
				count = numberMap.get(numbers[i]) + 1;
			}
			numberMap.put(numbers[i], count);
		}

		for (int i = 0; i < numbers.length; i++) {
			int key = numbers[i];
			// for case 0+0=0 target is 0, 1+1=2 target is 2
			if (numberMap.containsKey(key) && numberMap.get(key) > 1
					&& (key + key) == target) {
				numberMap.remove(key);
				ArrayList<Integer> twoSet = new ArrayList<Integer>();
				twoSet.add(key);
				twoSet.add(key);
				result.add(twoSet);

				continue;
			}

			if (numberMap.containsKey(numbers[i])) {
				numberMap.remove(numbers[i]);

				int check = target - numbers[i];
				if (numberMap.containsKey(check)) {
					ArrayList<Integer> twoSet = new ArrayList<Integer>();
					twoSet.add(numbers[i]);
					twoSet.add(check);
					Collections.sort(twoSet);
					result.add(twoSet);

				}

			}

		}

		Collections.sort(result, new Comparator<ArrayList<Integer>>() {

			public int compare(ArrayList<Integer> list1,
					ArrayList<Integer> list2) {
				return list1.get(0).compareTo(list2.get(0));
			}

		});

		return result;
	}

	public static void main(String[] args) {
		ThreeSum sol = new ThreeSum();
		//ArrayList<ArrayList<Integer>> result = sol.threeSum(new int[] { 1, 1,-2 });
		System.out.println("Test");
	}

}
