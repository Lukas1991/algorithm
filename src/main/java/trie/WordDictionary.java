package trie;

import java.util.HashMap;
import java.util.Map;

public class WordDictionary {

	TrieNode root;

	/** Initialize your data structure here. */
	public WordDictionary() {
		root = new TrieNode();
	}

	/** Adds a word into the data structure. */
	public void addWord(String word) {
		TrieNode curr = root;
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			if (!curr.children.containsKey(c)) {
				curr.children.put(c, new TrieNode());
			}

			curr = curr.children.get(c);
		}

		curr.isEnd = true;
	}

	/**
	 * Returns if the word is in the data structure. A word could contain the
	 * dot character '.' to represent any one letter.
	 */
	public boolean search(String word) {
		return search(root, word);
	}

	private boolean search(TrieNode node, String word) {
		if (word.isEmpty()) {
			return node.isEnd;
		}

		char c = word.charAt(0);
		String sub = word.substring(1);

		if (c == '.') {
			for (TrieNode child : node.children.values()) {
				if (search(child, sub)) {
					return true;
				}
			}

			return false;

		} else {
			if (node.children.containsKey(c)) {
				return search(node.children.get(c), sub);
			} else {
				return false;
			}
		}
	}

	class TrieNode {
		boolean isEnd;
		Map<Character, TrieNode> children;

		public TrieNode() {
			this.children = new HashMap<>();
		}
	}

	public static void main(String[] args) {
		WordDictionary obj = new WordDictionary();
		obj.addWord("bad");
		obj.addWord("dad");
		obj.addWord("mad");
		System.err.println(obj.search("pad"));
		System.err.println(obj.search("bad"));
		System.err.println(obj.search(".ad"));
		System.err.println(obj.search("b.."));
	}

}
