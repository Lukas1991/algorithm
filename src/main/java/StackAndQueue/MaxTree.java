package StackAndQueue;

import Tree.TreeNode;
import java.util.Stack;

public class MaxTree {

    /*
     * 如果stack里有比当前数小的，tmp.left = pop小的数
     * 如果stack里有比当前数大的，peek大的数.right = tmp
     * tmp放入stack里
     * stack最底部为最大的数
     *
     * O(n)
     */
    public TreeNode maxTree(int[] A) {
        Stack<TreeNode> stack = new Stack<>();
        for (int i = 0; i < A.length; i++) {
            TreeNode tmp = new TreeNode(A[i]);
            while (!stack.isEmpty() && A[i] > stack.peek().val) {
                tmp.left = stack.pop();
            }

            if (!stack.isEmpty()) {
                stack.peek().right = tmp;
            }

            stack.push(tmp);
        }

        //last one in stack
        TreeNode last = null;
        while (!stack.isEmpty()) {
            last = stack.pop();
        }
        return last;
    }

    //---------------递归方法----------------------
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return constructMaximumBinaryTree(nums, 0, nums.length - 1);
    }

    public TreeNode constructMaximumBinaryTree(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }
        int maxIndex = findMax(nums, start, end);
        TreeNode node = new TreeNode(nums[maxIndex]);
        node.left = constructMaximumBinaryTree(nums, start, maxIndex - 1);
        node.right = constructMaximumBinaryTree(nums, maxIndex + 1, end);
        return node;
    }

    public int findMax(int[] nums, int start, int end) {
        int max = start;
        for (int i = start + 1; i <= end; i++) {
            if (nums[i] > nums[max]) {
                max = i;
            }
        }
        return max;
    }
}
