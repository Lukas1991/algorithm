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

    int[] table;

    //9个点，用9个bit表示9个live or die， max value is 111,111,111 = 511, min value is 0, so the size is 512(= 2^9).
    public void createLookupTable() {
        table = new int[512];
        for (int i = 0; i < 512; i++) {
            int lives = Integer.bitCount(i);
            int me = (i & 16) > 0 ? 1 : 0; //n4

            if (lives == 3 || (lives == 4 && me == 1)) { //lives可能包括n4他自己
                table[i] = 1;
            }
        }
    }

    /**
     * n8, n5, n2, a
     * n7, n4, n1, b
     * n6, n3, n0, c
     * 每次读一行，保留前两行，总共存3行. 新的值，存入buffer里，buffer保留最近两行的值
     * 每次新读一行，(用newline, preLine, prepreLine的current state)计算前一行preLine的next state。计算完prepreline的current state无用了，替换prepreline的值为next state
     */
    public void gameOfLifeReadLineByLine(int[][] board) {
        if (board.length == 0 || board[0].length == 0) {
            return;
        }

        createLookupTable();

        int m = board.length;
        int n = board[0].length;

        int[][] buffer = new int[2][n];

        int[] prepreLine = null;
        int[] preLine = readLine(board, 0);
        int[] newLine;

        int i = 1; //line number

        do {
            newLine = readLine(board, i);

            int env = addRightCol(0, 0, prepreLine, preLine, newLine);

            for (int j = 0; j < n; j++) {
                env = (env % 64) * 8; //之前的右边两列左移

                if (j + 1 < n) {
                    env = addRightCol(env, j + 1, prepreLine, preLine, newLine);
                }

                buffer[(i + 1) % 2][j] = table[env];
            }

            //save the next state for prepreLine
            if (prepreLine != null) {
                for (int j = 0; j < n; j++) {
                    prepreLine[j] = buffer[i % 2][j];
                }
            }

            prepreLine = preLine;
            preLine = newLine;
            i++;

        } while (newLine != null);

        //save the next state for prepreLine, which is the last line
        if (prepreLine != null) {
            for (int j = 0; j < n; j++) {
                prepreLine[j] = buffer[i % 2][j];
            }
        }

    }

    int[] readLine(int[][] board, int line) {
        if (line < board.length) {
            return board[line];
        } else {
            return null;
        }
    }

    int addRightCol(int env, int newCol, int[] prepreLine, int[] preLine, int[] newLine) {
        //加上右边的第三列
        if (prepreLine != null && prepreLine[newCol] == 1) {
            env += 4;
        }

        if (preLine[newCol] == 1) {
            env += 2;
        }

        if (newLine != null && newLine[newCol] == 1) {
            env += 1;
        }

        return env;
    }

    //可以把board全读进memory的时候
    public void gameOfLife2(int[][] board) {
        if (board.length == 0 || board[0].length == 0) {
            return;
        }

        createLookupTable();

        int m = board.length;
        int n = board[0].length;

        int[][] buffer = new int[m][n];
        for (int i = 0; i < m; i++) {
            int env = 0;
            if (i - 1 >= 0 && board[i - 1][0] == 1) {
                env += 4;
            }

            if (board[i][0] == 1) {
                env += 2;
            }

            if (i + 1 < m && board[i + 1][0] == 1) {
                env += 1;
            }

            for (int j = 0; j < n; j++) {
                env = (env % 64) * 8; //之前的右边两列左移
                //加上右边的第三列
                if (i - 1 >= 0 && j + 1 < n && board[i - 1][j + 1] == 1) {
                    env += 4;
                }

                if (j + 1 < n && board[i][j + 1] == 1) {
                    env += 2;
                }

                if (i + 1 < m && j + 1 < n && board[i + 1][j + 1] == 1) {
                    env += 1;
                }

                buffer[i][j] = table[env];
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = buffer[i][j];
            }
        }
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
