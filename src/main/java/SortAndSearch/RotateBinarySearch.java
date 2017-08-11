package SortAndSearch;

import java.util.Arrays;

public class RotateBinarySearch {

	/**
	 * Find the smallest number index, which is the index where the array rotate at.
	 * 
	 * When do binary search, use mid and rotate to calculate the real mid(the mid if there's no rotate)
	 */
	public int search(int[] nums, int target) {
		int rotate = findSmallest(nums);

		int s = 0;
		int e = nums.length - 1;

		while (s <= e) {
			int mid = (s + e) / 2;
			if (nums[mid] == target) {
				return mid;
			}

			int realMid = (mid + rotate) % nums.length;
			if (nums[realMid] == target) {
				return realMid;
			} else if (nums[realMid] > target) {
				e = mid - 1;
			} else {
				s = mid + 1;
			}
		}

		return -1;
	}

	/**
	 * The while loop finish when s == e, and at index=s is the smallest
	 */
	public int findSmallest(int[] a) {
		int s = 0;
		int e = a.length - 1;
		while (s < e) {
			int mid = s + (e - s) / 2;
			if (a[mid] > a[e]) {
				s = mid + 1;
			} else {
				// mid may be the smallest, don't skip it (cannot use e=mid-1)
				e = mid;
			}
		}
		// System.err.println("s:" + s + " e: " + e);
		// System.err.println("Smallest:" + a[s]);
		return s;
	}

	public int[] rotateAt(int[] a, int index) {
		int[] b = new int[a.length];
		int j = 0;
		for (int i = index; i < a.length; i++) {
			b[j] = a[i];
			j++;
		}
		for (int i = 0; i < index; i++) {
			b[j] = a[i];
			j++;
		}
		return b;
	}

	public static void main(String[] args) {
		RotateBinarySearch obj = new RotateBinarySearch();
		
		int[] a = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		int[] test = obj.rotateAt(a, 5);

		System.err.println("Rotate: " + Arrays.toString(test));
		//obj.findSmallest(test);

		int pos = obj.search(test, 6);
		System.err.println(test[pos]);
	}

}
