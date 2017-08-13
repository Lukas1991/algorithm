package array;

public class MaximumSubArray {
    /**
     * Find the contiguous subarray within an array (containing at least one number) which has the largest sum.
     *
     * For example, given the array [-2,1,-3,4,-1,2,1,-5,4], the contiguous subarray [4,-1,2,1] has the largest sum = 6.
     */
    public int maxSubArrayBetter(int[] A) {
        int newsum = A[0];//instead of using an arr, track the max sum of A[0 - i], ends at i!!!
        int max = A[0];

        for (int i = 1; i < A.length; i++) {
            newsum = Math.max(newsum + A[i], A[i]); //if sum[i-1] < 0, ignore sum[i-1]

            max = Math.max(max, newsum);  //change global max value
        }

        return max;
    }

    /**
     * Find the contiguous subarray within an array (containing at least one number) which has the largest product.
     *
     * For example, given the array [2,3,-2,4], the contiguous subarray [2,3] has the largest product = 6.
     */
    public int maxProduct(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int max = nums[0];
        int min = nums[0];
        int maxProduct = max;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < 0) {
                int tmp = max;
                max = min;
                min = tmp;
                System.err.println("after swap max: " + max + " min: " + min);
            }

            max = Math.max(max * nums[i], nums[i]);
            min = Math.min(min * nums[i], nums[i]);

            maxProduct = Math.max(maxProduct, max);
            System.err.println("max: " + max + " min: " + min);
        }
        return maxProduct;
    }

    public static void main(String[] args) {
        MaximumSubArray obj = new MaximumSubArray();

        int[] nums = {2, 3, -2, -2};
        obj.maxProduct(nums);
    }

}
