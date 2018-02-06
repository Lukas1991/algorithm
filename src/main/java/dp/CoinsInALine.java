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

    public static void main(String[] args) {
        CoinsInALine obj = new CoinsInALine();
        int[] values = {1, 2, 4, 3, 7, 3};
        obj.firstWillWin(values);
    }
}
