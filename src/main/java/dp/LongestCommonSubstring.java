package dp;

public class LongestCommonSubstring {

    /**
     * dp[i][j] is the length of the longest substring
     * ends with A[i - 1] & B[j - 1], A[0..i-1] & B[0..j-1]
     */
    public int longestCommonSubstring(String A, String B) {
        int n = A.length();
        int m = B.length();
        int dp[][] = new int[n + 1][m + 1];
        //first row, first col all 0

        int max = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {

                if (A.charAt(i - 1) == B.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = 0;
                }
                max = Math.max(max, dp[i][j]);
            }
        }
        return max;
    }
}
