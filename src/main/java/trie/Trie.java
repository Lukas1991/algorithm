package trie;

import java.util.HashMap;
import java.util.Map;

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    /**
     * Inserts a word into the trie.
     * O(K), k is key's length
     */
    public void insert(String word) {
        TrieNode curr = root;
        char[] arr = word.toCharArray();
        for (char c : arr) {
            if (!curr.children.containsKey(c)) {
                curr.children.put(c, new TrieNode());
            }
            curr = curr.children.get(c);
        }

        curr.isEnd = true;
    }

    private TrieNode searchNode(String word) {
        TrieNode curr = root;
        char[] arr = word.toCharArray();
        for (char c : arr) {
            if (!curr.children.containsKey(c)) {
                return null;
            } else {
                curr = curr.children.get(c);
            }
        }

        return curr;
    }

    /**
     * Returns if the word is in the trie.
     * O(K), k is key's length
     */
    public boolean search(String word) {
        TrieNode node = searchNode(word);
        return node != null && node.isEnd;
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     * O(K), k is key's length
     */
    public boolean startsWith(String prefix) {
        TrieNode node = searchNode(prefix);
        return node != null;
    }

    class TrieNode {
        Map<Character, TrieNode> children;
        boolean isEnd;
        public TrieNode() {
            children = new HashMap<>();
        }
    }
}
