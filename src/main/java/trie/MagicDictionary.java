package trie;

import java.util.HashMap;
import java.util.Map;

public class MagicDictionary {

    private TrieNode root;

    /**
     * Initialize your data structure here.
     */
    public MagicDictionary() {
        root = new TrieNode();
    }

    private void insert(String word) {
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


    /**
     * Build a dictionary through a list of words
     */
    public void buildDict(String[] dict) {
        for (String word : dict) {
            insert(word);
        }
    }

    /**
     * Returns if there is any word in the trie that equals to the given word after modifying exactly one character
     */
    public boolean search(String word) {
        return search(root, word, false);
    }

    private boolean search(TrieNode node, String word, boolean changed) {
        if (word.isEmpty()) {
            return node.isEnd && changed;
        }

        char c = word.charAt(0);
        String sub = word.substring(1);

        if (!changed) {
            for (Map.Entry<Character, TrieNode> entry : node.children.entrySet()) {
                if (entry.getKey() == c) {
                    if (search(entry.getValue(), sub, false)) {
                        return true;
                    }
                } else {
                    if (search(entry.getValue(), sub, true)) {
                        return true;
                    }
                }
            }

            return false;
        } else {
            //changed before
            if (node.children.containsKey(c)) {
                node = node.children.get(c);
                return search(node, sub, true);
            } else {
                return false;
            }
        }
    }

    private class TrieNode {
        Map<Character, TrieNode> children;
        boolean isEnd;

        public TrieNode() {
            children = new HashMap<>();
        }
    }

    public static void main(String[] args) {
        MagicDictionary obj = new MagicDictionary();
        obj.buildDict(new String[] {"hello", "leetcode"});
        System.err.println(obj.search("hello")); //false
        System.err.println(obj.search("hhllo")); //true
        System.err.println(obj.search("hell")); //f
        System.err.println(obj.search("leetcoded")); //false
        System.err.println(obj.search("abc")); // false


//        obj.buildDict(new String[]{"hello", "hallo", "leetcode"});
//        System.err.println(obj.search("hello")); //true
//        System.err.println(obj.search("hhllo")); //true
//        System.err.println(obj.search("hell"));  //false
//        System.err.println(obj.search("leetcoded")); //false
    }
}
