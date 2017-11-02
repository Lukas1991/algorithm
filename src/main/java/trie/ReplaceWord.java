package trie;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReplaceWord {

	TrieNode root;

	public String replaceWords(List<String> dict, String sentence) {
		root = new TrieNode();
		for (String s : dict) {
			addWord(s);
		}

		String[] arr = sentence.split(" ");
		for (int i = 0; i < arr.length; i++) {
			String word = arr[i];
			int index = findPrefix(word);
			if (index != -1) {
				String sub = word.substring(0, index);
				arr[i] = sub;
			}
		}

		return String.join(" ", arr);
	}

	// return -1 if word does't have a root.
	// return end index when find the root
	private int findPrefix(String word) {
		TrieNode curr = root;
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			if (curr.children.containsKey(c)) {
				TrieNode node = curr.children.get(c);
				if (node.isEnd) {
					return i + 1;
				} else {
					curr = node;
				}

			} else {
				return -1;
			}
		}

		return -1;
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

	class TrieNode {
		boolean isEnd;
		Map<Character, TrieNode> children;

		public TrieNode() {
			this.children = new HashMap<>();
		}
	}

	public static void main(String[] args) {
		ReplaceWord obj = new ReplaceWord();
		List<String> dict = Arrays.asList("cat", "bat", "rat");
		String sentence = "the cattle was rattled by the battery";
		System.err.println(obj.replaceWords(dict, sentence));
	}

}
