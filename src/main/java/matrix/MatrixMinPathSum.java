package matrix;

/**
 * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right
 * which minimizes the sum of all numbers along its path.
 * Note: You can only move either down or right at any point in time.
 */
public class MatrixMinPathSum {

    //Dynamic Programming
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];

        //fill top row
        for (int i=1; i<n; i++) {
            dp[0][i] = dp[0][i-1] + grid[0][i];
        }

        //fill left column
        for (int j=1; j<m; j++) {
            dp[j][0] = dp[j-1][0] + grid[j][0];
        }

        for (int i=1; i<n; i++) {
            for (int j=1; j<m; j++) {
                //min(上,左)
                int min = Math.min(dp[j][i-1], dp[j-1][i]);
                dp[j][i] = grid[j][i] + min;
            }
        }

        return dp[m-1][n-1];
    }
}
