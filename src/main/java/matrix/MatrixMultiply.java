package matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MatrixMultiply {

	/**
	 * Matrix A * Matrix B 
	 * C(i, j) = A row i * B column j 
	 * so (# of elements in A'row) -> A列数 and（# of elements in B's column）B行数 should be the same 
	 * C has i rows and j columns, # of A rows, # of B columns
	 */
	/**
	 * If A is an n × m matrix and B is an m × p matrix, the matrix product C is defined to be the n × p matrix.
	 * Cij = sum of Aik * Bkj, k from 1 to m
     * A的i行 * B的j列
     */
    public int[][] multiplyNaive(int[][] A, int[][] B) {
        int rows = A.length;
        int cols = B[0].length;

        int t = A[0].length; // should be the same as B.length

        int[][] C = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {

                for (int k = 0; k < t; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }

        return C;
    }

    //优化1
    //第二，三个for 换位置。 一维优化，skip A[i][k] == 0
    public int[][] multiply(int[][] A, int[][] B) {
        int rows = A.length;
        int cols = B[0].length;

        int t = A[0].length; // should be the same as B.length

        int[][] C = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int k = 0; k < t; k++) {
                if (A[i][k] == 0) {
                    continue;
                }

                for (int j = 0; j < cols; j++) {
                    C[i][j] += A[i][k] * B[k][j];
				}
			}
		}

		return C;
	}

    //继续优化，优化2
    //二维维优化，skip A[i][k] == 0 && B[k][j] == 0
    //保存B中非0的位, 所以也skip B[k][j] == 0
    public int[][] multiply2(int[][] A, int[][] B) {
        int rows = A.length;
        int cols = B[0].length;
        int t = A[0].length; //=B.length

        int[][] res = new int[rows][cols];

        //make a copy of B's not zero elements's index
        List<List<Integer>> bList = new ArrayList<>();
        for (int i = 0; i < B.length; i++) {
            bList.add(new ArrayList());
            for (int j = 0; j < B[0].length; j++) {
                if (B[i][j] != 0) {
                    bList.get(i).add(j);
                }
            }
		}

        for (int i = 0; i < rows; i++) {
            for (int k = 0; k < t; k++) {
                if (A[i][k] == 0) {
                    continue;
                }

                List<Integer> bRow = bList.get(k);
                for (int x = 0; x < bRow.size(); x++) {
                    int j = bRow.get(x);
                    res[i][j] += A[i][k] * B[k][j];
                }
            }
        }

        return res;
    }

    //空间上优化，新开两个一维数组。貌似不是最优解。。。
    public static int[][] multiply3(int[][] A, int[][] B) {
        int rows = A.length;
        int cols = B[0].length;

        int t = A[0].length; //=B.length

        boolean[] aRowZero = new boolean[rows];
        for (int i = 0; i < rows; i++) {
			boolean hasNum = false;
            for (int j = 0; j < A[0].length; j++) {
				if (A[i][j] != 0) {
					hasNum = true;
					break;
				}
			}
			aRowZero[i] = !hasNum;
		}

        boolean[] bColumnZero = new boolean[cols];
        for (int j = 0; j < cols; j++) {
			boolean hasNum = false;
            for (int i = 0; i < B.length; i++) {
				if (B[i][j] != 0) {
					hasNum = true;
					break;
				}
			}
			bColumnZero[j] = !hasNum;
		}

		int[][] res = new int[A.length][B[0].length];

        for (int i = 0; i < rows; i++) { //row
            for (int j = 0; j < cols; j++) { // column

				if (aRowZero[i] || bColumnZero[j]) {
                    continue;
                }

                for (int k = 0; k < t; k++) {
                    res[i][j] += A[i][k] * B[k][j];
				}
			}
		}

		return res;
	}

	private static int getMultiply(int[] rows, int[] columns) {
		int sum = 0;
		for (int i = 0; i < rows.length; i++) {
			sum += rows[i] * columns[i];
		}
		return sum;
	}

	public static void main(String[] args) {
        MatrixMultiply obj = new MatrixMultiply();

		int[][] A = { { 1, 0, 0 }, { -1, 0, 3 } };
		// int[][] A = {
		// {1, -5}
		// };

		// int[][] B = {
		// {12},
		// {-1}
		// };

		int[][] B = { { 7, 0, 0 }, { 0, 0, 0 }, { 0, 0, 1 } };

        int[][] res = obj.multiply(A, B);

		for (int[] row : res) {
			System.err.println(Arrays.toString(row));
		}
	}

}
