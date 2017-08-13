package SortAndSearch;

public class SearchRange {

    /**
     * Given an array of integers sorted in ascending order, find the starting and ending position of a given target value.
     *
     * Your algorithm's runtime complexity must be in the order of O(log n).
     *
     * If the target is not found in the array, return [-1, -1].
     *
     * For example, Given [5, 7, 7, 8, 8, 10] and target value 8, return [3, 4].
     */
    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[2];
        res[0] = -1;
        res[1] = -1;

        if (nums.length == 0) {
            return res;
        }

        //1st search, find left
        int s = 0;
        int e = nums.length - 1;
        while (s < e) {
            int mid = (s + e) / 2;
            if (nums[mid] < target) {
                s = mid + 1;
            } else {
                e = mid;
            }
        }

        if (nums[s] != target) {
            return res;
        } else {
            res[0] = s;
        }

        e = nums.length - 1;
        //2nd search, find right
        while (s < e) {
            int mid = (s + e) / 2 + 1;  // Make mid biased to the right
            if (nums[mid] > target) {
                e = mid - 1;
            } else {
                s = mid;
            }
        }

        res[1] = e;

        return res;
    }
}
