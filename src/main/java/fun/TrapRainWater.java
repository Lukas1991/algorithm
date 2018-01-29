package fun;

import java.util.PriorityQueue;

/**
 * Given n non-negative integers representing an elevation map where the width of each bar is 1,
 * compute how much water it is able to trap after raining.
 * For example,
 * Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
 */
public class TrapRainWater {

    //Two pointers, Time O(n), Space O(1)
    public int trapBest(int[] height) {
        if (height == null || height.length <= 2) {
            return 0;
        }

        int left = 0, right = height.length - 1;
        int leftMax = height[0], rightMax = height[height.length - 1];

        int count = 0;
        while (left < right) {
            if (leftMax < rightMax) {
                left++;
                if (height[left] >= leftMax) {
                    leftMax = height[left];
                } else {
                    count += leftMax - height[left];
                }
            } else {
                right--;
                if (height[right] >= rightMax) {
                    rightMax = height[right];
                } else {
                    count += rightMax - height[right];
                }
            }
        }

        return count;
    }

    //----------------Trap Rain Water 2-----------------------------------
    /*
     * 四条外边放到min heap里，
     * 每次取min, 扫四向，如果not visited, 判断是否灌水，再加到heap里
     */
    public int trapRainWater(int[][] heights) {
        int rows = heights.length;
        int cols = heights[0].length;

        PriorityQueue<Cell> minHeap = new PriorityQueue<>((c1, c2) -> c1.h - c2.h);
        boolean[][] visited = new boolean[rows][cols];
        putBordersToHeap(heights, minHeap, visited);

        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        int res = 0;
        while (!minHeap.isEmpty()) {
            Cell cell = minHeap.poll();

            for (int k = 0; k < 4; k++) {
                int x = cell.i + dx[k];
                int y = cell.j + dy[k];
                if (inBound(x, y, rows, cols) && !visited[x][y]) {
                    if (heights[x][y] < cell.h) { //灌水
                        res += cell.h - heights[x][y];
                    }

                    visited[x][y] = true;
                    minHeap.offer(new Cell(x, y, Math.max(cell.h, heights[x][y]))); //取大的高度，放到heap里
                }
            }
        }

        return res;
    }

    void putBordersToHeap(int[][] heights, PriorityQueue<Cell> minHeap, boolean[][] visited) {
        int rows = heights.length;
        int cols = heights[0].length;
        //first and last row
        for (int j = 0; j < cols; j++) {
            minHeap.offer(new Cell(0, j, heights[0][j]));
            visited[0][j] = true;
            minHeap.offer(new Cell(rows - 1, j, heights[rows - 1][j]));
            visited[rows - 1][j] = true;
        }

        //first and last column
        for (int i = 0; i < rows; i++) {
            minHeap.offer(new Cell(i, 0, heights[i][0]));
            visited[i][0] = true;
            minHeap.offer(new Cell(i, cols - 1, heights[i][cols - 1]));
            visited[i][cols - 1] = true;
        }
    }

    boolean inBound(int x, int y, int n, int m) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }

    class Cell {
        int i;
        int j;
        int h;

        public Cell(int i, int j, int h) {
            this.i = i;
            this.j = j;
            this.h = h;
        }
    }

    //---------------------------------------------------
    //DP, Time O(n), Space O(n)
    public int trap(int[] height) {
        if (height == null || height.length <= 2) {
            return 0;
        }

        int n = height.length;
        int[] left = new int[n];
        int[] right = new int[n];

        int max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, height[i]);
            left[i] = max;
        }

        max = 0;
        for (int i = n - 1; i >= 0; i--) {
            max = Math.max(max, height[i]);
            right[i] = max;
        }

        int sum = 0;

        for (int i = 0; i < n; i++) {
            sum += Math.min(left[i], right[i]) - height[i];
        }

        return sum;
    }

}
