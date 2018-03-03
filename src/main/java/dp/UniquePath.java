package dp;

import java.util.HashSet;
import java.util.Set;

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
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }

        return dp[m - 1][n - 1];
    }

    /**
     * Now consider if some obstacles are added to the grids. How many unique paths would there be?
     * An obstacle and empty space is marked as 1 and 0 respectively in the grid.
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int rows = obstacleGrid.length;
        if (rows == 0) return 0;
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

                    if (j >= 1) {
                        dp[i][j] += dp[i][j - 1];
                    }

                    if (i >= 1) {
                        dp[i][j] += dp[i - 1][j];
                    }

                }
            }
        }

        return dp[rows - 1][columns - 1];
    }

    /**
     * Now each grid contains a value, so each path also has a value. Find the sum of all the unique values paths.
     * [
     * [1,1,2],
     * [1,2,3],
     * [3,2,4]
     * ]
     * <p>
     * There are 2 unique value path:
     * [1,1,2,3,4] = 11
     * [1,1,2,2,4] = 10
     * <p>
     * return 21
     */
    public int uniqueWeightedPaths(int[][] grid) {
        int m = grid.length;
        if (m == 0) return 0;
        int n = grid[0].length;
        if (n == 0) return 0;

        Set<Integer>[][] dp = new HashSet[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = new HashSet();

                if (i == 0 && j == 0) {
                    dp[i][j].add(grid[0][0]);
                } else {
                    if (i >= 1) {
                        Set<Integer> set1 = dp[i - 1][j];
                        for (int a : set1) {
                            dp[i][j].add(a + grid[i][j]);
                        }
                    }

                    if (j >= 1) {
                        Set<Integer> set2 = dp[i][j - 1];
                        for (int a : set2) {
                            dp[i][j].add(a + grid[i][j]);
                        }
                    }
                }
            }
        }

        int total = 0;
        for (int a : dp[m - 1][n - 1]) {
            total += a;
        }
        return total;
    }
}
