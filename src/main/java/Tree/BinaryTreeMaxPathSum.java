package Tree;

/**
 * 124. Given a binary tree, find the maximum path sum.
 * For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree
 * along the parent-child connections. The path must contain at least one node and does not need to go through the root.
 */
public class BinaryTreeMaxPathSum {

    private int max = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        calculateSum(root);
        return max;
    }

    //Get largest left sum and right sum
    //compare with max
    public int calculateSum(TreeNode root) {
        if (root == null) return 0;
        int left = Math.max(calculateSum(root.left), 0);
        int right = Math.max(calculateSum(root.right), 0);

        max = Math.max(max, root.val + left + right);

        return root.val + Math.max(left, right);
    }
}
