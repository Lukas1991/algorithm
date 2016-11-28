package Tree;

/**
 * Find the number of paths that sum to a given value.
 * The path does not need to start or end at the root or a leaf, but it must go downwards
 * (traveling only from parent nodes to child nodes).
 */
public class PathSumIII {

    public int pathSum(TreeNode root, int sum) {
        if (root == null) return 0;
        int count = pathSumStartWith(root, sum);

        count = count + pathSum(root.left, sum) + pathSum(root.right, sum);
        return count;
    }

    public int pathSumStartWith(TreeNode root, int sum) {
        if (root == null) return 0;
        int count = 0;
        int target = sum - root.val;
        if (target == 0) {
            count++;
        }
        count += pathSumStartWith(root.left, target) + pathSumStartWith(root.right, target);
        return count;
    }
}
