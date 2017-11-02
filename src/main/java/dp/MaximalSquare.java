package dp;

public class MaximalSquare {

    /**
     * 221. Maximal Square
     * dp[i][j]:以(i,j)为右下角的最大正方形的边长
     * dp[i][j] = min{dp[i-1][j], dp[i-1][j-1], dp[i][j-1]} + 1, 其他三个角的min+1
     *
     * 最大面积=max边长*max边长
     */
    public int maximalSquare(char[][] matrix) {
        int rows = matrix.length;
        if (rows == 0)  return 0;
        int columns = matrix[0].length;

        int[][] dp = new int[rows][columns];
        int max = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (matrix[i][j] == '1') {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        int min = Math.min(dp[i-1][j], dp[i-1][j-1]);
                        min = Math.min(min, dp[i][j-1]);
                        dp[i][j] = min + 1;
                    }
                }
                max = Math.max(max, dp[i][j]);
            }
        }

        return max * max;
    }
}
