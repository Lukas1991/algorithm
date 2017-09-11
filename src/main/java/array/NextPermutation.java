package array;

public class NextPermutation {
    //1. scan from end, find the number to swap, swap it with the next larger after it
    //2. reverse the reset
    public void nextPermutation(int[] nums) {
        int i = nums.length - 1;
        for (; i - 1 >= 0; i--) {
            if (nums[i] > nums[i - 1]) {
                break;
            }
        }

        //i-1 is the one to swap
        if (i == 0) {
            reverse(nums, i);
        } else {
            int j = nums.length - 1;
            for (; j >= i; j--) {
                if (nums[j] > nums[i - 1]) {
                    break;
                }
            }

            swap(nums, i - 1, j);
            reverse(nums, i);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    private void reverse(int[] nums, int i) {
        int s = i;
        int e = nums.length - 1;
        while (s < e) {
            swap(nums, s, e);
            s++;
            e--;
        }
    }
}
