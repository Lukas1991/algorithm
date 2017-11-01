package matrix;

public class RotateImage {

    //rotate in-place，layer by layer
    public void rotate(int[][] matrix) {
        int n = matrix[0].length;
        for (int layer = 0; layer < n / 2; layer++) {
            int i = layer;

            for (int j = layer; j < n - 1 - layer; j++) {

                //left top
                int leftTop = matrix[i][j];
                //right top
                int rightTop = matrix[j][n - 1 - i];
                matrix[j][n - 1 - i] = leftTop;
                //left bottom
                int leftBottom = matrix[n - 1 - i][n - 1 - j];
                matrix[n - 1 - i][n - 1 - j] = rightTop;
                //right bottom
                int rightBottom = matrix[n - 1 - j][i];
                matrix[n - 1 - j][i] = leftBottom;

                matrix[i][j] = rightBottom;
            }
        }

    }

    public void rotateDa(int[][] matrix) {
        int n = matrix.length;

        for (int layer = 0; layer < n / 2; layer++) {
            int last = n - layer - 1;

            for (int i = layer; i < last; i++) {
                int offset = i - layer;
                //leftBottom
                int tmp = matrix[last - offset][layer]; // left to tmp

                matrix[last - offset][layer] = matrix[last][last - offset]; // bottom to left
                matrix[last][last - offset] = matrix[i][last]; // right to bottom
                matrix[i][last] = matrix[layer][i]; // top to right
                matrix[layer][i] = tmp;
            }
        }
    }

}
