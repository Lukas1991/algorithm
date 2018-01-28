package trie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordSquare {

    /**
     * 判断（i, j）(j, i)两个字符相等
     */
    public boolean validWordSquare(List<String> words) {
        int n = words.size();
        ;
        for (int i = 0; i < n; i++) {
            int m = words.get(i).length();
            if (m > n) {
                return false;
            }

            for (int j = 0; j < m; j++) {
                if (j >= n || i >= words.get(j).length() || words.get(i).charAt(j) != words.get(j).charAt(i)) {
                    return false;
                }
            }
        }

        return true;
    }

    //每个点存prefix到这个点的 word list
    class TrieNode {
        List<String> startWith = new ArrayList<>();
        Map<Character, TrieNode> children = new HashMap<>();
    }

    TrieNode root;

    /**
     * 1. words 构建Trie
     * 2. 以每个word为起始，dfs/back tracking搜索能不能够建成square
     * 3. 检查够建square时，根据tmp list中已经有的words, 算出要搜的prefix, find words ByPrefix, 放到list里面
     */
    public List<List<String>> wordSquares(String[] words) {
        root = new TrieNode();

        for (String word : words) {
            addWord(word);
        }

        List<List<String>> res = new ArrayList<>();
        for (String word : words) {
            List<String> list = new ArrayList<>();
            list.add(word);
            search(word.length(), list, res);
        }

        return res;
    }

    //dfs
    private void search(int len, List<String> list, List<List<String>> res) {
        if (list.size() == len) {
            res.add(new ArrayList<>(list));
            return;
        }

        int index = list.size();
        String prefix = "";
        for (String s : list) {
            prefix += s.charAt(index);
        }

        List<String> words = findByPrefix(prefix);
        for (String word : words) {
            list.add(word);
            search(len, list, res);
            list.remove(list.size() - 1);
        }
    }

    private void addWord(String word) {
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!cur.children.containsKey(c)) {
                cur.children.put(c, new TrieNode());
            }

            cur = cur.children.get(c);
            //add word to this trie node
            cur.startWith.add(word);
        }
    }

    private List<String> findByPrefix(String prefix) {
        TrieNode cur = root;
        List<String> res = new ArrayList<>();
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (!cur.children.containsKey(c)) {
                return res;
            } else {
                cur = cur.children.get(c);
            }
        }

        res.addAll(cur.startWith);
        return res;
    }
}
