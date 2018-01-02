package Tree;

/**
 * 230. Kth Smallest Element in a BST
 * In order traverse for BST gives the natural order of numbers.
 * O(k)
 */
public class kthSmallestInBST {

    int count = 0;
    int res = 0;

    public int kthSmallest(TreeNode root, int k) {
        traverse(root, k);
        return res;
    }

    private void traverse(TreeNode root, int k) {
        if (root == null)   return;

        traverse(root.left, k);

        count++;
        if (count == k) {
            res = root.val;
            return;
        }

        traverse(root.right, k);
    }

}
