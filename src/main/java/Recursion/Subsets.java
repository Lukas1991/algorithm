package Recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Subsets {

    /**
     * Given a set of distinct integers, nums, return all possible subsets.
     *
     * Note: The solution set must not contain duplicate subsets.
     *
     * For example, If nums = [1,2,3], a solution is: [ [3], [1], [2], [1,2,3], [1,3], [2,3], [1,2], [] ]
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();

        helper(res, new ArrayList<>(), 0, nums);
        return res;
    }

    private void helper(List<List<Integer>> res, List<Integer> tmp, int start, int[] nums) {
        res.add(new ArrayList<>(tmp));

        for (int i = start; i < nums.length; i++) {
            tmp.add(nums[i]);
            helper(res, tmp, i + 1, nums);
            tmp.remove(tmp.size() - 1);
        }
    }

    /**
     * Given a collection of integers that might contain duplicates, nums, return all possible subsets.
     *
     * Note: The solution set must not contain duplicate subsets.
     *
     * For example, If nums = [1,2,2], a solution is:
     *
     * [ [2], [1], [1,2,2], [2,2], [1,2], [] ]
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);

        helper2(res, new ArrayList<>(), 0, nums);
        return res;
    }

    private void helper2(List<List<Integer>> res, List<Integer> tmp, int start, int[] nums) {
        res.add(new ArrayList<>(tmp));

        for (int i = start; i < nums.length; i++) {
            tmp.add(nums[i]);
            helper2(res, tmp, i + 1, nums);
            tmp.remove(tmp.size() - 1);
            while (i + 1 < nums.length && nums[i + 1] == nums[i]) {
                i++;
            }
        }
    }

    public static void main(String[] args) {
        Subsets obj = new Subsets();
        int[] nums = {5, 5, 5};
        List<List<Integer>> res = obj.subsetsWithDup(nums);

        for (List<Integer> list : res) {
            System.err.println(list.toString());
        }
    }

}
