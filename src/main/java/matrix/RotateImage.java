package matrix;

import java.util.Arrays;

public class RotateImage {

    //rotate n*n matrix, Rotate the image by 90 degrees (clockwise).
    //rotate in-place，layer by layer
    public static void rotate(int[][] matrix) {
        int n = matrix[0].length;
        for (int layer = 0; layer < n / 2; layer++) {
            int i = layer;

            for (int j = layer; j < n - 1 - layer; j++) {

                //left top
                int leftTop = matrix[i][j];
                //right top
                int rightTop = matrix[j][n - 1 - i];
                matrix[j][n - 1 - i] = leftTop;
                //right bottom
                int rightBottom = matrix[n - 1 - i][n - 1 - j];
                matrix[n - 1 - i][n - 1 - j] = rightTop;

                //left bottom
                int leftBottom = matrix[n - 1 - j][i];
                matrix[n - 1 - j][i] = rightBottom;

                matrix[i][j] = leftBottom;
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            System.err.println(Arrays.toString(matrix[i]));
        }
    }

    public static void rotateAntiClockwise(int[][] matrix) {
        int n = matrix[0].length;
        for (int layer = 0; layer < n / 2; layer++) {
            int i = layer;

            for (int j = layer; j < n - 1 - layer; j++) {

                //left top
                int leftTop = matrix[i][j];

                //left bottom
                int leftBottom = matrix[n - 1 - j][i];
                matrix[n - 1 - j][i] = leftTop;

                //right bottom
                int rightBottom = matrix[n - 1 - i][n - 1 - j];
                matrix[n - 1 - i][n - 1 - j] = leftBottom;

                //right top
                int rightTop = matrix[j][n - 1 - i];
                matrix[j][n - 1 - i] = rightBottom;

                matrix[i][j] = rightTop;
            }
        }

//        for (int i = 0; i < matrix.length; i++) {
//            System.err.println(Arrays.toString(matrix[i]));
//        }
    }

    public static void main(String[] args) {
        int[][] matrix = {
            {1,2,3},
            {4,5,6},
            {7,8,9}};

        for (int i = 0; i < matrix.length; i++) {
            System.err.println(Arrays.toString(matrix[i]));
        }

        rotate(matrix);
        //rotateAntiClockwise(matrix);
    }
}
