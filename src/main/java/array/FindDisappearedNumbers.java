package array;

import java.util.ArrayList;
import java.util.List;

public class FindDisappearedNumbers {
    /**
     * Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once. Find
     * all the elements of [1, n] inclusive that do not appear in this array.
     *
     * Iterate the array, mark elements as negative, nums[nums[i] -1] = -nums[nums[i]-1] In this way all the numbers that we have
     * seen will be marked as negative. In the second iteration, if value > 0, it implies we have never seen that index before, so
     * just add it to the return list.
     */
    public static List<Integer> findDisappearedNumbers(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]) - 1;
            if (nums[index] > 0) {
                nums[index] = -nums[index];
            }
        }

        List<Integer> res = new ArrayList<>();
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] > 0) {
                res.add(j + 1);
            }
        }

        return res;
    }

    public static void main(String[] args) {
        int[] nums = {4, 3, 2, 7, 8, 2, 3, 1};
        //should return {5,6}
        List<Integer> res = findDisappearedNumbers(nums);
        System.err.println(res.toString());
    }
}
