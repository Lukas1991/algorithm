package Tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * 742. Closest Leaf in a Binary Tree
 *
 * Given a binary tree where every node has a unique value, and a target key k, find the value of the nearest leaf node to target k in the tree.
 *
 * Here, nearest to a leaf means the least number of edges travelled on the binary tree to reach any leaf of the tree.
 * Also, a node is called a leaf if it has no children.
 *
 * In the following examples, the input tree is represented in flattened form row by row. The actual root tree given will be a TreeNode object.
 */
public class FindClosestLeaf {

    //convert to graph in DFS, find the shortest path to a leaf using BFS
    public int findClosestLeaf(TreeNode root, int k) {
        Map<TreeNode, List<TreeNode>> graph = new HashMap<>();
        dfs(graph, root, null);

        Queue<TreeNode> queue = new LinkedList<>();
        Set<TreeNode> set = new HashSet<>();

        //find target
        for (TreeNode node : graph.keySet()) {
            if (node.val == k) {
                queue.offer(node);
                set.add(node);
                break;
            }
        }

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left == null && node.right == null) {
                    return node.val;
                }

                for (TreeNode nei : graph.get(node)) {
                    if (nei != null && !set.contains(nei)) {
                        set.add(nei);
                        queue.offer(nei);
                    }
                }
            }
        }

        return -1;
    }

    void dfs(Map<TreeNode, List<TreeNode>> graph, TreeNode node, TreeNode parent) {
        if (node == null) {
            return;
        }
        graph.putIfAbsent(node, new ArrayList<>());

        if (parent != null) {
            graph.get(node).add(parent);
        }

        if (node.left != null) {
            graph.get(node).add(node.left);
            dfs(graph, node.left, node);
        }

        if (node.right != null) {
            graph.get(node).add(node.right);
            dfs(graph, node.right, node);
        }

    }

    public static void main(String[] args) {
        FindClosestLeaf obj = new FindClosestLeaf();

        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);

        n1.left = n2;
        n1.right = n3;

        n2.left = n4;
        n4.left = n5;
        n5.left = n6;

        int test = obj.findClosestLeaf(n1, 2);
        System.err.println(test);
    }
}
