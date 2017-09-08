package matrix;

//Followup in https://segmentfault.com/a/1190000003819277#articleHeader11

/**
 * [2nd bit, 1st bit] = [next state, current state]
 */
public class GameOfLife {

    public void gameOfLife(int[][] board) {
        int m = board.length;
        if (m == 0) {
            return;
        }
        int n = board[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int status = board[i][j];   //current status
                int count = count(board, i, j);
                int next = 0;   //next status
                if (status == 1 && (count == 2 || count == 3)) {
                    next = 1;
                } else if (status == 0 && count == 3) {
                    next = 1;
                }

                //set 2nd bit as next, 1st bit still old status
                board[i][j] = (next << 1) | status;
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = (board[i][j] & 2) >> 1;
            }
        }

    }

    //count 8 neighbors
    private int count(int[][] board, int i, int j) {
        int count = 0;
        int[] dx = {-1, -1, -1, 0, 1, 1, 1, 0};
        int[] dy = {-1, 0, 1, 1, 1, 0, -1, -1};

        for (int k = 0; k < dx.length; k++) {
            int x = i + dx[k];
            int y = j + dy[k];
            if (x >= 0 && y >= 0 && x < board.length && y < board[0].length) {
                int status = board[x][y] & 1;
                if (status == 1) {
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        GameOfLife gameOfLife = new GameOfLife();

        int[][] board = {
            {1, 1},
            {1, 0}
        };
        int m = board[0].length;
        int n = board.length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.err.print(board[i][j] + ", ");
            }
            System.err.println();
        }

        gameOfLife.gameOfLife(board);


        System.err.println("after:");

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.err.print(board[i][j] + ", ");
            }
            System.err.println();
        }

    }

}
