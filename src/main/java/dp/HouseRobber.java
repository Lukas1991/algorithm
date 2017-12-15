package dp;

import Tree.TreeNode;

public class HouseRobber {

    public int rob(int[] num) {
        return rob(num, 0, num.length - 1);
    }

    public int rob(int[] num, int lo, int hi) {
        int prepre = 0;
        int pre = 0;

        for (int i = lo; i <= hi; i++) {
            int cur = Math.max(prepre + num[i], pre);
            prepre = pre;
            pre = cur;
        }

        return Math.max(prepre, pre);
    }

    /**
     * All houses at this place are arranged in a circle.
     */
    public int rob2(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums[0];
        return Math.max(rob(nums, 0, n-2), rob(nums,1, n-1));
    }

    public int rob(TreeNode root) {
        int[] res = robHelper(root);
        return Math.max(res[0], res[1]);
    }

    //res[0] is include root -> rob root
    //res[1] is exclude root -> do not rob root
    private int[] robHelper(TreeNode root) {
        int[] res = new int[2];
        if (root == null)   return res;

        int[] left = robHelper(root.left);
        int[] right = robHelper(root.right);

        res[0] = left[1] + right[1] + root.val;
        res[1] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);

        return res;
    }

    public static void main(String[] args) {
        HouseRobber obj = new HouseRobber();
        System.err.println(obj.rob2(new int[]{20, 15}));
    }
}
