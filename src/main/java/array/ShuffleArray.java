package array;

import java.util.Random;

/**
 * 384. Shuffle an Array
 * Shuffle a set of numbers without duplicates.
 */
public class ShuffleArray {

    private int[] nums;
    Random random;

    public ShuffleArray(int[] nums) {
        this.nums = nums;
        random = new Random();
    }

    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return nums;
    }

    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        if(nums == null) return null;

        int[] arr = nums.clone();
        for (int i = nums.length - 1; i >= 0; i--) {
            int index = random.nextInt(i+1);
            swap(arr, i, index);
        }

        return arr;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
