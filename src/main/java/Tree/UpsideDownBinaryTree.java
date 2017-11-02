package Tree;

public class UpsideDownBinaryTree {

    /**
     * update the root.left node instead of newRoot. newRoot is the left most node which will never be changed, and it will pass
     * back to be the final root node
     */
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        if (root.left == null && root.right == null) {
            return root;
        }

        TreeNode newRoot = upsideDownBinaryTree(root.left);
        root.left.left = root.right;
        root.left.right = root;
        root.left = null;
        root.right = null;
        return newRoot;
    }
}
