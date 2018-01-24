package SortAndSearch;


public class SortColors {

    /**
     * two pointers start and end. start 左边都是0, end右边都是2
     * one-pass
     */
    //跟2换完,nums[i]可能是0,1,2.不确定换回来的还是在正确位置，所以i不变
    //跟0换完,nums[i]只可能是0,1. 因为left在i前面，前面是已经排好序的，所以i++
    public void sortColors(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        int i = 0;
        while (i <= right) {
            if (nums[i] == 0) {
                swap(nums, i, left);
                left++;
                i++;
            } else if (nums[i] == 2) {
                swap(nums, i, right);
                right--;
            } else {
                i++;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
