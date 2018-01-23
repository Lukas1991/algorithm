package fun;

/**
 * Given n non-negative integers representing an elevation map where the width of each bar is 1,
 * compute how much water it is able to trap after raining.
 * For example,
 * Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
 */
public class TrapRainWater {

    //Two pointers, Time O(n), Space O(1)
    public int trapBest(int[] height) {
        if (height == null || height.length <= 2) {
            return 0;
        }

        int left = 0, right = height.length - 1;
        int leftMax = height[0], rightMax = height[height.length - 1];

        int count = 0;
        while (left < right) {
            if (leftMax < rightMax) {
                left++;
                if (height[left] >= leftMax) {
                    leftMax = height[left];
                } else {
                    count += leftMax - height[left];
                }
            } else {
                right--;
                if (height[right] >= rightMax) {
                    rightMax = height[right];
                } else {
                    count += rightMax - height[right];
                }
            }
        }

        return count;
    }

    //DP, Time O(n), Space O(n)
    public int trap(int[] height) {
        if (height == null || height.length <= 2) {
            return 0;
        }

        int n = height.length;
        int[] left = new int[n];
        int[] right = new int[n];

        int max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, height[i]);
            left[i] = max;
        }

        max = 0;
        for (int i = n - 1; i >= 0; i--) {
            max = Math.max(max, height[i]);
            right[i] = max;
        }

        int sum = 0;

        for (int i = 0; i < n; i++) {
            sum += Math.min(left[i], right[i]) - height[i];
        }

        return sum;
    }

}
