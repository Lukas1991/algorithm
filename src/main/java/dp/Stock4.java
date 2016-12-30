package dp;

public class Stock4 {
	/**
	 * dp[i][j] is the max profit for up to i transactions by time j (0<=i<=K, 0<=j<=T).
	 * 
	 * dp[i][j] can be either dp[i][j-1]: not sell on day j, already have i transactions before day j
	 * or have i-1 transactions before, and sell on day j.
	 * price[j] - price[jj] + dp[i-1][jj], assume jj is the previous buy action day
	 * 
	 * dp[i, j] = max(dp[i, j-1], prices[j] - prices[jj] + dp[i-1, jj]) { jj in range of [0, j-1] }
	 *          = max(dp[i, j-1], prices[j] + max(dp[i-1, jj] - prices[jj]))
	 * 
	 * so for each i, we keep a localMax for dp[i-1, j] - prices[j]
	 * 
	 * dp[0, j] = 0; 0 transactions makes 0 profit
	 * dp[i, 0] = 0; if there is only one price data point you can't make any transaction.
	 * 
	 */
	
	public int maxProfit(int k, int[] prices) {
        int len = prices.length;
        if (len <=1) return 0;
        if (k >= len/2) return quickSolve(prices);
        
        int[][] dp = new int[k+1][len];
        for (int i=1; i<=k; i++) {
            int localMax = dp[i-1][0] - prices[0];
            for (int j=1; j<len; j++) {
                dp[i][j] = Math.max(dp[i][j-1], prices[j] + localMax);
                localMax = Math.max(dp[i-1][j] - prices[j], localMax);
            }
        }
        
        return dp[k][len-1];
    }
    
    private int quickSolve(int[] prices) {
        int p = 0;
        for (int i=1; i<prices.length; i++) {
            if (prices[i] > prices[i-1]) {
                p+= prices[i] - prices[i-1];
            }
        }
        return p;
    }
}
