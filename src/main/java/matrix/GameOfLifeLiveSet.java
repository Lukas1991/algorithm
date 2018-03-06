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

    int[] readLine() {
        return new int[200];
    }

    void writeLine(int[] array) {

    }

    public void gameOfLife() {
        int[] prev = null, cur = null, next = null;
        int[] pointer = null;

        while ((pointer = readLine()) != null) {
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
                writeLine(nextStateBoard[0]);
            } else {
                int[][] tmpBoard = new int[3][];
                tmpBoard[0] = prev.clone();
                tmpBoard[1] = cur.clone();
                tmpBoard[2] = next.clone();
                int[][] nextStateBoard = updateBoard(tmpBoard);
                writeLine(nextStateBoard[1]);
            }

            prev = cur;
            cur = next;
            next = null;
        }

        // Last row
        int[][] tmpBoard = new int[2][];
        tmpBoard[0] = prev.clone();
        tmpBoard[1] = cur.clone();
        int[][] nextStateBoard = updateBoard(tmpBoard);
        writeLine(nextStateBoard[1]);
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
}


