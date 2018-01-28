package matrix;

import java.util.ArrayList;
import java.util.List;

/**
 * Union Find
 */
public class NumberOfIslandsII {

    int[] parent;
    int count;
    int[] dx = {1, -1, 0, 0};
    int[] dy = {0, 0, 1, -1};

    public List<Integer> numIslands2(int n, int m, int[][] positions) {
        List<Integer> res = new ArrayList<>();
        if (positions == null || positions.length == 0) {
            return res;
        }

        parent = new int[m * n];
        count = 0;
        for (int i = 0; i < m * n; i++) {
            parent[i] = i;
        }
        boolean[][] grid = new boolean[n][m];

        for (int[] p : positions) {
            int i = p[0];
            int j = p[1];

            if (!grid[i][j]) {
                grid[i][j] = true;
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

            res.add(count);
        }

        return res;
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
}
