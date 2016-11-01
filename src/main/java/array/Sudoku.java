package array;

public class Sudoku {

	/**
	 * rows[i][j] means if rows[i] has a number j
	 * cols[i][j] means if cols[i] has a number j
	 * blocks[i][j] means if blocks[i] has a number j
	 * blocks number is [0,1,2]
	 * 					[3,4,5]
	 * 					[6,7,8]
	 * 
	 */
	
	public boolean isValidSudoku(char[][] board) {
		//boolean default value is false
        boolean[][] rows = new boolean[9][9];
        boolean[][] cols = new boolean[9][9];
        boolean[][] blocks = new boolean[9][9];
        
        for (int row = 0; row < 9; row++) {
        	for (int col = 0; col < 9; col++) {
        		
        		if (board[row][col] == '.') continue;
        		
        		int blockNum = row/3 * 3 + col/3;
        		int num = board[row][col] - '1'; // index is 0-8
        		//if already has this number in row or cols, or block
        		if (rows[row][num] || cols[col][num] || blocks[blockNum][num]) {
        			return false;
        		}
        		
        		rows[row][num] = cols[col][num] = blocks[blockNum][num] = true;
        	}
        }
        
        return true;
    }
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
