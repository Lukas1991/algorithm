package matrix;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CountCornerRectangle {

    //O(N*根号N), 是下面两种方法的结合
    public int countCornerRectangles(int[][] grid) {
        List<List<Integer>> rows = new ArrayList();
        int N = 0;
        for (int r = 0; r < grid.length; ++r) {
            rows.add(new ArrayList());
            for (int c = 0; c < grid[r].length; ++c) {
                if (grid[r][c] == 1) {
                    rows.get(r).add(c);
                    N++;
                }
            }
        }

        int sqrtN = (int) Math.sqrt(N);
        int ans = 0;
        Map<Integer, Integer> countMap = new HashMap();

        //根号N个heavy 行， O(根号N * N)
        //根号N个light 行， O(根号N * N)
        //总共O(根号N * N)
        for (int r = 0; r < grid.length; ++r) {
            if (rows.get(r).size() >= sqrtN) {
                //这一行heavy O(N)
                Set<Integer> target = new HashSet(rows.get(r));

                for (int r2 = 0; r2 < grid.length; ++r2) {
                    if (r2 <= r && rows.get(r2).size() >= sqrtN) {
                        continue;
                    }
                    //r2为 上面的light行 or r2是r1下面的行
                    int found = 0;
                    for (int c2 : rows.get(r2)) {
                        if (target.contains(c2)) {
                            found++;
                        }
                    }
                    ans += found * (found - 1) / 2;
                }
            } else {
                //这一行light, O(colSize * colSize)， 因为是light row, colSize < 根号N, O(根号N * 根号N) = O(N)
                //O(N)
                List<Integer> colList = rows.get(r);
                int colSize = colList.size();

                //这一行的每一对column i,j
                for (int colIndex1 = 0; colIndex1 < colSize; colIndex1++) {
                    int c1 = colList.get(colIndex1);

                    for (int colIndex2 = colIndex1 + 1; colIndex2 < colSize; colIndex2++) {
                        int c2 = colList.get(colIndex2);

                        int key = c1 * 200 + c2;
                        int previousCount = countMap.getOrDefault(key, 0);
                        ans += previousCount;
                        countMap.put(key, previousCount + 1);
                    }
                }
            }
        }
        return ans;
    }

    //O(rows * cols * cols)
    public int countCornerRectangles2(int[][] grid) {
        Map<Integer, Integer> countMap = new HashMap();
        int ans = 0;

        for (int r = 0; r < grid.length; ++r) {

            for (int c1 = 0; c1 < grid[0].length; c1++) {
                if (grid[r][c1] == 0) {
                    continue;
                }

                for (int c2 = c1 + 1; c2 < grid[0].length; c2++) {
                    if (grid[r][c2] == 0) {
                        continue;
                    }

                    int key = c1 * 200 + c2;
                    int previousCount = countMap.getOrDefault(key, 0);
                    ans += previousCount;
                    countMap.put(key, previousCount + 1);
                }
            }
        }
        return ans;
    }

    //O(rows * rows * cols)
    public int countCornerRectangles1(int[][] grid) {
        List<List<Integer>> rows = new ArrayList();
        int N = 0;
        for (int r = 0; r < grid.length; ++r) {
            rows.add(new ArrayList());
            for (int c = 0; c < grid[r].length; ++c) {
                if (grid[r][c] == 1) {
                    rows.get(r).add(c);
                    N++;
                }
            }
        }

        int sqrtN = (int) Math.sqrt(N);
        int ans = 0;
        Map<Integer, Integer> count = new HashMap();

        for (int r = 0; r < grid.length; ++r) {
            Set<Integer> target = new HashSet(rows.get(r));

            for (int r2 = r + 1; r2 < grid.length; ++r2) {
                int found = 0;

                for (int c2 : rows.get(r2)) {
                    if (target.contains(c2)) {
                        found++;
                    }
                }

                ans += found * (found - 1) / 2;
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        CountCornerRectangle obj = new CountCornerRectangle();

        int[][] grid = {
            {1, 1, 1},
            {1, 1, 1},
            {1, 1, 1}
        };
        int count = obj.countCornerRectangles1(grid);
        System.err.println(count);
    }
}
