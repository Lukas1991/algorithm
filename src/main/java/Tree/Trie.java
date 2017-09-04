package Tree;

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (node.keys[c - 'a'] == null) {
                node.keys[c - 'a'] = new TrieNode();
            }
            node = node.keys[c - 'a'];
        }
        node.isEnd = true;
    }

    private TrieNode searchPrefix(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (node.keys[c - 'a'] != null) {
                node = node.keys[c - 'a'];
            } else {
                return null;
            }
        }
        return node;
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        TrieNode node = searchPrefix(word);
        return node != null;
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        TrieNode node = searchPrefix(prefix);
        return node != null && !node.isEnd;
    }

    class TrieNode {
        boolean isEnd;
        //26 children
        TrieNode[] keys;

        public TrieNode() {
            keys = new TrieNode[26];
        }
    }
}
