package Tree;

import java.util.ArrayList;
import java.util.List;

public class RightSideView {
    /**
     * 1.Each depth of the tree only select one node.
     * 2. View depth is current size of result list.
     * Always call root.right first
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        rightView(root, res, 0);
        return res;
    }

    private void rightView(TreeNode root, List<Integer> res, int level) {
        if (root == null) return;
        if (res.size() == level) {
            res.add(root.val);
        }

        rightView(root.right, res, level+1);
        rightView(root.left, res, level+1);
    }
}
