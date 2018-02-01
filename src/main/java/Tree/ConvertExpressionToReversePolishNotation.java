package Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 构建expression tree, post-order traverse
 */
public class ConvertExpressionToReversePolishNotation {

    int get(String a, int base) {
        if (a.equals("+") || a.equals("-"))
            return 1 + base;
        if (a.equals("*") || a.equals("/"))
            return 2 + base;

        //numbers are the largest rank
        return Integer.MAX_VALUE;
    }

    public List<String> convertToRPN(String[] expression) {
        // write your code here
        Stack<TreeNode> stack = new Stack<TreeNode>();
        int val = 0;
        int base = 0;
        for (int i = 0; i <= expression.length; i++) {
            if (i != expression.length) {

                if (expression[i].equals("(")) {
                    base += 10;
                    continue;
                }
                if (expression[i].equals(")")) {
                    base -= 10;
                    continue;
                }
                val = get(expression[i], base);

            }

            TreeNode right = i == expression.length ? new TreeNode(Integer.MIN_VALUE, "") : new TreeNode(val, expression[i]);

            while (!stack.isEmpty()) {
                if (right.val <= stack.peek().val) {
                    TreeNode nodeNow = stack.pop();

                    if (stack.isEmpty()) {//pop之前stack只剩一个了，之前已经构成一个完整的tree，新的right node只能往上加一层
                        right.left = nodeNow;

                    } else {
                        //不从stack中拿出来
                        TreeNode left = stack.peek();
                        if (left.val < right.val) {//如果right val大，right node在上面作为parent
                            right.left = nodeNow;
                        } else {
                            left.right = nodeNow;//如果right val小，left node在上面作为parent, right node 就是右孩子
                        }
                    }
                } else {
                    break;
                }
            }

            stack.push(right);
        }

        List<String> result = new ArrayList<String>();
        //post-order traverse
        reversePolishDFS(stack.peek().left, result);

        //pre-order traverse
        //polishDFS(stack.peek().left, result);

        return result;
    }

    void reversePolishDFS(TreeNode root, List<String> as) {
        if (root == null)
            return;
        if (root.left != null)
            reversePolishDFS(root.left, as);

        if (root.right != null)
            reversePolishDFS(root.right, as);
        as.add(root.s);
    }

    void polishDFS(TreeNode root, List<String> as) {
        if (root == null)
            return;

        as.add(root.s);

        if (root.left != null)
            polishDFS(root.left, as);

        if (root.right != null)
            polishDFS(root.right, as);
    }

    class TreeNode {
        public int val;
        public String s;
        public TreeNode left, right;

        public TreeNode(int val, String ss) {
            this.val = val;
            this.s = ss;
            this.left = this.right = null;
        }

    }

    public static void main(String[] args) {
        ConvertExpressionToReversePolishNotation obj = new ConvertExpressionToReversePolishNotation();
        //String[] expression = {"3", "-", "4", "+", "5"};
        String[] expression = {"6", "+", "(", "3", "-", "4", ")", "+", "5"};
        List<String> reversepolish = obj.convertToRPN(expression);
        System.err.println(reversepolish.toString());
    }
}
