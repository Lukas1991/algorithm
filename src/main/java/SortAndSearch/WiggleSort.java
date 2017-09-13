package SortAndSearch;

import java.util.Arrays;

public class WiggleSort {

    /**
     * 280. Wiggle Sort.
     * Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <= nums[3]....
     * For example, given nums = [3, 5, 2, 1, 6, 4], one possible answer is [1, 6, 2, 5, 3, 4].
     */

    /**
     * One-pass Swap, Time complexity : O(n)
     */
    public void wiggleSort(int[] nums) {
        int n = nums.length;
        for (int i = 0; i + 1 < n; i++) {
            if (i % 2 == 0) {
                if (nums[i] > nums[i+1]) {
                    swap(nums, i, i+1);
                }
            } else {
                if (nums[i] < nums[i+1]) {
                    swap(nums, i, i+1);
                }
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void main(String[] args) {
        WiggleSort obj = new WiggleSort();
        int[] nums = {3, 5, 2, 1, 6, 4};
        obj.wiggleSort(nums);
        System.err.println(Arrays.toString(nums));
    }
}
