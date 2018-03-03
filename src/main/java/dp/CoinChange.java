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

    /**
     * Coin Change 2, 多少种取法
     * You are given coins of different denominations and a total amount of money.
     * Write a function to compute the number of combinations that make up that amount.
     * You may assume that you have infinite number of each kind of coin.
     */
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;

        //注意内外层顺序
        for (int j = 0; j < coins.length; j++) {
            for (int i = coins[j]; i <= amount; i++) {
                if (i - coins[j] >= 0) {
                    dp[i] += dp[i - coins[j]];
                }
            }
        }

        return dp[amount];
    }
}
