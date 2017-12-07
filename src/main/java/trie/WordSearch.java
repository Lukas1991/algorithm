package trie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordSearch {

    TrieNode root;

    public List<String> findWords(String[] dict, String string) {
        root = new TrieNode();
        for (String word : dict) {
            insert(word);
        }

        List<String> res = new ArrayList<>();
        for (int i = 0; i < string.length(); i++) {
            search(root, string, i, res);
        }

        return res;
    }
    
    private void search(TrieNode node, String string, int start, List<String> res) {
    		int j = start;

        while (j < string.length() && node.children.containsKey(string.charAt(j))) {
            TrieNode child = node.children.get(string.charAt(j));
            if (child.word != null) {
                res.add(child.word);
                child.word = null; // avoid duplicate if return type is list, don't need it if use set
            }
            node = child;
            j++;
        }
    }

    private void insert(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);

            if (!node.children.containsKey(c)) {
                TrieNode newNode = new TrieNode();
                node.children.put(c, newNode);
            }

            node = node.children.get(c);
        }

        node.word = word;
    }

    class TrieNode {
        String word = null;
        Map<Character, TrieNode> children = new HashMap<>();

        public TrieNode() {
        }
    }

    public static void main(String[] args) {
        WordSearch obj = new WordSearch();
        String string = "helloappleappletree";
        String[] arr = {"hello", "ello", "apple", "tree", "appletree"};
        List<String> res = obj.findWords(arr, string);
        System.out.println(res.toString());
    }
}

