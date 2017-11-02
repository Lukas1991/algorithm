package Tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Level Order Traveral
 */
public class SerializeTree {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) return "";
        StringBuilder sb = new StringBuilder();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node != null) {
                sb.append(node.val + ",");

                queue.offer(node.left);
                queue.offer(node.right);

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

        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = toNode(arr[0]);
        queue.offer(root);

        int i = 0;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            i++;
            if (i < arr.length) {
                TreeNode left = toNode(arr[i]);
                node.left = left;
                if (left != null) {
                    queue.offer(left);
                }
            }
            i++;
            if (i < arr.length) {
                TreeNode right = toNode(arr[i]);
                node.right = right;
                if (right != null) {
                    queue.offer(right);
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

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);

        node1.left = node2;
        node1.right = node3;
        node3.left = node4;
        node3.right = node5;

        SerializeTree solution = new SerializeTree();
        System.err.println(solution.serialize(node1));

    }
}
