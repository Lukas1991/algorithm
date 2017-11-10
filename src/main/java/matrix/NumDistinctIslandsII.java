package matrix;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NumDistinctIslandsII {
    int[][] grid;
    boolean[][] seen;

    public void explore(int r, int c, List<Point> shape) {
        if (0 <= r && r < grid.length && 0 <= c && c < grid[0].length && grid[r][c] == 1 && !seen[r][c]) {
            seen[r][c] = true;

            shape.add(new Point(r, c));

            explore(r+1, c, shape);
            explore(r-1, c, shape);
            explore(r, c+1, shape);
            explore(r, c-1, shape);
        }
    }

    public String canonical(List<Point> shape) {
        //list of points to string, return the largest string
        String ans = "";

        System.err.println("Shape: " + shape.toString());

        for (int c = 0; c < 8; c++) {
            int minx = Integer.MAX_VALUE;
            int miny = Integer.MAX_VALUE;

            List<Point> points = new ArrayList<>();

            for (int t = 0; t < shape.size(); t++) {
                Point p = shape.get(t);

                Point transformed = transform(p, c);

                minx = Math.min(minx, transformed.x);
                miny = Math.min(miny, transformed.y);

                points.add(transformed);
            }

            for (Point p : points) {
                p.x = p.x - minx;
                p.y = p.y - miny;
            }

            //sort by x, then by y
            points.sort((p1, p2) -> {
                if (p1.x == p2.x) {
                    return p1.y - p2.y;
                } else {
                    return p1.x - p2.x;
                }
            });

            String candidate = points.toString();

            //get largest string
            if (ans.compareTo(candidate) < 0) ans = candidate;
        }

        System.err.println("------ ans: " + ans);
        return ans;
    }

    public int numDistinctIslands2(int[][] grid) {
        this.grid = grid;
        seen = new boolean[grid.length][grid[0].length];
        Set shapes = new HashSet<String>();

        for (int r = 0; r < grid.length; ++r) {
            for (int c = 0; c < grid[0].length; ++c) {
                List<Point> shape = new ArrayList();
                explore(r, c, shape);

                if (!shape.isEmpty()) {
                    shapes.add(canonical(shape));
                }
            }
        }

        return shapes.size();
    }

    //x y, x -y, -x y, -x -y
    //y x, y -x, -y x, -y -x
    private Point transform(Point p, int i) {
        int x = p.x;
        int y = p.y;

        switch (i) {
            case 0:
                return p;
            case 1:
                return new Point(x, -y);
            case 2:
                return new Point(-x, y);
            case 3:
                return new Point(-x, -y);

            case 4:
                return new Point(y, x);
            case 5:
                return new Point(y, -x);
            case 6:
                return new Point(-y, x);
            case 7:
                return new Point(-y, -x);
            default:
                return null;
        }
    }

    class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "(" + x + "," + y + ")";
        }
    }

    public static void main(String[] args) {
        NumDistinctIslandsII obj = new NumDistinctIslandsII();

        int[][] grid = {
            {1,1,1,0,0},
            {1,0,0,0,1},
            {0,1,0,0,1},
            {0,1,1,1,0}
        };

        obj.numDistinctIslands2(grid);
    }
}
