package dp;

public class CoinChange {

    /**
     * 322. Coin Change.
     * You are given coins of different denominations and a total amount of money amount.
     * Write a function to compute the fewest number of coins that you need to make up that amount.
     * If that amount of money cannot be made up by any combination of the coins, return -1.
     */
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < coins.length; j++) {
                if (i - coins[j] >= 0) {
                    min = Math.min(min, dp[i - coins[j]]);
                }
            }
            if (min != Integer.MAX_VALUE) {
                dp[i] = min + 1;
            } else {
                dp[i] = Integer.MAX_VALUE;
            }
        }

        if (dp[amount] == Integer.MAX_VALUE) {
            return -1;
        } else {
            return dp[amount];
        }
    }
}
