package dp;

public class CountNumbersWithUniqueDigits {

    /**
     * 357. Count Numbers with Unique Digits
     * dp[i] 表示位数为i的unique个数,
     * dp[1] 9个一位数
     * dp[2] = 9 * 9, 两位数, 最高位不能为0, [1-9] 9个选择, 第二位[0-9] 10-1 = 9个选择
     * dp[3] = 9 * 9 * 8
     * dp[4] = 9 * 9 * 8 * 7
     *
     * 最后求dp数组的sum
     */
    public int countNumbersWithUniqueDigits(int n) {
        if (n == 0) return 1;
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 9;

        int countDigitsUsed = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i-1] * (10 - countDigitsUsed);
            countDigitsUsed++;
        }

        int sum = 0;
        for (int a : dp) {
            sum += a;
        }

        return sum;
    }

    /**
     * 改进版,不需要dp数组,只需要记录pre结果
     */
    public int countNumbersWithUniqueDigitsImproved(int n) {
        if (n == 0) return 1;

        int pre = 9;
        int sum = 10;

        int countDigitsUsed = 1;
        for (int i = 2; i <= n; i++) {
            int curr = pre * (10 - countDigitsUsed);
            countDigitsUsed++;
            sum += curr;
            pre = curr;
        }

        return sum;
    }
}


