package matrix;

//Followup in https://segmentfault.com/a/1190000003819277#articleHeader11

/**
 * 初始状态为0和1，那么变化后的状态无非有四个，{00，01，10，11}，其中低位表示当前状态, 高位表示下个状态，
 *
 *  1. current live, count < 2, next status die                 0<-1, 1
 *  2. current live, count ==2 or count ==3, next status live   1<-1, 3
 *  3. current live, count > 3, next status die                 0<-1, 1
 *  4. current die, count = 3, next status live                 1<-0, 2
 *
 *  if(board[y][x] == 1 || board[y][x] == 3), current status is live
 */
public class GameOfLife {


    public void gameOfLife(int[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return;
        }

        int m = board[0].length;
        int n = board.length;

        for (int i=0; i<n; i++) { //y
            for (int j=0; j<m; j++) { //x

                int count = 0;
                //左, 左上, 上, 右上, 右... 顺时针一圈, 共8个neighbors
                int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
                int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};

                for(int k=0; k<8; k++) {
                    int x = j + dx[k];
                    int y = i + dy[k];
                    if (x >= 0 && x < m && y >= 0 && y < n) {
                        if (board[y][x] == 1 || board[y][x] == 3) {
                            count ++;
                        }
                    }
                }

                if (board[i][j] == 1 || board[i][j] == 3) {
                    if (count == 2 || count == 3) {
                        board[i][j] = 3;
                    } else {
                        board[i][j] = 1;
                    }
                } else {
                    if (count == 3) {
                        board[i][j] = 2;
                    }
                }

            }
        }

        //get all cells' next status, get the higher digit
        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                board[i][j] = board[i][j] >>1;
            }
        }

    }

    public static void main(String[] args) {
        GameOfLife gameOfLife = new GameOfLife();

        int[][] board = {
                {1,1},
                {1,0}
        };
        int m = board[0].length;
        int n = board.length;

        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                System.err.print(board[i][j] + ", ");
            }
            System.err.println();
        }

        gameOfLife.gameOfLife(board);


        System.err.println("after:");

        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                System.err.print(board[i][j] + ", ");
            }
            System.err.println();
        }

    }

}
