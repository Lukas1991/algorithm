package Tree;

public class ValidateBST {

	/**
     * All values on the left sub tree must be less than root,
     * and all values on the right sub tree must be greater than root.
	 */
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, null, null);
    }

    private boolean isValidBST(TreeNode root, Integer min, Integer max) {
        if (root == null) {
            return true;
        }

        if (min != null && root.val <= min) {
			return false;
		}

        if (max != null && root.val >= max) {
            return false;
        }

        return isValidBST(root.left, min, root.val) && isValidBST(root.right, root.val, max);
	}

}
