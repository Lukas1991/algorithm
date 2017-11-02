package matrix;

import java.util.Arrays;

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
     */
	public static int[][] multiply(int[][] A, int[][] B) {
		int n = A.length;
		int m = A[0].length;
		int p = B[0].length;

		int[][] C = new int[n][p];

		for (int i = 0; i < n; i++) {
			for (int k = 0; k < m; k++) {
				if (A[i][k] != 0) {

					for (int j = 0; j < p; j++) {
						if (B[k][j] != 0) {
							C[i][j] += A[i][k] * B[k][j];
						}
					}

				}
			}
		}

		return C;
	}

	public static int[][] multiply2(int[][] A, int[][] B) {
		int aColumns = A[0].length;
		int bRows = B.length;
		if (aColumns != bRows) {
			return null;
		}

		boolean[] aRowZero = new boolean[A.length];
		for (int i = 0; i < A.length; i++) {
			boolean hasNum = false;
			for (int j = 0; j < aColumns; j++) {
				if (A[i][j] != 0) {
					hasNum = true;
					break;
				}
			}
			aRowZero[i] = !hasNum;
		}

		int bColumns = B[0].length;
		boolean[] bColumnZero = new boolean[bColumns];
		for (int j = 0; j < bColumns; j++) {
			boolean hasNum = false;
			for (int i = 0; i < bRows; i++) {
				if (B[i][j] != 0) {
					hasNum = true;
					break;
				}
			}
			bColumnZero[j] = !hasNum;
		}

		int[][] res = new int[A.length][B[0].length];

		for (int i = 0; i < A.length; i++) { // row
			int[] rows = A[i];

			for (int j = 0; j < B[0].length; j++) { // column

				if (aRowZero[i] || bColumnZero[j]) {
					res[i][j] = 0;
				} else {
					int[] columns = new int[bRows];

					for (int k = 0; k < bRows; k++) {
						columns[k] = B[k][j];
					}

					res[i][j] = getMultiply(rows, columns);
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
		int[][] A = { { 1, 0, 0 }, { -1, 0, 3 } };
		// int[][] A = {
		// {1, -5}
		// };

		// int[][] B = {
		// {12},
		// {-1}
		// };

		int[][] B = { { 7, 0, 0 }, { 0, 0, 0 }, { 0, 0, 1 } };

		int[][] res = multiply(A, B);

		for (int[] row : res) {
			System.err.println(Arrays.toString(row));
		}
	}

}
