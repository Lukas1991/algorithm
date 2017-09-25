package dp;

public class IntegerBreak {

    /**
     * Given a positive integer n, break it into the sum of at least two positive integers and maximize the product of those integers.
     * Return the maximum product you can get.
     * For example, given n = 2, return 1 (2 = 1 + 1); given n = 10, return 36 (10 = 3 + 3 + 4).
     *
     * Note: You may assume that n is not less than 2 and not larger than 58.
     *
     * dp[i] = max product after break the number i OR i itself
     * dp[10] = max of dp[1] * dp[9], dp[2] * dp[8], dp[3] * dp[7], dp[4] * dp[6], dp[5] * dp[5]. if n != 10, also compare max with 10
     */
    public int integerBreak(int n) {
        int[] dp = new int[n+1];
        dp[1] = 1;

        for (int i = 2; i <=n; i++) {
            int start = 1;
            int end = i - 1;
            int max = 0;
            while (start <= end) {
                max = Math.max(dp[start] * dp[end], max);
                start++;
                end--;
            }

            if (i < n) {
                dp[i] = Math.max(i, max);
            } else {
                dp[i] = max;
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        IntegerBreak obj = new IntegerBreak();
        System.err.println(obj.integerBreak(2));
        System.err.println(obj.integerBreak(4));
        System.err.println(obj.integerBreak(10));
    }
}
