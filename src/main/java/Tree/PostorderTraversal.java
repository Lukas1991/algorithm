package Tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class PostorderTraversal {

    public List<Integer> postorderTraversal(TreeNode root) {
        LinkedList<Integer> list = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                list.addFirst(cur.val);
                stack.push(cur);
                cur = cur.right;
            } else {
                TreeNode node = stack.pop();
                cur = node.left;
            }
        }

        return list;
    }
}
