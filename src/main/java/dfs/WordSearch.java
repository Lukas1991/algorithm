package dfs;

/**
 * 79. Word Search
 * Given a 2D board and a word, find if the word exists in the grid.
 * The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.
 * For example,
 * Given board =
[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]
 * word = "ABCCED", -> returns true,
 * word = "SEE", -> returns true,
 * word = "ABCB", -> returns false.
 *
 */
public class WordSearch {

	//No additional space, mark visited as *
	public boolean exist(char[][] board, String word) {
		int m = board.length;
		if (m == 0)
			return false;
		int n = board[0].length;

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (exist(board, i, j, word))
					return true;
			}
		}
		return false;
	}

	private boolean exist(char[][] board, int i, int j, String word) {
		if (word.length() == 0)
			return true;
		if (i < 0 || j < 0 || i >= board.length || j >= board[0].length)
			return false;
		if (board[i][j] != word.charAt(0))
			return false;

		char tmp = board[i][j];
		board[i][j] = '*';
		word = word.substring(1);
		boolean find = exist(board, i - 1, j, word) 
				|| exist(board, i + 1, j, word) 
				|| exist(board, i, j - 1, word)
				|| exist(board, i, j + 1, word);
		board[i][j] = tmp;
		return find;
	}
}
