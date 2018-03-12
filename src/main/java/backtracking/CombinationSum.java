package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CombinationSum {

    /**
     * 39. Combination Sum
     * @param candidates, without duplicates. The same repeated number may be chosen from C unlimited number of times.
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        helper(candidates, target, res, new ArrayList<>(), 0);
        return res;
    }

    private void helper(int[] candidates, int remain, List<List<Integer>> res, List<Integer> tmp, int start) {
        if (remain == 0) {
            res.add(new ArrayList<>(tmp));
            return;
        } else if (remain < 0) {
            return;
        } else {
            for (int i = start; i < candidates.length; i++) {
                int a = candidates[i];
                tmp.add(a);
                helper(candidates, remain - a, res, tmp, i);
                tmp.remove(tmp.size() - 1);
            }
        }
    }

    /**
     * 40. Combination Sum II
     * @param candidates candidates contains duplicate. Each number in candidates may only be used once in the combination.
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        helper2(candidates, target, res, new ArrayList<>(), 0);
        return res;
    }

    private void helper2(int[] candidates, int remain, List<List<Integer>> res, List<Integer> tmp, int start) {
        if (remain == 0) {
            res.add(new ArrayList<>(tmp));
            return;
        } else if (remain < 0) {
            return;
        } else {
            for (int i = start; i < candidates.length; i++) {
                if (i > start && candidates[i] == candidates[i-1])  continue;

                int a = candidates[i];
                tmp.add(a);
                helper2(candidates, remain - a, res, tmp, i+1);
                tmp.remove(tmp.size() - 1);
            }
        }
    }

    /**
     * 216. Combination Sum III
     * Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.
     * [1-9] 取k个数字，sum = n. 数字不能重复使用
     */
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();

        helper3(1, n, new ArrayList<>(), res, k);
        return res;
    }

    void helper3(int start, int remain, List<Integer> tmp, List<List<Integer>> res, int k) {
        if (remain < 0) {
            return;
        } else if (tmp.size() == k) {
            if (remain == 0) {
                res.add(new ArrayList<>(tmp));
            }
        } else {
            for (int i = start; i < 10; i++) {
                tmp.add(i);
                helper3(i + 1, remain - i, tmp, res, k);
                tmp.remove(tmp.size() - 1);
            }
        }
    }

    /**
     * 377. Combination Sum IV
     * Given an integer array with all positive numbers and no duplicates, find the number of possible combinations that add up to a positive integer target.
     * DP
     */
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;

        for (int i = 1; i <= target; i++) {
            dp[i] = 0;
            for (int j = 0; j < nums.length; j++) {
                if (i - nums[j] >= 0) {
                    dp[i] += dp[i - nums[j]];
                }
            }
        }

        return dp[target];
    }

    Map<Integer, Integer> map = new HashMap<>();

    public int combinationSumMap(int[] nums, int target) {
        if (target < 0) {
            return 0;
        }
        map.put(0, 1);

        if (!map.containsKey(target)) {
            int count = 0;
            for (int j = 0; j < nums.length; j++) {
                count += combinationSumMap(nums, target - nums[j]);
            }

            map.put(target, count);

        }

        return map.get(target);
    }

    /**
     * Follow up:
     * What if negative numbers are allowed in the given array?
     * How does it change the problem?
     * What limitation we need to add to the question to allow negative numbers?
     *
     * The problem with negative numbers is that now the combinations could be potentially of infinite length. Think about nums = [-1, 1] and target = 1. 可以有N个-1， N+1个1 组成target = 1.
     * So we should limit the length of the combination sequence, so as to give a bound to the problem.
     */
    //key is target, value is map(key is len)
    Map<Integer, Map<Integer, Integer>> map2;

    public int combinationSumMapWithLength(int[] nums, int target, int MaxLen) {
        if (nums == null || nums.length == 0 || MaxLen <= 0) {
            return 0;
        }
        map2 = new HashMap<>();
        return helper4(nums, target, 0, MaxLen);
    }

    public int helper4(int[] nums, int target, int len, int maxLen) {
        if (len > maxLen) {
            return 0;
        }

        if (map2.containsKey(target) && map2.get(target).containsKey(len)) {
            return map2.get(target).get(len);
        } else {
            if (!map2.containsKey(target)) {
                map2.put(target, new HashMap<>());
            }

            int count = 0;
            if (target == 0) {
                count++;
            }

            for (int j = 0; j < nums.length; j++) {
                count += helper4(nums, target - nums[j], len + 1, maxLen);
            }

            map2.get(target).put(len, count);
            return count;
        }
    }

    public static void main(String[] args) {
        CombinationSum obj = new CombinationSum();
        int[] nums = {-1, 1};
        System.err.println(obj.combinationSumMapWithLength(nums, 1, 1)); //1
        System.err.println(obj.combinationSumMapWithLength(nums, 1, 2)); //1
        System.err.println(obj.combinationSumMapWithLength(nums, 1, 3)); //4
        //[1], [1,1,-1], [1, -1, 1], [-1, 1, 1]
    }
}
