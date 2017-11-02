package SortAndSearch;

import java.util.Arrays;

/**
 * 719. Find K-th Smallest Pair Distance
 * Given an integer array, return the k-th smallest distance among all the pairs.
 * The distance of a pair (A, B) is defined as the absolute difference between A and B.
 */
public class KthSmallestDistancePair {

    /**
     * Binary Search + sliding window
     * N(logN) + NlogW
     * Sorting, W is max diff, so logW for binary search, and in each search, it's O(N)
     */
    public static int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);

        int lo = 0;
        int hi = nums[nums.length - 1] - nums[0]; //max diff

        while (lo < hi) {
            int mid = (lo + hi) / 2; //mid diff

            int count = 0;
            int left = 0;
            for (int right = 1; right < nums.length; right++) {
                while (nums[right] - nums[left] > mid) {
                    left++;
                }

                count += right - left;
            }

            //count is number of pairs with distance <= mid
            if (count >= k) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }

        return hi;
    }

    public static void main(String[] args) {
        System.err.println(smallestDistancePair(new int[]{9,10,7,10,6,1,5,4,9,8}, 18));
    }
}