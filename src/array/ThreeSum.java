package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
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
	 * @param args
	 */

	// O(n^2)
	public ArrayList<ArrayList<Integer>> threeSum(int[] num) {
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
		ArrayList<ArrayList<Integer>> result = sol.threeSum(new int[] { 1, 1,
				-2 });
		System.out.println("Test");
	}

}
