package dp;

import java.util.Arrays;

public class CoinsInALine {

    public boolean firstWillWin(int[] values) {
        int n = values.length;
        if (n <= 2) return true;

        int[] sum = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            sum[i] = sum[i - 1] + values[n - i]; //左边剩余i个硬币的和
        }

        System.err.println(Arrays.toString(sum));

        int[] dp = new int[n + 1];
        dp[1] = values[n - 1];//还剩1个硬币
        for (int i = 2; i <= n; ++i) {
            //dp[i]总共有左边i个，我怎么取使得最后取最多
            //我取1个，对手从左边剩下i-1个中取,到最后对手最多有dp[i-1]
            //我取2个，对手从左边剩下i-2个中取,到最后对手最多有dp[i-2]
            dp[i] = sum[i] - Math.min(dp[i - 1], dp[i - 2]);
        }

        System.err.println(Arrays.toString(dp));
        return dp[n] > sum[n] / 2;
    }

    /**
     * There are n coins in a line. Two players take turns to take a coin from one of the ends of the line until there are no more coins left.
     * The player with the larger amount of money wins. Could you please decide the first player will win or lose?
     * dp[i][j] 先手从区间i,j 取，一直到区间都取完，先手获得最多硬币数
     */
    public boolean firstWillWin3(int[] values) {
        int n = values.length;
        if (n == 0) {
            return false;
        }

        int[] sum = new int[n];
        sum[0] = values[0];
        for (int i = 1; i < n; i++) {
            sum[i] = sum[i - 1] + values[i];//从0到i的sum
        }

        int[][] dp = new int[n][n];
        //初始化，区间只有一个，那就取
        for (int i = 0; i < n; i++) {
            dp[i][i] = values[i];
        }

        for (int len = 2; len <= n; len++) {
            for (int i = 0; i < n; i++) {
                int j = i + len - 1;
                if (j >= n) {
                    continue;
                }
                //从i到j的sum
                int sumij = 0;
                if (i - 1 >= 0) {
                    sumij = sum[j] - sum[i - 1];
                } else {
                    sumij = sum[j];
                }
                //先手取i, 对手从剩下的[i+1, j]区间取完获得的硬币为dp[i+1][j]，先手就获得sumij-dp[i+1][j]的硬币
                dp[i][j] = Math.max(sumij - dp[i + 1][j], sumij - dp[i][j - 1]);
            }
        }

        return dp[0][n - 1] > sum[n - 1] / 2;
    }

    public static void main(String[] args) {
        CoinsInALine obj = new CoinsInALine();
        int[] values = {1, 2, 4, 3, 7, 3};
        obj.firstWillWin(values);
    }
}
