package trie;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReplaceWord {

	TrieNode root;

	public String replaceWords(List<String> dict, String sentence) {
		root = new TrieNode();
		for (String word: dict) {
			addWord(word);
		}

		String[] arr = sentence.split("\\s");

		for (int i = 0; i < arr.length; i++) {
			String str = arr[i];
			String prefix = getPrefix(str);
			if (prefix != null) {
				arr[i] = prefix;
			}
		}

		return String.join(" ", arr);
	}


	//return the prefix return null if word does't have a root.
	private String getPrefix(String str) {
		TrieNode current = root;
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (!current.children.containsKey(c)) {
				return null;
			}

			current = current.children.get(c);
			if (current.word != null) {
				return current.word;
			}
		}

		return null;
	}

	private void addWord(String word) {
		TrieNode current = root;

		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			if (!current.children.containsKey(c)) {
				current.children.put(c, new TrieNode());
			}

			current = current.children.get(c);
		}

		current.word = word;
	}

	class TrieNode {
		String word;
		Map<Character, TrieNode> children;

		public TrieNode() {
			children = new HashMap<>();
		}
	}

	public static void main(String[] args) {
		ReplaceWord obj = new ReplaceWord();
		List<String> dict = Arrays.asList("cat", "bat", "rat");
		String sentence = "the cattle was rattled by the battery";
		System.err.println(obj.replaceWords(dict, sentence));
	}

}
