package Interview.dropbox;

import java.util.Arrays;

// Given a 2-d array of "sharpness" values. Find a path from the leftmost column to the rightmost column which has the highest minimum sharpness.
// Output the highest minimum sharpness. Each move can only move to the top right, right or bottom right grid.
// Example: 3*3 matrix
// 5 7 2
// 7 5 8
// 9 1 5
// The path with highest minimum sharpness is 7-->7-->8, because 7 is the highest minimum value in all the paths.
// Idea: Use DP dp[r][c] = min(max(dp[r-1][c-1], dp[r][c-1], dp[r+1][c-1]), grid[r][c])
// dp[i][j]记录之前所有(到达第(i,j)个点的路径中的最小值)里的最大值。

//从最左一列中的任意一个出发，到达最右一列的任意一个，要求：
//1）当前格子要想往右走，只能往右上、右边、右下三个格子移动；
//2）一条path中最小的那个值才是这条path的合格value；
//3）在所有path中找到合格value最大的那一条，输出它的value。
public class HighestMinSharpness {

    public int highestMinVal(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int rows = grid.length;
        int cols = grid[0].length;
        int[][] dp = new int[rows][cols]; // dp stores the highest min value of the path at current position

        //初始化0列, fill the col 0 of dp as the height
        for (int r = 0; r < rows; r++) {
            dp[r][0] = grid[r][0];
        }

        // Iterate from col 1, fill dp
        for (int c = 1; c < cols; c++) {
            for (int r = 0; r < rows; r++) {
                int max = 0;

                for (int dx = -1; dx <= 1; dx++) { //dx = {-1, 0, 1}
                    int x = r + dx;
                    if (x >= 0 && x < rows) {
                        max = Math.max(max, dp[x][c - 1]);
                    }
                }
//                for(int x = Math.max(0, r-1); x <= Math.min(rows-1, r+1); x++) {
//                    max = Math.max(max, dp[x][c-1]);
//                }

                // update the highest min value on current position
                dp[r][c] = Math.min(max, grid[r][c]); //注意是grid[r][c]
            }
        }

        for (int[] arr : dp) {
            System.err.println(Arrays.toString(arr));
        }

        // Find the highest min value of every path
        int res = 0;
        for (int r = 0; r < rows; r++) {
            res = Math.max(res, dp[r][cols - 1]);
        }

        return res;
    }

    //滚动数组
    public int highestMinVal2(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int rows = grid.length;
        int cols = grid[0].length;
        int[][] dp = new int[rows][2];//!!!

        //初始化0列, fill the col 0 of dp as the height
        for (int r = 0; r < rows; r++) {
            dp[r][0] = grid[r][0];
        }

        // Iterate from col 1, fill dp
        for (int c = 1; c < cols; c++) {
            for (int r = 0; r < rows; r++) {
                int max = 0;

                for (int dx = -1; dx <= 1; dx++) {
                    int x = r + dx;
                    if (x >= 0 && x < rows) {
                        max = Math.max(max, dp[x][(c - 1 + 2) % 2]); //!!!
                    }
                }

                dp[r][c % 2] = Math.min(max, grid[r][c]); //!!!
            }
        }

        for (int[] arr : dp) {
            System.err.println(Arrays.toString(arr));
        }

        int res = 0;
        for (int r = 0; r < rows; r++) {
            res = Math.max(res, dp[r][(cols - 1) % 2]);//!!!
        }

        return res;
    }

    //一个数组
    public int highestMinVal3(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int rows = grid.length;
        int cols = grid[0].length;

        int[] dp = new int[rows];//!!!

        //初始化0列, fill the col 0 of dp as the height
        for (int r = 0; r < rows; r++) {
            dp[r] = grid[r][0];//!!!
        }

        // Iterate from col 1, fill dp
        for (int c = 1; c < cols; c++) {
            int[] newdp = new int[rows];//!!!

            for (int r = 0; r < rows; r++) {
                int max = 0;

                for (int dx = -1; dx <= 1; dx++) {
                    int x = r + dx;
                    if (x >= 0 && x < rows) {
                        max = Math.max(max, dp[x]);//!!!
                    }
                }

                newdp[r] = Math.min(max, grid[r][c]);//!!!
            }
            dp = newdp;//!!!
        }

        // Find the highest min value of every path
        int res = 0;
        for (int r = 0; r < rows; r++) {
            res = Math.max(res, dp[r]);//!!!
        }

        return res;
    }


    public static void main(String[] args) {
        HighestMinSharpness obj = new HighestMinSharpness();

        int[][] grid = new int[][] {
            {5, 7, 2},
            {7, 5, 8},
            {9, 1, 5}
        };

        System.err.println(obj.highestMinVal(grid)); //7
        System.err.println(obj.highestMinVal2(grid)); //7
        System.err.println(obj.highestMinVal3(grid)); //7

        int[][] grid2 = new int[][] {
            {4, 5, 4, 0, 1, 2},
            {5, 6, 5, 0, 0, 0},
            {0, 0, 0, 0, 0, 0},
            {1, 3, 1, 2, 3, 3},
            {3, 0, 3, 0, 0, 0}
        };

        System.err.println(obj.highestMinVal(grid2));//2
        System.err.println(obj.highestMinVal2(grid2));//2
        System.err.println(obj.highestMinVal3(grid2)); //2
    }
//读三行，算中间哪行的dp, 方法是错的！

// Follow up:
//如果图片是1million*1million的怎么办，整张图片读不进内存，我答说dp结构可以改成一维的，然后每次只读一列。小哥说每次读一列的第一个字符非常耗时，因为要不断的跳读指针，
//然后我说可以对这个矩阵转置写到另外一个文件里，然后每次想做这个函数就对这个新文件操作就好了，这样就能按行读。
// 小哥就问我怎么转置再存到另外一个文件里，我说根据内存大小可以多读几列。
//然后小哥就说还可以再优化，他说这有一个balance，读行输出列，写文件就很耗时，读列输出行，读文件就很耗时（主要问题是 写指针或读指针跳转到下一行 所带来的时间消耗），
//结果是每次根据内存大小读一个接近正方形的矩形，将他们写到新文件，再读下一块矩形。这样的话，读写指针跳转次数就最小了。


}
