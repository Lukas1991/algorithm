package SortAndSearch;

public class BinarySearch {

    /**
     * Classic Binary Search
     *
     * Note: If we use mid = (start + end) / 2; be careful about the big input: low = 1702766719, high = 2126753390
     * Then (high+low) overflows Integer limit
     */
    public int findPosition(int[] nums, int target) {
        if (nums == null || nums.length == 0)   return -1;

        int start = 0;
        int end = nums.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return -1;
    }

    /**
     * First Position of Target
     * [1, 2, 3, 3, 4, 5, 10] target = 3, first position = 2
     */
    public int binarySearchFirstPosition(int[] nums, int target) {
        if (nums == null || nums.length == 0)   return -1;

        int start = 0;
        int end = nums.length - 1;

        while (start + 1 < end) {
            int mid = start + (end - start) / 2;

            if (nums[mid] == target) {
                end = mid;
            } else if (nums[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }

        if (nums[start] == target) {
            return start;
        }

        if (nums[end] == target) {
            return end;
        }

        return -1;
    }

    /**
     * Last Position of Target
     * [1, 2, 3, 3, 4, 5, 10] target = 3, last position = 3
     */
    public int binarySearchLastPosition(int[] nums, int target) {
        if (nums == null || nums.length == 0)   return -1;

        int start = 0;
        int end = nums.length - 1;

        while (start + 1 < end) {
            int mid = start + (end - start) / 2;

            if (nums[mid] == target) {
                start = mid;
            } else if (nums[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }

        if (nums[end] == target) {
            return end;
        }

        if (nums[start] == target) {
            return start;
        }

        return -1;
    }
}
