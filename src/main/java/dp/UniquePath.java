package dp;

public class UniquePath {
	/**
	 * A robot is located at the top-left corner of a m x n grid. The robot can only move either down or right at any point in time. 
	 * The robot is trying to reach the bottom-right corner of the grid. How many possible unique paths are there?
	 */
	public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                }
            }
        }
        
        return dp[m-1][n-1];
    }

	/**
	 * Now consider if some obstacles are added to the grids. How many unique paths would there be?
	 * An obstacle and empty space is marked as 1 and 0 respectively in the grid.
	 */
	public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int rows = obstacleGrid.length;
        if (rows == 0)  return 0;
        int columns = obstacleGrid[0].length;
        int[][] dp = new int[rows][columns];
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                } else {
                    if (i == 0 && j == 0) {
                        dp[i][j] = 1;
                    }
                    
                    if (j>=1) {
                        dp[i][j] += dp[i][j-1];
                    }
                    
                    if (i>=1) {
                        dp[i][j] += dp[i-1][j];
                    }
                
                }
            }
        }
        
        return dp[rows-1][columns-1];
    }
}
