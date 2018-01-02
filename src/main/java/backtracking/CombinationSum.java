package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
}
