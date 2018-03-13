package Interview.dropbox;

/**
 * 每个cell表示海拔。 只能从左向右走，水位从0开始，每走一步水位就上升1，水没过的path不能走了。
 * 找一个路径，让路径中的最高海拔 最小
 * 例如
 * {3,1,2,4},
 * {5,7,1,0},
 * {3,9,2,6}
 * 3-> 1-> 2-> 4是一条valid路径，最高海拔为4
 * 3-> 7-> 2-> 4是一条valid路径，最高海拔为7
 * 返回最小的 4
 */
public class MinHighestHeight {

    // dp[r][c] = max(min(dp[r-1][c-1], dp[r][c-1], dp[r+1][c-1]), grid[r][c])
    //return min(dp[r][cols - 1]);
    int minMaxHeight(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int rows = grid.length;
        int cols = grid[0].length;
        int[][] dp = new int[rows][cols]; // dp stores the min of highest value of the path at current position

        //first col, 高度. 不能到达的点设为max value
        for (int i = 0; i < rows; i++) {
            if (grid[i][0] >= 0) {
                dp[i][0] = grid[i][0];
            } else {
                dp[i][0] = Integer.MAX_VALUE;
            }
        }

        for (int c = 1; c < cols; c++) {
            for (int r = 0; r < rows; r++) {

                if (grid[r][c] < c) {
                    dp[r][c] = Integer.MAX_VALUE;
                    continue;
                }

                int min = Integer.MAX_VALUE;
                for (int dx = -1; dx <= 1; dx++) {
                    int x = r + dx;
                    if (x >= 0 && x < rows) { //可加可不加 && grid[x][c - 1] >= c - 1
                        min = Math.min(min, dp[x][c - 1]);
                    }
                }

                dp[r][c] = Math.max(min, grid[r][c]);
            }
        }

//        for (int[] arr : dp) {
//            System.err.println(Arrays.toString(arr));
//        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < rows; i++) {
            min = Math.min(min, dp[i][cols - 1]);
        }
        return min;
    }

    public static void main(String[] args) {
        MinHighestHeight obj = new MinHighestHeight();
        int[][] grid = {
                {3, 1, 2, 4},
                {5, 7, 1, 0},
                {3, 9, 2, 6}
        };
        System.err.println(obj.minMaxHeight(grid)); //4
    }
}
