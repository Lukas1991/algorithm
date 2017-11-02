package Tree;

public class InvertBinaryTree {

    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;

        TreeNode invertLeft = invertTree(root.left);
        TreeNode invertRight = invertTree(root.right);
        root.left = invertRight;
        root.right = invertLeft;

        return root;
    }
}
