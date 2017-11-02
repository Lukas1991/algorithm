package dp;

public class BurstBalloons {


    /**
     * dp[start][end] 表示区间(start, end), start和end没爆, 中间全爆了之后的maxCoins
     * 原数组前后各加一个气球, size n = 原长度+2, 返回dp[0][n-1], 前后加的气球不用爆炸, 中间的气球(原长度个)全爆了
     */
    public static int maxCoins(int[] nums) {
        int n = nums.length + 2;
        int[] arr = new int[n];
        arr[0] = 1;
        arr[n-1] = 1;
        for (int i = 0; i < nums.length; i++) {
            arr[i+1] = nums[i];
        }

        int[][] dp = new int[n][n];

        //k表示区间大小 (0,k)
        //nums size 至少是1,那么最小区间(0,1,2)区间,start=0, end=2, 1是最后一个爆的, k=2-0=2
        //最大区间(0, ..., n-1), k=n-1, k<n
        for(int k = 2; k < n; k++) {

            for (int start = 0; start + k < n; start++ ) {
                int end = start + k;

                for(int i = start + 1; i < end; i++) {
                    //i是(start, end)中最后一个爆的. start和end没爆, i从start+1, 到end-1
                    dp[start][end] = Math.max(
                        dp[start][end],
                        //爆i, i相连的没爆的是start和end, 区间(start, i) 里面的都爆了, 区间(i, end)里面的也全爆了
                        dp[start][i] + dp[i][end] + arr[i] * arr[start] * arr[end]
                    );
                }
            }
        }

        return dp[0][n-1];
    }

    public static void main(String[] args) {
        int max = maxCoins(new int[]{3,1,5,8});
        System.err.println(max);
    }
}
