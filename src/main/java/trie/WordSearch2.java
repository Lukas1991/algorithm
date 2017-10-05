package trie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 212. Word Search II
 * Given a 2D board and a list of words from the dictionary, find all words in the board.
 * 
 * Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. 
 * The same letter cell may not be used more than once in a word.

 * For example, Given words = ["oath","pea","eat","rain"] and board =
[
  ['o','a','a','n'],
  ['e','t','a','e'],
  ['i','h','k','r'],
  ['i','f','l','v']
]
 * Return ["eat","oath"].
 *
 */
public class WordSearch2 {
	TrieNode root;
	
	public List<String> findWords(char[][] board, String[] words) {
		List<String> res = new ArrayList<>();
		root = new TrieNode();
		for (String s : words) {
			addWord(s);
		}
		
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				dfs(board, i, j, root, res);
			}
		}
		
		return res;
    }
	
	private void dfs(char[][] board, int i, int j, TrieNode node, List<String> res) {
		if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || board[i][j] == '*') {
			return;
		}
		
		char c = board[i][j];
		
		if (node.children.containsKey(c)) {
			TrieNode next = node.children.get(c);
			
            if (next.word != null) { //found one word
                res.add(next.word);
                next.word = null;  //mark as null to avoid duplicates
                //do not return; //continue dfs because words may contain "oath", "oathx", some words with same prefix, and prefix is also a word
            }
            
			board[i][j] = '*'; //mark as visited
			
			dfs(board, i-1, j, next, res);
			dfs(board, i+1, j, next, res);
			dfs(board, i, j-1, next, res);
			dfs(board, i, j+1, next, res);
			
			board[i][j] = c;
		} else {
			return;
		}
		
	}
	
	public void addWord(String word) {
		TrieNode curr = root;
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			if (!curr.children.containsKey(c)) {
				curr.children.put(c, new TrieNode());
			}

			curr = curr.children.get(c);
		}

		curr.word = word;
	}
	
	class TrieNode {
		String word;
		Map<Character, TrieNode> children;

		public TrieNode() {
			this.children = new HashMap<>();
		}
	}
	
	public static void main(String[] args) {
		String[] words = {"oath","pea","eat","rain"};
		char[][] board = {
				  {'o','a','a','n'},
				  {'e','t','a','e'},
				  {'i','h','k','r'},
				  {'i','f','l','v'}
		};
		
		WordSearch2 obj = new WordSearch2();
		List<String> res = obj.findWords(board, words);
		System.err.println(res.toString());
	}

}
