package Tree;

/**
 * 450. Delete Node in a BST
 */
public class DeleteNode {

    /**
     * Once the node is found, have to handle the below 4 cases
     * 1. node doesn’t have left or right - return null
     * 2. node only has left subtree - return the left subtree
     * 3. node only has right subtree - return the right subtree
     * 4. node has both left and right - find the minimum value in the right subtree, set that value to the currently found node,
     * then recursively delete the minimum value in the right subtree
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null)   return null;

        if (key < root.val) {
            root.left = deleteNode(root.left, key);
        } else if (key > root.val) {
            root.right = deleteNode(root.right, key);
        } else {
            //find
            if (root.left == null && root.right == null) {
                return null;
            } else if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            } else {
                int min = findMin(root.right);
                root.val = min;
                root.right = deleteNode(root.right, min);
            }
        }

        return root;
    }

    private int findMin(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }

        return node.val;
    }
}
