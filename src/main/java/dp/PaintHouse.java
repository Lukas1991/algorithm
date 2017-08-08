package dp;

public class PaintHouse {

    /**
     * There are a row of n houses, each house can be painted with one of the three colors: red, blue or green. The cost of painting
     * each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same
     * color.
     *
     * The cost of painting each house with a certain color is represented by a n x 3 cost matrix. For example, costs[0][0] is the
     * cost of painting house 0 with color red; costs[1][2] is the cost of painting house 1 with color green, and so on... Find the
     * minimum cost to paint all houses.
     */
    public int minCost(int[][] costs) {
        int rows = costs.length;
        if (rows == 0) {
            return 0;
        }

        int[][] dp = new int[rows][3];
        dp[0][0] = costs[0][0];
        dp[0][1] = costs[0][1];
        dp[0][2] = costs[0][2];

        for (int i = 1; i < rows; i++) {
            dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + costs[i][0];
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + costs[i][1];
            dp[i][2] = Math.min(dp[i - 1][1], dp[i - 1][0]) + costs[i][2];
        }

        int min = Math.min(dp[rows - 1][0], dp[rows - 1][1]);
        min = Math.min(min, dp[rows - 1][2]);
        return min;
    }

    /**
     * There are a row of n houses, each house can be painted with one of the k colors. The cost of painting each house with a
     * certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.
     *
     * The cost of painting each house with a certain color is represented by a n x k cost matrix. For example, costs[0][0] is the
     * cost of painting house 0 with color 0; costs[1][2] is the cost of painting house 1 with color 2, and so on... Find the
     * minimum cost to paint all houses.
     *
     * Could you solve it in O(nk) runtime?
     *
     * same as how to get previous house's min in O(1)
     * We can use min1 and min2 to track the indices of the smallest and 2nd smallest cost of the previous house,
     * if the current house's color index is same as min1, then we have to go with min2, otherwise we can safely go with min1.
     */
    public static int minCostII(int[][] costs) {
        int n = costs.length;
        if (n == 0) {
            return 0;
        }
        int k = costs[0].length;

        int[][] dp = new int[n][k];

        int currMin1Index = -1;//index of the 1st-smallest cost
        int currMin2Index = -1;//index of the 2nd-smallest cost

        for (int i = 0; i < n; i++) {
            int preMin1Index = currMin1Index;
            int preMin2Index = currMin2Index;

            currMin1Index = -1;
            currMin2Index = -1;

            for (int j = 0; j < k; j++) {
                if (i == 0) {
                    dp[0][j] = costs[0][j];
                } else {

                    if (j == preMin1Index) {
                        dp[i][j] = dp[i - 1][preMin2Index] + costs[i][j];
                    } else {
                        dp[i][j] = dp[i - 1][preMin1Index] + costs[i][j];
                    }
                }

                //re-calculate min1, min2
                if (currMin1Index < 0 || dp[i][j] <= dp[i][currMin1Index]) {
                    currMin2Index = currMin1Index;
                    currMin1Index = j;
                } else if (currMin2Index < 0 || dp[i][j] < dp[i][currMin2Index]) {
                    currMin2Index = j;
                }
            }
        }

        return dp[n - 1][currMin1Index];
    }

    public static void main(String[] args) {
        int[][] costs = {
            {1, 5, 3},
            {2, 9, 4}
        };
        System.err.println(minCostII(costs));
    }
}
