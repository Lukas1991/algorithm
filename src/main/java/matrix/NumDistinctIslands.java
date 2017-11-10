package matrix;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NumDistinctIslands {
    int[][] grid;
    boolean[][] seen;

    public void explore(int r, int c, int r0, int c0, List<String> shape) {
        if (0 <= r && r < grid.length && 0 <= c && c < grid[0].length && grid[r][c] == 1 && !seen[r][c]) {
            seen[r][c] = true;

            shape.add(toString(r - r0, c - c0));

            explore(r+1, c, r0, c0, shape);
            explore(r-1, c, r0, c0, shape);
            explore(r, c+1, r0, c0, shape);
            explore(r, c-1, r0, c0, shape);
        }
    }

    public int numDistinctIslands(int[][] grid) {
        this.grid = grid;
        seen = new boolean[grid.length][grid[0].length];
        Set shapes = new HashSet<String>();

        for (int r = 0; r < grid.length; ++r) {
            for (int c = 0; c < grid[0].length; ++c) {
                List<String> shape = new ArrayList();
                explore(r, c, r, c, shape);

                if (!shape.isEmpty()) {
                    shapes.add(shape);
                }
            }
        }

        return shapes.size();
    }

    private String toString(int x, int y) {
        return "(" + x + "," + y + ")";
    }
}
