package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
 */
public class Combinations {

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        helper(1, n, k, new ArrayList<>(), res);
        return res;
    }

    void helper(int start, int n, int k, List<Integer> tmp, List<List<Integer>> res) {
        if (tmp.size() == k) {
            res.add(new ArrayList<>(tmp));
        } else {
            for (int i = start; i <= n; i++) {
                tmp.add(i);
                helper(i + 1, n, k, tmp, res);
                tmp.remove(tmp.size() - 1);
            }
        }
    }
}
