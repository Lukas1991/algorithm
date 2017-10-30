package matrix;

/**
 * 348. Design Tic-Tac-Toe
 * Design a Tic-tac-toe game that is played between two players on a n x n grid.

 You may assume the following rules:

 A move is guaranteed to be valid and is placed on an empty block.
 Once a winning condition is reached, no more moves is allowed.
 A player who succeeds in placing n of their marks in a horizontal, vertical, or diagonal row wins the game.
 */
public class TicTacToe {
	int[] rows;
    int[] cols;
    int size;
    int diagnal;
    int antiDiagnal;
    
    /** Initialize your data structure here. */
    public TicTacToe(int n) {
        rows = new int[n];
        cols = new int[n];
        this.size = n;
    }
    
    /** Player {player} makes a move at ({row}, {col}).
        @param row The row of the board.
        @param col The column of the board.
        @param player The player, can be either 1 or 2.
        @return The current winning condition, can be either:
                0: No one wins.
                1: Player 1 wins.
                2: Player 2 wins. */
    //Time O(1)
    //Space O(n)
    public int move(int row, int col, int player) {
        int toAdd = player == 1 ? 1 : -1;
        
        rows[row] += toAdd;
        if (Math.abs(rows[row]) == size)    return player;
        
        cols[col] += toAdd;
        if (Math.abs(cols[col]) == size)    return player;
        
        if (row == col) {
            diagnal += toAdd;
            if (Math.abs(diagnal) == size)    return player;
        }
        
        if (col == size - row - 1) {
            antiDiagnal += toAdd;
            if (Math.abs(antiDiagnal) == size)    return player;
        }
        
        return 0;
    }
}
