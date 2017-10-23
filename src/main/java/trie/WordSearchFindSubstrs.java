package trie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * O(n * k), n is str length, k is avarage or word length
 */
public class WordSearchFindSubstrs {
    TrieNode root;

    //找长单词里所有在字典里的substr
    public List<String> findSubstrs(String[] words, String str) {
        root = new TrieNode();

        for (String word : words) {
            addWord(word);
        }

        List<String> res = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            char start = str.charAt(i);
            if (root.children.containsKey(start)) {
                find(str, i, res, root);
            }
        }

        return res;
    }

    private void find(String str, int start, List<String> res, TrieNode node) {
        if (start == str.length()) {
            return;
        }

        for (int i = start; i < str.length(); i++) {
            char c = str.charAt(i);
            if (node.children.containsKey(c)) {
                TrieNode next = node.children.get(c);

                if (next.word != null) {
                    res.add(next.word);
                    next.word = null;
                }

                node = next;
            } else {
                return;
            }
        }
    }

    private void addWord(String word) {
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!curr.children.containsKey(c)) {
                TrieNode newnode = new TrieNode();
                curr.children.put(c, newnode);
            }

            curr = curr.children.get(c);
        }

        curr.word = word;
    }

    class TrieNode {
        String word;
        Map<Character, TrieNode> children = new HashMap<>();
    }

    public static void main(String[] args) {
        WordSearchFindSubstrs obj = new WordSearchFindSubstrs();

        String[] words = {"oat", "oatp", "pea", "eat", "atp"};
        String str = "oatpeaxy";

        List<String> res = obj.findSubstrs(words, str);
        System.err.println(res.toString()); //[oat, oatp, atp, pea]
    }
}
