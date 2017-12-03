package Tree;

/**
 * 110. Balanced Binary Tree
 */
public class CheckBalance {


    /**
     * Bottom up O(N)
     * checkHeight checks if the tree is balanced and checks tree's height at the same time.
     * if the subtree isn't balanced, return -1
     * Time O(N) and O(H) space, where H is the height of the tree
     */
    public boolean isBalanced(TreeNode root) {
        return checkHeight(root) != -1;
    }

    private int checkHeight(TreeNode root) {
        if (root == null) return 0;

        int leftHeight = checkHeight(root.left);
        if (leftHeight == -1) return -1;//left not balanced

        int rightHeight = checkHeight(root.right);
        if (rightHeight == -1) return -1;//right not balanced

        if (Math.abs(leftHeight - rightHeight) > 1) return -1;

        return Math.max(leftHeight, rightHeight) + 1;
    }
}
