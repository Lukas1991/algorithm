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

    /**
     * Given a 2D binary matrix filled with 0's and 1's, find the largest square which diagonal is all 1 and others is 0.
     */
    public int maxSquare2(int[][] matrix) {
        int n = matrix.length;
        if (n == 0) {
            return 0;
        }

        int m = matrix[0].length;
        if (m == 0) {
            return 0;
        }

        int[][] dignalOnes = new int[n][m];  //以i, j作为正方形的右下角最长边长
        int[][] upZeros = new int[n][m]; //从i, j 往上数，有多少个连着的0
        int[][] leftZeros = new int[n][m];  //从i, j 往左数，有多少个连着的0

        int max = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (matrix[i][j] == 0) {
                    dignalOnes[i][j] = 0;
                    upZeros[i][j] = leftZeros[i][j] = 1;
                    if (i > 0) {
                        upZeros[i][j] = upZeros[i - 1][j] + 1;
                    }
                    if (j > 0) {
                        leftZeros[i][j] = leftZeros[i][j - 1] + 1;
                    }
                } else {
                    upZeros[i][j] = leftZeros[i][j] = 0;

                    if (i == 0 || j == 0) {
                        dignalOnes[i][j] = 1;
                    } else {
                        //min(斜着1的个数，上面0的个数，左边0的个数)
                        dignalOnes[i][j] = Math.min(dignalOnes[i - 1][j - 1], Math.min(upZeros[i - 1][j], leftZeros[i][j - 1])) + 1;
                    }
                }

                max = Math.max(max, dignalOnes[i][j]);
            }
        }

        return max * max;
    }
}
