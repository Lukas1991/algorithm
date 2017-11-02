package Interview.amzn.oa;

import Interview.amzn.oa.model.TreeNode;

/**
 * https://yeqiuquan.blogspot.com/2017/03/lintcode-628-maximum-subtree.html
 * 
 * @author dacai
 *
 */
public class MaximumSubtree {

	private int maxSum = Integer.MIN_VALUE;
	private TreeNode ret = null;

	public TreeNode findSubtree(TreeNode root) {

		find(root);
		return ret;
	}

	public int find(TreeNode node) {

		if (node == null)
			return 0;

		int left = find(node.left);
		int right = find(node.right);

		if (left + right + node.val > maxSum) {
			maxSum = left + right + node.val;
			ret = node;
		}

		return node.val + left + right;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
