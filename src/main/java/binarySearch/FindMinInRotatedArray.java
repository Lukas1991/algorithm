package binarySearch;

/**
 * 153. Find Minimum in Rotated Sorted Array
 * 154. Find Minimum in Rotated Sorted Array II
 */
public class FindMinInRotatedArray {

    /**
     * You may assume no duplicate exists in the array.
     * Binary Search O(logn)
     * mid 跟A[last]比较，判断是左边还是右边
     */
    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int start = 0, end = nums.length - 1;
        int last = nums[nums.length - 1];

        while (start + 1 < end) {
            int mid = start + (end - start) / 2;

            if (nums[mid] > last) {
                start = mid;
            } else {
                end = mid;
            }
        }

        if (nums[start] < nums[end]) {
            return nums[start];
        } else {
            return nums[end];
        }
    }

    /**
     * Follow up, The array may contain duplicates. Would this affect the run-time complexity? How and why?
     * 这道题目在面试中不会让写完整的程序
     * 只需要知道最坏情况下 [1,1,1....,1] 里有一个0
     * 这种情况使得时间复杂度必须是 O(n)
     * 因此写一个for循环就好了。
     * 如果你觉得，不是每个情况都是最坏情况，你想用二分法解决不是最坏情况的情况，那你就写一个二分吧。
     * 反正面试考的不是你在这个题上会不会用二分法。这个题的考点是你想不想得到最坏情况。
     */
    public int findMinDuplicate(int[] nums) {
        int start = 0;
        int end = nums.length - 1;

        //the only difference!
        while (nums[end] == nums[start] && start < end) {
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

        int min = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < min) {
                min = nums[i];
            }
        }
        return min;
    }
}
