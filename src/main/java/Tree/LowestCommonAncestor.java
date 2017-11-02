package Tree;

public class LowestCommonAncestor {

    /**
     * Lowest Common Ancestor of a Binary Tree
     * 根节点, 如果左子树找到p/q, 右子树也找到p/q, 根节点就是common ancestor
     * 如果左子树找到p/q, 右子树没找到, 返回左
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        System.err.println("root: " + (root == null ? "null" : root.val));
        if (root == null) return root;
        if (root == p || root == q) return root;

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        System.err.println(root.val + ".left: " + (left == null ? "null" : left.val));
        System.err.println(root.val + ".right: " + (right == null ? "null" : right.val));


        if (left != null && right != null) {
            return root;
        } else if (left == null && right == null) {
            return null;
        } else {
            return left == null ? right : left;
        }
    }

    //Lowest Common Ancestor of a Binary Search Tree
    public TreeNode lowestCommonAncestorBST(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }

        if ((root.val >= p.val && root.val <= q.val) || (root.val <=p.val && root.val >= q.val)) {
            return root;
        } else if (root.val > p.val && root.val > q.val) {
            return lowestCommonAncestor(root.left, p, q);
        } else {
            return lowestCommonAncestor(root.right, p, q);
        }
    }


    public static void main(String[] args) {
        LowestCommonAncestor lca = new LowestCommonAncestor();

        TreeNode n3 = new TreeNode(3);
        TreeNode n5 = new TreeNode(5);
        TreeNode n1 = new TreeNode(1);
        n3.left = n5; n3.right = n1;

        TreeNode n6 = new TreeNode(6);
        TreeNode n2 = new TreeNode(2);
        n5.left = n6; n5.right = n2;

        TreeNode n7 = new TreeNode(7);
        TreeNode n4 = new TreeNode(4);
        n2.left = n7;n2.right = n4;

        TreeNode n0 = new TreeNode(0);
        TreeNode n8 = new TreeNode(8);
        n1.left = n0; n1.right = n8;

        TreeNode ancestor = lca.lowestCommonAncestor(n3, n6, n4);
        System.err.println(ancestor.val);
    }
}
