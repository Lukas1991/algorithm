package matrix;

public class SetMatrixZero {

    /**
     * use O(1) space
     * have boolean firstRowZero and firstColumnZero, use matrix's first row and first column as the flag arrays
     */
    public void setZeroes(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return;

        boolean firstRowZero = false, firstColumnZero = false;
        int m = matrix[0].length;
        int n = matrix.length;

        //check first row
        for (int j=0; j<m; j++) {
            if (matrix[0][j] == 0) {
                firstRowZero = true;
                break;
            }
        }

        //check first column
        for (int i=0; i<n; i++) {
            if (matrix[i][0] == 0) {
                firstColumnZero = true;
                break;
            }
        }

        //check each cell
        for (int i=1; i<n; i++) {
            for (int j=1; j<m; j++) {
                if (matrix[i][j] == 0) {
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }

        //change each cell
        for (int i=1; i<n; i++) {
            for (int j=1; j<m; j++) {
                if (matrix[0][j] == 0 || matrix[i][0] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        //change first row
        if (firstRowZero) {
            for (int j=0; j<m; j++) {
                matrix[0][j] = 0;
            }
        }

        //change first column
        if(firstColumnZero) {
            for (int i=0; i<n; i++) {
                matrix[i][0] = 0;
            }
        }

    }
}
