package matrix;

import java.util.Arrays;

public class SurroundedRegions {

    /**
     * 130. Surrounded Regions
     * 1, check the four border of the matrix. If there is a element is 'O', alter it and all its neighbor 'O' elements to '1'.
     * 2, alter all the 'O' to 'X'
     * 3, alter all the '1' to 'O'
     */
    public void solve(char[][] board) {
        int rows = board.length;
        if (rows == 0) {
            return;
        }
        int columns = board[0].length;

        //mark up and down border's 0 to 1
        for (int i = 0; i < rows; i++) {
            dfs(board, i, 0);
            if (columns > 1) {
                dfs(board, i, columns - 1);
            }
        }

        //left and right border
        for (int j = 1; j < columns - 1; j++) {
            dfs(board, 0, j);
            if (rows > 1) {
                dfs(board, rows - 1, j);
            }
        }

        //mark O as X
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }

        //change 1 to O
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (board[i][j] == '1') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    private void dfs(char[][] board, int i, int j) {
        if (board[i][j] == 'O') {
            board[i][j] = '1';

            //不是>=0, 边界暂时不用管, 为了避免死循环,
            if (i - 1 > 0) {
                dfs(board, i-1, j);
            }

            //不是<length, 边界暂时不用管, 为了避免死循环,
            if (i + 1 < board.length - 1) {
                dfs(board, i+1, j);
            }

            if (j - 1 > 0) {
                dfs(board, i, j-1);
            }

            if (j + 1 < board[0].length - 1) {
                dfs(board, i, j+1);
            }
        }
    }

    public static void main(String[] args) {
        SurroundedRegions obj = new SurroundedRegions();
        char[][] board = new char[4][4];
        board[0] = new char[]{'X', 'X', 'X', 'X'};
        board[1] = new char[]{'X', 'O', 'O', 'X'};
        board[2] = new char[]{'X', 'X', 'O', 'X'};
        board[3] = new char[]{'X', 'O', 'X', 'X'};

        obj.solve(board);

        for (char[] arr : board) {
            System.err.println(Arrays.toString(arr));
        }

    }
}
