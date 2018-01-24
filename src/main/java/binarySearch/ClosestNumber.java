package binarySearch;

public class ClosestNumber {

    /**
     * Given a target number and an integer array A sorted in ascending order, find the index i in A such that A[i] is closest to the given target.
     *
     * Return -1 if there is no element in the array.
     *
     * There can be duplicate elements in the array, and we can return any of the indices with same value.
     *
     * Example
     * Given [1, 2, 3] and target = 2, return 1.
     * Given [1, 4, 6] and target = 3, return 1.
     * Given [1, 4, 6] and target = 5, return 1 or 2.
     * Given [1, 3, 3, 4] and target = 2, return 0 or 1 or 2.
     *
     * 先二分找target的insert index
     * 比较index和index前一个数
     */
    public int closestNumber(int[] A, int target) {
        if (A == null || A.length == 0) return -1;
        int index = searchInsertPosition(A, target);

        if (index == 0) {
            return 0;
        }

        if (index == A.length) {
            return A.length - 1;
        }

        if (target - A[index - 1] < A[index] - target) {
            return index - 1;
        } else {
            return index;
        }
    }

    public int searchInsertPosition(int[] nums, int target) {
        if (nums == null || nums.length == 0) return 0;
        int start = 0, end = nums.length - 1;

        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }

        if (nums[start] >= target) return start;
        if (nums[end] >= target) return end;

        return nums.length;
    }
}
