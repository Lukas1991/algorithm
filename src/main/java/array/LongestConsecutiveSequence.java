package array;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LongestConsecutiveSequence {

	/**
	 * Longest Consecutive Sequence
	 * 
	 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
	 * 
	 * For example, Given [100, 4, 200, 1, 3, 2], The longest consecutive
	 * elements sequence is [1, 2, 3, 4]. Return its length: 4.
	 * 
	 * Your algorithm should run in O(n) complexity.
	 */
	public int longestConsecutive(int[] nums) {
		Map<Integer, Integer> map = new HashMap<>();
		int max = 0;
		for (int i = 0; i < nums.length; i++) {
			int a = nums[i];
			if (map.containsKey(a)) {
				continue;
			}

			int rightCount = map.getOrDefault(a - 1, 0);
			int leftCount = map.getOrDefault(a + 1, 0);

			int count = 1 + rightCount + leftCount;
			map.put(a, count);
			max = Math.max(max, count);

			// update the value only for the left and right boundaries
			map.put(a - rightCount, count);
			map.put(a + leftCount, count);
		}

		return max;
	}

	/**
	 * Notes: Whenever a new element n is inserted into the map, do two things:
	 * 
	 * 1. See if n - 1 and n + 1 exist in the map, and if so, it means there is an existing sequence next to n. 
	 * Variables rightCount and leftCount will be the length of those two sequences, while 0 means there is no sequence and n will be
	 * the boundary point later. Store (rightCount + leftCount + 1) as the associated value to key n into the map. 
	 * 
	 * 2. Use rightCount and leftCount to locate the boundary of the sequences to the left and right of n respectively, and replace the
	 * value with the new length.
	 */
	public static void main(String[] args) {
		int[] num = { 1, 2, 4, 5, 3 };

		LongestConsecutiveSequence obj = new LongestConsecutiveSequence();
		System.out.println(obj.longestConsecutive(num));
	}

}
