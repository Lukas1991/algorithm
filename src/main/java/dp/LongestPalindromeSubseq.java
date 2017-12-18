package dp;

/**
 * 516. Longest Palindromic Subsequence
 */
public class LongestPalindromeSubseq {

    /**
     * dp[i][j]: the longest palindromic subsequence's length of substring(i, j)
     * State transition:
     * dp[i][j] = dp[i+1][j-1] + 2 if s.charAt(i) == s.charAt(j)
     * otherwise, dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1])
     * Initialization: dp[i][i] = 1
     */
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];

        for (int i = n - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
                }
            }
        }
        return dp[0][n - 1];
    }
}
