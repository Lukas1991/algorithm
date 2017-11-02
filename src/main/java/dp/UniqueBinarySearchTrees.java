package dp;

import Tree.TreeNode;
import java.util.ArrayList;
import java.util.List;

public class UniqueBinarySearchTrees {

    /**
     * 96. Unique Binary Search Trees
     * Given n, how many structurally unique BST's (binary search trees) that store values 1...n?
     */
    public int numTrees(int n) {
        if (n == 0) {
            return 0;
        }
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            int count = 0;
            for (int j = 1; j <= i; j++) {
                //j is root
                int left = dp[j - 1];//比j小的有j-1个, dp[j-1]种排列可能
                int right = dp[i - j];//比j大的有i-j个, dp[i-j]种排列可能
                count += left * right;
            }
            dp[i] = count;
        }

        return dp[n];
    }

    /**
     * 95. Unique Binary Search Trees II.
     * Recursion.
     */
    public List<TreeNode> generateTrees(int n) {
        if (n <= 0) {
            return new ArrayList<>();
        } else {
            return generateTrees(1, n);
        }
    }

    private List<TreeNode> generateTrees(int start, int end) {
        List<TreeNode> res = new ArrayList<>();

        if (start > end) {
            res.add(null);
            return res;
        }

        for (int i = start; i <= end; i++) {
            List<TreeNode> lefts = generateTrees(start, i - 1);
            List<TreeNode> rights = generateTrees(i + 1, end);

            //count = lefts * rights
            for (TreeNode left : lefts) {
                for (TreeNode right : rights) {
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    res.add(root);
                }
            }
        }

        return res;
    }
}
