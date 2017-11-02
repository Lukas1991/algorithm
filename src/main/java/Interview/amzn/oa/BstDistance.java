package Interview.amzn.oa;

public class BstDistance {

	public static void main(String[] args) {

		BstDistance run = new BstDistance();
		int[] values = { 5, 6, 3, 1, 2, 4 };
		System.out.println(run.bstDistance(values, 6, 2, 4));
	}

	private TreeNode root;

	public int bstDistance(int[] values, int n, int node1, int node2) {

		if (!exits(values, node1) || !exits(values, node2))
			return -1;

		for (int v : values) {
			insert(v);
		}

		return getDistance(root, node1, node2);

	}

	int getDistance(TreeNode root, int p, int q) {
		if (p > q) {
			return getDistance(root, q, p);
		}

		if (root.val > p && root.val > q) {
			return getDistance(root.left, p, q);
		} else if (root.val < p && root.val < q) {
			return getDistance(root.right, p, q);
		} else {
			int leftCount = getDis(root, p);
			int rightCount = getDis(root, q);
			return leftCount + rightCount;
		}
	}

	private int getDis(TreeNode root, int p) {
		TreeNode current = root;
		int count = 0;
		while (current != null) {
			if (current.val == p) {
				return count;
			} else if (p < current.val) {
				current = current.left;
				count++;
			} else {
				current = current.right;
				count++;
			}
		}
		return count;
	}

	private boolean exits(int[] values, int v) {

		for (int i : values) {

			if (i == v)
				return true;
		}

		return false;
	}

	private void insert(int val) {

		TreeNode newNode = new TreeNode(val);
		if (root == null) {
			root = newNode;
			return;
		}

		TreeNode parent = root;
		TreeNode current = root;

		while (true) {
			parent = current;
			if (val < current.val) {
				current = current.left;
				if (current == null) {
					parent.left = newNode;
					return;
				}
			} else {
				current = current.right;
				if (current == null) {
					parent.right = newNode;
					return;
				}
			}
		}
	}

	class TreeNode {
		public int val;
		public TreeNode left;
		public TreeNode right;

		public TreeNode(int val) {
			this.val = val;
		}

	}
}
