package Tree;

import java.util.LinkedList;

/**
 * Level Order Traveral
 */
public class SerializeTree {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) return "";
        StringBuilder sb = new StringBuilder();

        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node != null) {
                sb.append(node.val + ",");

                queue.add(node.left);
                queue.add(node.right);

            } else {
                sb.append("#,");
            }
        }

        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) return null;
        String[] arr = data.split(",");
        if (arr.length == 0) return null;

        LinkedList<TreeNode> queue = new LinkedList<>();
        TreeNode root = toNode(arr[0]);
        queue.add(root);

        int i = 0;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            i++;
            if (i < arr.length) {
                TreeNode left = toNode(arr[i]);
                node.left = left;
                if (left != null) {
                    queue.add(left);
                }
            }
            i++;
            if (i < arr.length) {
                TreeNode right = toNode(arr[i]);
                node.right = right;
                if (right != null) {
                    queue.add(right);
                }
            }
        }

        return root;
    }

    private TreeNode toNode(String str) {
        if (str.equals("#")) {
            return null;
        } else {
            return new TreeNode(Integer.valueOf(str));
        }
    }
}
