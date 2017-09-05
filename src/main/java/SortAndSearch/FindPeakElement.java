package SortAndSearch;

public class FindPeakElement {

    /**
     * Binary Search, Time complexity : O(log​2(n))
     */
    public int findPeakElementBest(int[] nums) {
        int l = 0;
        int r = nums.length - 1;

        while (l < r) {
            int mid = (l + r) / 2;

            if (nums[mid] > nums[mid + 1]) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }

        return l;
    }

    /**
     * Linear Scan, Time complexity : O(n)
     */
    public int findPeakElement(int[] nums) {

        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                return i;
            }
        }

        return nums.length - 1;
    }

}
