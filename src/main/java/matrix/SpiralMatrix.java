package matrix;

import java.util.ArrayList;
import java.util.List;

/**
 * Spiral Matrix I and II
 */
public class SpiralMatrix {

    /**
     * 54. Spiral Matrix I
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        int m = matrix.length;
        if (m == 0) {
            return res;
        }
        int n = matrix[0].length;

        int rs = 0;
        int re = m - 1;
        int cs = 0;
        int ce = n - 1;

        while (rs <= re && cs <= ce) {
            for (int j = cs; j <= ce; j++) {
                res.add(matrix[rs][j]);
            }

            rs++;

            for (int i = rs; i <= re; i++) {
                res.add(matrix[i][ce]);
            }

            ce--;

            if (rs <= re) {
                for (int j = ce; j >= cs; j--) {
                    res.add(matrix[re][j]);
                }
            }
            re--;

            if (cs <= ce) {
                for (int i = re; i >= rs; i--) {
                    res.add(matrix[i][cs]);
                }
            }

            cs++;
        }

        return res;
    }

    /**
     * Spiral Matrix II
     */
    public int[][] generateMatrix(int n) {
        if (n < 0) {
            return null;
        }
        int[][] matrix = new int[n][n];
        int num = 1;

        int x = 0, y = 0;
        while (n > 1) {
            //from top left to right
            for (int i = 0; i < n; i++) {
                matrix[x][y + i] = num;
                num++;
            }
            //from top right to bottom right
            for (int j = 1; j < n; j++) {
                matrix[x + j][y + n - 1] = num;
                num++;
            }

            for (int i = n - 2; i >= 0; i--) {
                matrix[x + n - 1][y + i] = num;
                num++;
            }

            for (int j = n - 2; j > 0; j--) {
                matrix[x + j][y] = num;
                num++;
            }

            x++;
            y++;
            n = n - 2;

        }
        if (n == 1) {
            matrix[x][y] = num;
        }


        return matrix;

    }

    public static void main(String[] args) {
        SpiralMatrix obj = new SpiralMatrix();
        int[][] m2 = {{1, 2}, {3, 4}};
        int[][] m3 = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        List<Integer> res = obj.spiralOrder(m3);
        for (int i : res) {
            System.out.println(i);
        }
    }
}
