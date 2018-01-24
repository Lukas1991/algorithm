package matrix;

public class SearchMatrix {
	/**
	 * 1. Integers in each row are sorted from left to right.
	 * 2. The first integer of each row is greater than the last integer of the previous row.
	 * [[1,   3,  5,  7],
	 * [10, 11, 16, 20],
	 * [23, 30, 34, 50]]
	 *
	 * Binary Search - O(log(mn)) = O(logm + logn)
     */
	public boolean searchMatrix(int[][] matrix, int target) {
		if (matrix == null || matrix.length == 0) return false;
		int rows = matrix.length;
		int columns = matrix[0].length;

		int start = 0, end = rows * columns -1;
		while (start <= end) {
			int mid = (start + end) /2;
			int current = matrix[mid / columns][mid % columns];
			if (target > current) {
				start = mid + 1;
			} else if (target < current) {
				end = mid - 1;
			} else {
				return true;
			}
		}

		return false;
	}

	/**
	 * 1. Integers in each row are sorted in ascending from left to right.
	 * 2. Integers in each column are sorted in ascending from top to bottom.
	 *[[1,   4,  7, 11, 15],
	 * [2,   5,  8, 12, 19],
	 * [3,   6,  9, 16, 22],
	 * [10, 13, 14, 17, 24],
	 * [18, 21, 23, 26, 30]]
	 *
	 * Search the matrix from top right corner - O(m+n)
	 */
	public boolean searchMatrix2(int[][] matrix, int target) {
		if (matrix == null || matrix.length == 0) return false;
		int rows = matrix.length;
		int columns = matrix[0].length;

		int row = 0, column = columns - 1;

		while (row < rows && column >=0) {
			if (matrix[row][column] < target) {
				row ++;
			} else if (matrix[row][column] > target) {
				column --;
			} else {
				return true;
			}
		}
		return false;
	}

	/**
	 * searches for a value in an m x n matrix, return the occurrence of it.
	 *
	 * Integers in each row are sorted from left to right.
	 * Integers in each column are sorted from up to bottom.
	 * No duplicate integers in each row or column.
	 *
	 * Search the matrix from top right corner - O(m+n)
	 */
	public int searchMatrix22(int[][] matrix, int target) {
		if (matrix == null || matrix.length == 0)   return 0;

		int rows = matrix.length;
		int cols = matrix[0].length;

		int row = 0;
		int col = cols - 1;

		int count = 0;
		while (row < rows && col >= 0) {
			int val = matrix[row][col];
			if (val == target) {
				count++;
				col--;
				row++;
			} else if (val > target) {
				col--;
			} else {
				row++;
			}
		}

		return count;
	}

	public static void main(String[] args) {
		SearchMatrix searchMatrix = new SearchMatrix();
		int[][] matrix = {{0}};
		System.out.println(searchMatrix.searchMatrix(matrix, 1));
	}

}
