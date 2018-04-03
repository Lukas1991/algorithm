package backtracking;

import java.util.ArrayList;
import java.util.List;

public class NQueen {

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        search(n, new ArrayList<>(), res);
        return res;
    }

    void search(int n, List<Integer> cols, List<List<String>> res) {
        if (cols.size() == n) {
            res.add(draw(cols));
        } else {
            for (int j = 0; j < n; j++) {
                if (isValid(cols, j)) {
                    cols.add(j);
                    search(n, cols, res);
                    cols.remove(cols.size() - 1);
                }
            }
        }
    }

    /**
     * (r,c)与（row, col）
     * 同一列: c = col
     * 正对角线: y = x + k, y - x = k, r - c == row - col
     * 反对角线: y = k - x, y + x = k, r + c == row + col
     *
     * 正反对角线: abs(x1 - x2) == abs(y1 - y2)
     */
    boolean isValid(List<Integer> cols, int col) {
        int row = cols.size();

        for (int r = 0; r < cols.size(); r++) {
            int c = cols.get(r);

            if (c == col) {
                return false;
            }

//            if (r + c == row + col) {
//                return false;
//            }
//
//            if (r - c == row - col) {
//                return false;
//            }

            if (Math.abs(r - row) == Math.abs(c - col)) {
                return false;
            }
        }

        return true;
    }

    List<String> draw(List<Integer> cols) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < cols.size(); i++) {
            int col = cols.get(i);
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < cols.size(); j++) {
                if (j == col) {
                    sb.append("Q");
                } else {
                    sb.append(".");
                }
            }
            list.add(sb.toString());
        }
        return list;
    }

    /**
     * Follow up for N-Queens problem.
     * Now, instead outputting board configurations, return the total number of distinct solutions.
     */
    int total = 0;

    public int totalNQueens(int n) {
        search2(n, new ArrayList<>());
        return total;
    }

    void search2(int n, List<Integer> cols) {
        if (cols.size() == n) {
            total++;
        } else {
            for (int j = 0; j < n; j++) {
                if (isValid(cols, j)) {
                    cols.add(j);
                    search2(n, cols);
                    cols.remove(cols.size() - 1);
                }
            }
        }
    }

    public static void main(String[] args) {
        NQueen obj = new NQueen();
        List<List<String>> res = obj.solveNQueens(4);
        System.out.println(res);
    }
}
