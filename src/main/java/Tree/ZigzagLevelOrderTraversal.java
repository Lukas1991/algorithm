package Tree;

import java.util.ArrayList;
import java.util.List;

public class ZigzagLevelOrderTraversal {

    //DFS
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(root, 0, res);
        return res;
    }

    void dfs(TreeNode root, int depth, List<List<Integer>> res) {
        if (root == null) {
            return;
        }
        if (res.size() == depth) {
            res.add(new ArrayList<>());
        }
        if (depth % 2 == 0) {
            res.get(depth).add(root.val);
        } else {
            res.get(depth).add(0, root.val);
        }

        dfs(root.left, depth + 1, res);
        dfs(root.right, depth + 1, res);
    }


    //Iterative
    public List<List<Integer>> zigzagLevelOrder2(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        List<TreeNode> curr = new ArrayList<>();
        curr.add(root);
        int level = 0;

        while (!curr.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            List<TreeNode> nextLevel = new ArrayList<>();

            for (TreeNode node : curr) {
                if (level % 2 == 0) {
                    list.add(node.val);
                } else {
                    list.add(0, node.val);
                }

                if (node.left != null) {
                    nextLevel.add(node.left);
                }
                if (node.right != null) {
                    nextLevel.add(node.right);
                }
            }

            res.add(list);
            curr = nextLevel;
            level++;
        }
        return res;
    }
}
