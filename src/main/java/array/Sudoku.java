package array;

public class Sudoku {

	/**
	 * 36. Valid Sudoku
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

	/**
	 * 37. Sudoku Solver
	 * Backtracking. For each blank cell, try 1-9. 如果试的数字is valid, 那么继续solve, 不然试下一个数
	 * The time complexity should be 9 ^ m (m represents the number of blanks to be filled in), since each blank can have 9 choices.
	 */
	public void solveSudoku(char[][] board) {
		solve(board);
	}

	private boolean solve(char[][] board) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {

				if (board[i][j] == '.') {
					for (char c = '1'; c <= '9'; c++) {

						if (isValid(board, i, j, c)) {
							board[i][j] = c;

							if (solve(board)) {
								return true;
							}

							board[i][j] = '.';
						}
					}

					return false; //1-9 cannot solve, have to go back
				}
			}
		}

		return true;  //no blank cells
	}

	private boolean isValid(char[][] board, int row, int col, char c) {
		int blkrow = (row / 3) * 3;
		int	blkcol = (col / 3) * 3;
		for (int i = 0; i < 9; i++) {
			if (board[row][i] == c) return false;
			if (board[i][col] == c) return false;
			if (board[blkrow + i / 3][blkcol + i % 3] == c)  return false;
		}

		return true;
	}

}
