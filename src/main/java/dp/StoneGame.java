package dp;

public class StoneGame {

    /**
     * There is a stone game.At the beginning of the game the player picks n piles of stones in a line.
     * The goal is to merge the stones in one pile observing the following rules:
     * At each step of the game,the player can merge two adjacent piles to a new pile.
     * The score is the number of stones in the new pile.
     * You are to determine the minimum of the total score.
     *
     * dp[i][j] 表示把第i到第j个石子合并到一起的最小花费
     * dp[i][j] = min(dp[i][k]+dp[k+1][j]+sum[i,j]) 对于所有k属于{i,j}
     */
    public int stoneGame(int[] A) {
        int n = A.length;
        if (n <= 1) {
            return 0;
        }

        int[][] dp = new int[n][n];
        int[][] sum = new int[n][n];
        for (int i = 0; i < n; i++) {
            sum[i][i] = A[i];
            for (int j = i + 1; j < n; j++) {
                sum[i][j] = sum[i][j - 1] + A[j];
            }
        }

        for (int i = 0; i < n; i++) {
            dp[i][i] = 0;
        }

        for (int len = 2; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;

                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    int cost = dp[i][k] + dp[k + 1][j] + sum[i][j];

                    dp[i][j] = Math.min(dp[i][j], cost);

                }
            }
        }

        return dp[0][n - 1];
    }

    /**
     * There is a stone game.At the beginning of the game the player picks n piles of stones in a circle.
     * dp数组 2n * 2n
     */
    public int stoneGame2(int[] A) {
        int n = A.length;
        if (n <= 1) {
            return 0;
        }

        int[][] dp = new int[2 * n][2 * n];
        int[][] sum = new int[2 * n][2 * n];
        for (int i = 0; i < 2 * n; i++) {
            sum[i][i] = A[i % n];
            for (int j = i + 1; j < 2 * n; j++) {
                sum[i][j] = sum[i][j - 1] + A[j % n];
            }
        }

        for (int i = 0; i < 2 * n; i++) {
            dp[i][i] = 0;
        }

        for (int len = 2; len <= n; len++) {
            for (int i = 0; i + len - 1 < 2 * n; i++) {
                int j = i + len - 1;

                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    int cost = dp[i][k] + dp[k + 1][j] + sum[i][j];
                    dp[i][j] = Math.min(dp[i][j], cost);
                }
            }
        }


        int ans = Integer.MAX_VALUE;
        for (int start = 0; start < n; start++) {
            ans = Math.min(ans, dp[start][start + n - 1]);
        }
        return ans;
    }
}
