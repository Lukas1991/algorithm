package dp;

import java.util.Arrays;

public class PostOffice {

    int[][] calMidDistance(int[] A) {
        int n = A.length;
        int[][] dis = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = i + 1; j <= n; ++j) {
                int mid = (i + j) / 2;

                int total = 0;
                //dis = [i, j] 之间每点到mid的距离之和
                for (int k = i; k <= j; ++k) {
                    total += Math.abs(A[k - 1] - A[mid - 1]);
                }

                dis[i][j] = total;
            }
        }

        for (int[] arr : dis) {
            System.err.println(Arrays.toString(arr));
        }

        return dis;
    }

    /**
     * dp[i][k] = 前i个房子，有k个邮局，的最小距离
     * = dp[j][nk - 1] + dis[j + 1][i] 前j个房子有nk-1个邮局，从j+1到i有一个邮局
     * 只有一个邮局时，邮局放在中点，用dis[i][j]存i, j之间每点到mid的距离之和
     */
    public int postOffice(int[] A, int k) {
        int n = A.length;
        if (n == 0 || k >= A.length) {
            return 0;
        }

        Arrays.sort(A);

        int[][] dis = calMidDistance(A);
        int[][] dp = new int[n + 1][k + 1];

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i <= n; ++i) {
            //前i个房子只有一个邮局，邮局就选在中点，最小距离为dis[1][i] 从第一个房子到第i个房子
            dp[i][1] = dis[1][i];
        }

        for (int nk = 2; nk <= k; nk++) {
            //房子个数得>=邮局个数
            for (int i = nk; i <= n; i++) {
                dp[i][nk] = Integer.MAX_VALUE;

                for (int j = 0; j < i; j++) {
                    int tmp = dp[j][nk - 1] + dis[j + 1][i];
                    dp[i][nk] = Math.min(dp[i][nk], tmp);
                }
            }
        }
        return dp[n][k];
    }

    public static void main(String[] args) {
        PostOffice obj = new PostOffice();
        int[] A = {1, 2, 3, 4, 5};
        System.err.println(obj.postOffice(A, 2));
    }
}
