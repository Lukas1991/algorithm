package dp;

public class LongestCommonSubsequence {

    /**
     * dp[i][j]表示前i个字符配上前j个字符的LCS的长度
     */
    public int longestCommonSubsequence(String A, String B) {
        int n = A.length();
        int m = B.length();
        int dp[][] = new int[n + 1][m + 1];
        //first row, first col all 0

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {

                if (A.charAt(i - 1) == B.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }

            }
        }
        return dp[n][m];
    }
}
