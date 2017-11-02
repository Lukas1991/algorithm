package trie;

import java.util.ArrayList;
import java.util.List;

public class AutoCompleteSystem {
    TrieNode root;

    String prechars = "";

    //takes O(k*l) time. We need to iterate over l sentences each of average length k, to create the trie for the given set of sentencess.
    public AutoCompleteSystem(String[] sentences, int[] times) {
        root = new TrieNode();
        for (int i = 0; i < sentences.length; i++) {
            insert(sentences[i], times[i]);
        }
    }

    private void insert(String word, int times) {
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.children[index(c)] == null) {
                cur.children[index(c)] = new TrieNode();
            }

            cur = cur.children[index(c)];
        }

        cur.sentence = word;
        cur.times += times;
    }

    /**
     * takes O(p+q+mlog(m)) time
     * lookup take p (prefixLenth) + q (traverse nodes, all nodes under prefix node) + mlogm (sorting the node list)
     */
    public List<String> input(char c) {
        if (c == '#') {
            insert(prechars, 1);
            prechars = "";
            return new ArrayList<>();
        }

        prechars += c;

        List<TrieNode> res = lookup(prechars);

        res.sort((n1, n2) -> {
            if (n2.times == n1.times) {
                return n1.sentence.compareTo(n2.sentence);
            } else {
                return n2.times - n1.times;
            }
        });

        List<String> sentenses = new ArrayList<>();
        for (int i = 0; i < Math.min(3, res.size()); i++) {
            sentenses.add(res.get(i).sentence);
        }

        return sentenses;
    }

    private List<TrieNode> lookup(String prefix) {
        TrieNode cur = root;

        List<TrieNode> res = new ArrayList<>();

        for (int i = 0; i < prefix.length(); i++) {
            int index = index(prefix.charAt(i));
            if (cur.children[index] == null) {
                return res;
            } else {
                cur = cur.children[index];
            }
        }

        traverse(cur, res);

        return res;
    }

    private void traverse(TrieNode tmp, List<TrieNode> res) {
        TrieNode cur = tmp;

        if (cur.sentence != null) {
            res.add(cur);
        }

        for (int i = 0; i < 27; i++) {
            if (cur.children[i] != null) {
                traverse(cur.children[i], res);
            }
        }
    }

    private int index(char c) {
        return c == ' ' ? 26 : c - 'a';
    }

    public static void main(String[] args) {
        AutoCompleteSystem obj = new AutoCompleteSystem(
            new String[]{"i love you", "island","ironman", "i love leetcode"}, new int[]{5,3,2,2});

        //System.err.println(obj.input('w').toString());

        System.err.println(obj.input('i').toString());
        System.err.println(obj.input(' ').toString());
        System.err.println(obj.input('a').toString());
        System.err.println(obj.input('#').toString());


        System.err.println(obj.input('i').toString());
        System.err.println(obj.input(' ').toString());
        System.err.println(obj.input('a').toString());
        System.err.println(obj.input('#').toString());

        System.err.println(obj.input('i').toString());

    }

    class TrieNode {
        int times;
        String sentence;
        TrieNode[] children = new TrieNode[27];

        public TrieNode() {
        }
    }
}
