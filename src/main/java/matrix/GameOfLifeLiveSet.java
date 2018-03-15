package matrix;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//Follow up: what if the board size is very large, say 100 million * 100 million? We have to store the board data on local disk.
// Idea: we do not store the whole board. We only store the live cells in a set and other cells are dead by default.
// We read in the board data line by line and compute the new cell state using 3 lines each time. Then throw away the top line and read in a new line.
// I/O API: int[] readLine(), void writeLine(int[] array)
public class GameOfLifeLiveSet {

    int[] readLine(int[][] board, int l) {
        if (l < board.length) {
            return board[l];
        } else {
            return null;
        }
    }

    void writeLine(int[][] board, int[] array, int l) {
        board[l] = array;
    }

    public void gameOfLife(int[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return;
        }
        int[] prev = null, cur = null, next = null;
        int[] pointer = null;

        int rl = 0;
        int wl = 0;
        while ((pointer = readLine(board, rl++)) != null) {
            if (cur == null) {
                cur = pointer;
                continue;
            }

            if (next == null) {
                next = pointer;
            }

            // First row
            if (prev == null) {
                int[][] tmpBoard = new int[2][];
                tmpBoard[0] = cur.clone();
                tmpBoard[1] = next.clone();
                int[][] nextStateBoard = updateBoard(tmpBoard);
                writeLine(board, nextStateBoard[0], wl++);
            } else {
                int[][] tmpBoard = new int[3][];
                tmpBoard[0] = prev.clone();
                tmpBoard[1] = cur.clone();
                tmpBoard[2] = next.clone();
                int[][] nextStateBoard = updateBoard(tmpBoard);
                writeLine(board, nextStateBoard[1], wl++);
            }

            prev = cur;
            cur = next;
            next = null;
        }

        // Last row
        if (prev != null) {
            int[][] tmpBoard = new int[2][];
            tmpBoard[0] = prev.clone();
            tmpBoard[1] = cur.clone();
            int[][] nextStateBoard = updateBoard(tmpBoard);
            writeLine(board, nextStateBoard[1], wl++);
        } else {
            //only 1 line
            int[][] tmpBoard = new int[1][];
            tmpBoard[0] = cur.clone();
            int[][] nextStateBoard = updateBoard(tmpBoard);
            writeLine(board, nextStateBoard[0], wl++);
        }
    }

    public class Coord {
        int x;
        int y;

        public Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public boolean equals(Object o) {
            return o instanceof Coord && ((Coord) o).x == x && ((Coord) o).y == y;
        }

        public int hashCode() {
            int hashCode = 1;
            hashCode = hashCode * 31 + x;
            hashCode = hashCode * 31 + y;

            return hashCode;
        }
    }

    // Private helper function that update the board state
    private int[][] updateBoard(int[][] board) {
        if (board == null || board.length == 0) {
            return board;
        }

        int m = board.length;
        int n = board[0].length;

        Set<Coord> live = new HashSet<>();

        // Find the live cell in current state
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 1) {
                    live.add(new Coord(i, j));
                }
            }
        }

        live = updateLive(live, m, n);

        // Update board
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = live.contains(new Coord(i, j)) ? 1 : 0;
            }
        }

        return board;
    }

    // Private helper that computes the live cells in the next state given the current state
    private Set<Coord> updateLive(Set<Coord> lives, int m, int n) {
        //value is neighbours live count
        Map<Coord, Integer> neighbours = new HashMap<>();

        // Update the number of live neighbours of each cell
        for (Coord cell : lives) {
            for (int i = Math.max(0, cell.x - 1); i <= Math.min(m - 1, cell.x + 1); i++) {
                for (int j = Math.max(0, cell.y - 1); j <= Math.min(n - 1, cell.y + 1); j++) {
                    if (i == cell.x && j == cell.y) {
                        continue;
                    }
                    Coord c = new Coord(i, j);

                    if (neighbours.containsKey(c)) {
                        neighbours.put(c, neighbours.get(c) + 1);
                    } else {
                        neighbours.put(c, 1);
                    }
                }
            }
        }

        // Update live cells
        Set<Coord> newLives = new HashSet<>();
        for (Map.Entry<Coord, Integer> entry : neighbours.entrySet()) {
            Coord key = entry.getKey();
            int value = entry.getValue();

            if (lives.contains(key) && (value == 2 || value == 3)) {
                newLives.add(key);
            }
            if (!lives.contains(key) && value == 3) {
                newLives.add(key);
            }
        }

        return newLives;
    }

    public static void main(String[] args) {
        int[][] board = {
                {1, 1},
                {1, 0}
        };

        GameOfLifeLiveSet obj = new GameOfLifeLiveSet();
        obj.gameOfLife(board);

        System.err.println("after:");

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.err.print(board[i][j] + ", ");
            }
            System.err.println();
        }
    }
}


