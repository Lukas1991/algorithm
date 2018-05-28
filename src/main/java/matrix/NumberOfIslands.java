package matrix;

import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 3 ways, Union Find, BFS, DFS
 */
public class NumberOfIslands {

    //-------------- Union Find ------------------------------
    int[] parent;
    int count;
    int[] dx = {1, 0}; //can only scan right and down
    int[] dy = {0, 1};

    public int numIslands(boolean[][] grid) {
        if (grid.length == 0) {
            return 0;
        }
        int n = grid.length;
        int m = grid[0].length;

        parent = new int[m * n];
        count = 0;
        for (int i = 0; i < m * n; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j]) {
                    count++;
                    int id1 = toId(i, j, m);

                    for (int k = 0; k < dx.length; k++) {
                        int x = i + dx[k];
                        int y = j + dy[k];
                        if (inBound(x, y, n, m) && grid[x][y]) {
                            int id2 = toId(x, y, m);
                            union(id1, id2);
                        }
                    }
                }
            }
        }

        return count;
    }

    void union(int a, int b) {
        int roota = find(a);
        int rootb = find(b);
        if (roota != rootb) {
            parent[roota] = rootb;
            count--;
        }
    }

    int find(int a) {
        if (parent[a] == a) {
            return a;
        } else {
            parent[a] = find(parent[a]);
            return parent[a];
        }
    }

    int toId(int i, int j, int m) {
        return i * m + j;
    }

    boolean inBound(int x, int y, int n, int m) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }

    //-------------- BFS ------------------------------
    public int numIslandsBFS(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int count = 0;
        boolean[][] visited = new boolean[grid.length][grid[0].length];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1' && !visited[i][j]) {
                    //dfs(grid, i, j, visited);
                    bfs(grid, i, j, visited);
                    count++;
                }
            }
        }

        return count;
    }

    void bfs(char[][] grid, int i, int j, boolean[][] visited) {
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};

        Queue<Point> queue = new LinkedList<>();

        Point start = new Point(i, j);
        queue.offer(start);
        visited[i][j] = true;

        while (!queue.isEmpty()) {
            Point p = queue.poll();

            for (int k = 0; k < 4; k++) {
                int x = dx[k] + p.x;
                int y = dy[k] + p.y;
                Point p1 = new Point(x, y);

                if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] == '1' && !visited[x][y]) {
                    queue.offer(p1);
                    visited[x][y] = true;
                }
            }
        }
    }

    //-------------- DFS (not recommended) ------------------

    /**
     * mark the given grid[][]
     */
    public int numIslands2(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int m = grid.length;
        int n = grid[0].length;
        int count = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    merge(grid, i, j);
                }
            }
        }
        return count;
    }

    private void merge(char[][] grid, int i, int j) {
        int m = grid.length;
        int n = grid[0].length;
        if (i < 0 || j < 0 || i >= m || j >= n || grid[i][j] != '1') {
            return;
        }
        grid[i][j] = 'X';

        merge(grid, i + 1, j);
        merge(grid, i - 1, j);
        merge(grid, i, j + 1);
        merge(grid, i, j - 1);
    }


    /**
     * 463. Island Perimeter
     * Solution 1: when we meet an island, perimeter + 4,
     * if the island has right neighbour, perimeter -2,
     * if the island has down neighbour, perimeter -2.
     *
     * Solution 2: perimeter of each island = (4 - neighbours) up,down,left,right
     */
    public int islandPerimeterBest(int[][] grid) {
        int perimeter = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    perimeter += 4;
                    if (i + 1 < grid.length && grid[i + 1][j] == 1) {
                        perimeter -= 2;// count down neighbours
                    }
                    if (j + 1 < grid[i].length && grid[i][j + 1] == 1) {
                        perimeter -= 2; // count right neighbours
                    }
                }
            }
        }

        return perimeter;
    }

    public int islandPerimeterSecond(int[][] grid) {
        int perimeter = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    int neighbors = getNeighbors(i, j, grid);
                    perimeter += (4 - neighbors);
                }
            }
        }
        return perimeter;
    }

    private int getNeighbors(int i, int j, int[][] grid) {
        int res = 0;
        //up
        if ((i - 1 >= 0) && grid[i - 1][j] == 1) {
            res++;
        }
        //down
        if ((i + 1 < grid.length) && grid[i + 1][j] == 1) {
            res++;
        }
        //left
        if ((j - 1 >= 0) && grid[i][j - 1] == 1) {
            res++;
        }

        //right
        if ((j + 1 < grid[0].length) && grid[i][j + 1] == 1) {
            res++;
        }

        return res;
    }
}
