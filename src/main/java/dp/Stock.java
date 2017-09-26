package dp;

public class Stock {
	
	/**
	 * 121. Best Time to Buy and Sell Stock 只允许买卖一次
	 * @param prices
	 * @return
	 */
	public int maxProfit(int[] prices) {
        if (prices.length < 2)  return 0;
        
        int maxProfit = 0;
        int min = prices[0];
        for (int i = 1; i<prices.length; i++) {
            maxProfit = Math.max(maxProfit, prices[i] - min);
            min = Math.min(min, prices[i]);
        }
        return maxProfit;
    }
	
	/**
	 * 122. Best Time to Buy and Sell Stock II, 多次买卖
	 * You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times).
	 * 
	 * 求sum of 各个递增序列的（最大-最小）, 相当于求只要当前是增的，就把增加的部分加起来。
	 */
	public int maxProfit2(int[] prices) {
        if (prices.length < 2)  return 0;
        int sum = 0;
        
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i-1]) {
                sum += prices[i] - prices[i-1];
            }
        }
       
        return sum;
    }

	/**
	 * 309. Best Time to Buy and Sell Stock with Cooldown
	 * 卖完再买，中间至少cool down一天
	 * 
	 * buy[i] i天买，或者i天之前最后一个动作是买 buy[i] = max( sell[i-2] - price, buy[i-1])  i-1天停，i-2天或以前必须有个卖， -price是因为买消耗了钱
	 * sell[i] i天卖，或者i天最后一个动作是卖， sell[i] = max( buy[i-1] + price, sell[i-1] ) buy[i-1] i-1天或以前必须有个买， +price是因为卖赚了钱
	 * 
	 * 只用到了三个多余变量sell[i-2], buy[i-1], sell[i-1]
	 * 
	 * 初始条件，第一天buy = -prices, sell = 0 不可能第一天就卖, 所以buypre = MIN能同事满足两个条件 
	 */
	public int maxProfitCoolDown(int[] prices) {
        int sellpre = 0, sellprepre = 0, buypre = Integer.MIN_VALUE;
        
        for (int i = 0; i < prices.length; i++) {
            int buy = Math.max(sellprepre - prices[i], buypre);
            int sell = Math.max(buypre + prices[i], sellpre);
            
            sellprepre = sellpre;
            sellpre = sell;
            
            buypre = buy;
        }
        
        return sellpre;
    }
	
	/**
	 * 123. Best Time to Buy and Sell Stock III,最多买卖两次
	 * buy[i] 第i天买，或i天之后买，后面在max时最后一次卖 buy[i] = max(max - price, buy[i+1]), 从后往前扫，更新max
	 * sell[i] 第i天卖，或i天之前卖过，前面在min时第一次买 sell[i] = max(price - min, sell[i-1]), 从前往后扫，更新min
	 * 
	 * 最后求每一天i，在i或i之前卖过，而且i或i之后第二次买。如果i天又卖又买，相当于总共只有一次交易
	 * max(sell[i] + buy[i])
	 */
	public int maxProfit3(int[] prices) {
        int n = prices.length;
        if (n < 2)  return 0;
        
        int[] buy = new int[n];
        int max = prices[n-1];
        buy[n-1] = 0;
        for (int i = n-2; i>=0; i--) {
            buy[i] = Math.max(buy[i+1], max - prices[i]);
            max = Math.max(max, prices[i]);
        }
        
        int[] sell = new int[n];
        int min = prices[0];
        sell[0] = 0;
        for (int i = 1; i < n; i++) {
            sell[i] = Math.max(sell[i-1], prices[i] - min);
            min = Math.min(min, prices[i]);
        }
        
        int maxProfit = 0;
        for (int i = 0; i < n; i++) {
            maxProfit = Math.max(maxProfit, sell[i] + buy[i]);
        }
        
        return maxProfit;
    }
	
	/**
	 * 188. Best Time to Buy and Sell Stock IV,最多买卖K次
	 * dp[i][j] j天或之前有i次交易的最大收益，is the max profit for up to i transactions by time j (0<=i<=K, 0<=j<=T).
	 * 
	 * j天可以hold, 卖，或买
	 * dp[i][j] = max of {hold -> dp[i][j-1], 卖-> price + localMax}  
	 * localMax = j天的买所消耗的，加上是前一天有过i-1次交易的收益， max of {localMax, 买 -> dp[i-1][j-1] - price}
	 * 
	 * dp[0, j] = 0; 0 transactions makes 0 profit
	 * dp[i, 0] = 0; if there is only one price data point you can't make any transaction.
	 */
	public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        if (n < 2)  return 0;
        if (n <= k * 2) return maxProfit2(prices);
        
        int[][] dp = new int[k+1][n];
        
        for (int i = 1; i <=k; i++) {
            int localMax = -prices[0];//0天买
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.max(dp[i][j-1], prices[j] + localMax);//j天hold，或者j天卖
                localMax = Math.max(localMax, dp[i-1][j-1] -prices[j]);//j天买，之前有i-1次交易
            }
        }
        
        return dp[k][n-1];
    }
    
	public static void main(String[] args) {
	}
}
