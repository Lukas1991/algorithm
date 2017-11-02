package fun;

/**
 * Given n non-negative integers representing an elevation map where the width of each bar is 1,
 * compute how much water it is able to trap after raining.
 * For example,
 * Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
 */
public class TrapRainWater {

    public int trap(int[] height) {
        if (height == null || height.length <= 2) {
            return 0;
        }
        int size = height.length;

        int[] left = new int[size];
        int[] right = new int[size];

        int max = height[0];
        left[0] = height[0];
        for(int i=1; i<size; i++) {
            if (height[i] >= max) {
                left[i] = height[i];
                max = height[i];
            } else {
                left[i] = max;
            }
        }

        max = height[size-1];
        right[size-1] = height[size-1];
        for(int i= size-2; i>=0; i--) {
            if (height[i] >= max) {
                right[i] = height[i];
                max = height[i];
            } else {
                right[i] = max;
            }
        }

        int total = 0;
        for(int i=0; i<size-1; i++) {
            total += Math.min(left[i], right[i]) - height[i];
        }

        return total;
    }

    public int trapRainWater(int[][] heightMap) {
        if(heightMap == null || heightMap.length <= 2 || heightMap[0].length <= 2) {
            return 0;
        }
        int m = heightMap[0].length; //6
        int n = heightMap.length; //3

        int[][] left = new int[n][m];
        int[][] right = new int[n][m];
        int[][] up = new int[n][m];
        int[][] down = new int[n][m];

        //foreach row, scan from left to right
        for (int i=0; i<n; i++) {
            left[i][0] = heightMap[i][0];
            int max = heightMap[i][0];
            for (int j=1; j<m; j++) {
                if (heightMap[i][j] >= max) {
                    left[i][j] = heightMap[i][j];
                    max = heightMap[i][j];
                }else {
                    left[i][j] = max;
                }
            }
        }

        //foreach row, scan from right to left
        for (int i=0; i<n; i++) {
            right[i][m-1] = heightMap[i][m-1];
            int max = heightMap[i][m-1];
            for (int j=m-2; j>=0; j--) {
                if (heightMap[i][j] >= max) {
                    right[i][j] = heightMap[i][j];
                    max = heightMap[i][j];
                } else {
                    right[i][j] = max;
                }
            }
        }

        //foreach column, scan from up to down
        for (int j=0; j<m; j++) {
            up[0][j] = heightMap[0][j];
            int max = heightMap[0][j];
            for (int i=1; i<n; i++) {
                if (heightMap[i][j] > max) {
                    up[i][j] = heightMap[i][j];
                    max = heightMap[i][j];
                } else {
                    up[i][j] = max;
                }
            }
        }

        //foreach column, scan from down to up
        for (int j=0; j<m; j++) {
            down[n-1][j] = heightMap[n-1][j];
            int max = heightMap[n-1][j];
            for (int i=n-2; i>=0; i--) {
                if (heightMap[i][j] > max) {
                    down[i][j] = heightMap[i][j];
                    max = heightMap[i][j];
                } else {
                    down[i][j] = max;
                }
            }
        }

        int total = 0;
        for (int i=1; i<n-1; i++) {
            for (int j=1; j<m-1; j++) {
                if (findMin(left[i][j], right[i][j], up[i][j], down[i][j]) > heightMap[i][j]) {
                    System.err.println("findMin[i][j]" + findMin(left[i][j], right[i][j], up[i][j], down[i][j]));
                    System.err.println("heightMap[i][j]" + heightMap[i][j]);
                    total += findMin(left[i][j], right[i][j], up[i][j], down[i][j]) - heightMap[i][j];
                }
            }
        }
        return total;
    }

    private int findMin(int a, int b, int c, int d) {
        int min1 = Math.min(a, b);
        int min2 = Math.min(c, d);
        return Math.min(min1, min2);
    }

    public static void main(String[] args) {
        TrapRainWater trapRainWater = new TrapRainWater();
//        int[][] heightMap = {
//                {1,4,3,1,3,2},
//                {3,2,1,3,2,4},
//                {2,3,3,2,3,1}
//        };
        int[][] heightMap = {
                {12,13,1,12},
                {13,4,13,12},
                {13,8,10,12},
                {12,13,12,12},
                {13,13,13,13}
        };

        int result = trapRainWater.trapRainWater(heightMap);
        System.out.println(result);
       // System.out.println(heightMap[0][3]);
    }
}
