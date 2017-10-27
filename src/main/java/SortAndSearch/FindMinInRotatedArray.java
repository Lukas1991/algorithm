package SortAndSearch;

/**
 * 153. Find Minimum in Rotated Sorted Array
 * 154. Find Minimum in Rotated Sorted Array II
 */
public class FindMinInRotatedArray {

    //Binary Search O(logn)
    public int findMin(int[] nums) {
        int start = 0;
        int end = nums.length - 1;

        while (start < end) {
            if (nums[start] < nums[end]) {
                return nums[start];
            } else {
                int mid = (start + end) / 2;
                //注意有等于，例如[2,1]
                if (nums[start] <= nums[mid]) {
                    start = mid + 1;
                } else {
                    end = mid;
                }
            }
        }

        return nums[start];
    }

    public int findMinDuplicate(int[] nums) {
        int start = 0;
        int end = nums.length - 1;

        //the only difference!
        while (nums[end] == nums[start] && end > start) {
            end--;
        }

        while (start < end) {
            if (nums[start] < nums[end]) {
                return nums[start];
            } else {
                int mid = (start + end) / 2;
                //注意有等于，例如[2,1]
                if (nums[start] <= nums[mid]) {
                    start = mid + 1;
                } else {
                    end = mid;
                }
            }
        }

        return nums[start];
    }


    //O(n)
    public int findMinON(int[] nums) {
        if (nums.length == 0)   return -1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i-1]) {
                return nums[i];
            }
        }

        return nums[0];
    }
}
