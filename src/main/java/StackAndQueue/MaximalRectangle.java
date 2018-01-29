package StackAndQueue;

public class MaximalRectangle {

	/**
	 * 85. Maximal Rectangle: https://leetcode.com/problems/maximal-rectangle/description/
	 * Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing all ones and return its area.
	 * This problem can be converted to the "Largest Rectangle in Histogram" problem.
	 */
	public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;

        int rows = matrix.length;
        int columns = matrix[0].length;
         
        int[][] heights = new int[rows][columns];
        for (int i = 0; i<rows; i++) {
            for (int j = 0; j<columns; j++) {
                if (matrix[i][j] == '1') {
                	//count this columns 1 from row0 to rowi
                    heights[i][j] = (i == 0) ? 1 : (heights[i-1][j] + 1);
                }
            }
        }

//        for (int i = 0; i<rows; i++) {
//            System.err.println(Arrays.toString(heights[i]));
//        }

        int max = 0;
        for (int i = 0; i<rows; i++) {
            int[] hs = heights[i];
            int area = LargestRectangleArea.largestRectangleArea(hs);
            max = Math.max(max, area);
        }
        
        return max;
    }
	
	public static void main(String[] args) {
        MaximalRectangle obj = new MaximalRectangle();
        char[][] matrix = {
            {'1', '0', '1', '0', '0'},
            {'1', '0', '1', '1', '1'},
            {'1', '1', '1', '1', '1'},
            {'1', '0', '0', '1', '0'}
        };

        obj.maximalRectangle(matrix);
	}

}
