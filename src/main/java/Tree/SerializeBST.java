package Tree;

import java.util.LinkedList;
import java.util.Stack;

public class SerializeBST {

    /**
     * pre-order
     */
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        if (root == null) return "";

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while(!stack.isEmpty()) {
            TreeNode node = stack.pop();
            sb.append(node.val + ",");
            if (node.right != null) stack.push(node.right);
            if (node.left != null) stack.push(node.left);
        }

        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.isEmpty()) return null;
        String[] arr = data.split(",");
        LinkedList<Integer> queue = new LinkedList<>();
        for(String s : arr) {
            queue.offer(Integer.valueOf(s));
        }

        return getNode(queue);
    }

    // some notes:
    //   5
    //  3 6
    // 2   7
    private TreeNode getNode(LinkedList<Integer> queue) { //q: 5,3,2,6,7
        if (queue.isEmpty()) return null;
        int val = queue.poll();
        TreeNode root = new TreeNode(val);
        LinkedList<Integer> smallQueue = new LinkedList<>();
        while(!queue.isEmpty() && queue.peek() < val) {
            smallQueue.offer(queue.poll());
        }
        //smallerQueue : 3,2   storing elements smaller than 5 (root)
        //the rest in queue: 6,7   storing elements bigger than 5 (root)
        root.left = getNode(smallQueue);
        root.right = getNode(queue);

        return root;
    }

    public static void main(String[] args) {
        TreeNode node5 = new TreeNode(5);

        TreeNode node3 = new TreeNode(3);
        TreeNode node2 = new TreeNode(2);

        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);

        node5.left = node3;
        node5.right = node6;

        node3.left = node2;

        node6.right = node7;

        SerializeBST solution = new SerializeBST();
        System.err.println(solution.serialize(node5));  //5,3,2,6,7

    }

}
