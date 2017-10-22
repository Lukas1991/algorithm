package SortAndSearch;

import java.util.Arrays;
import java.util.Random;

public class SortColors {

    /**
     * two pointers start and end, start 左边都是0, end右边都是2
     *
     * 一定先查num[i]==2. while的作用是可能num[end]就是2, swap之后相当于没变,end--,但是还可以swap
     */
    public static void sortColors(int[] nums) {
        int start = 0;
        int end = nums.length - 1;

        for (int i = 0; i <=end; i++) {
            //have to check 2 before check 0
            //跟2换完,nums[i]只可能是0,1。如果是0, 跟start换,start一定在i之前,说明start之前已经查过了,可能的值只有1或者0, 不可能换着换着换出来个2出来。
            while (nums[i] == 2 && i < end) {
                swap(nums, end, i);
                end--;
            }

            while (nums[i] == 0 && i > start) {
                swap(nums, start, i);
                start++;
            }
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }


    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            int[] nums = generateArray();
            sortColors(nums);
            System.err.println(isSorted(nums) + " - " + Arrays.toString(nums));
        }
    }

    static Random random = new Random();

    static int[] generateArray() {
        int size = random.nextInt(10);
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(3);
        }
        return arr;
    }

    static boolean isSorted(int[] arr) {
        if (arr.length < 2) return true;
        int pre = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] >= pre) {
                pre = arr[i];
            } else {
                return false;
            }
        }
        return true;
    }
}
