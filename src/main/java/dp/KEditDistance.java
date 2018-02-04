package dp;

import java.util.ArrayList;
import java.util.List;

public class KEditDistance {

    public List<String> kDistance(String[] words, String target, int k) {
        TrieNode root = new TrieNode();
        for (int i = 0; i < words.length; i++) {
            addWord(root, words[i]);
        }

        List<String> result = new ArrayList<>();

        int n = target.length();
        int[] dp = new int[n + 1];
        for (int i = 0; i <= n; ++i) {
            dp[i] = i;
        }

        find(root, result, k, target, dp, "");
        return result;
    }

    //Trie里每个node, 算一遍当前node 的prefix 到target的min distance
    public void find(TrieNode node, List<String> result, int k, String target, int[] dp, String curStr) {
        int n = target.length();
        // dp[i] 表示从Trie的root节点走到当前node节点，形成的Prefix (curStr)
        // 和 target的前i个字符的最小编辑距离
        if (node.hasWord && dp[n] <= k) {
            result.add(node.str);
        }

        for (int i = 0; i < 26; ++i) {
            if (node.children[i] != null) {
                TrieNode nextNode = node.children[i];
                char c = (char) ('a' + i);

                //计算nextNode的dp
                int[] curDistance = new int[n + 1]; //all values 0
                curDistance[0] = curStr.length() + 1; //nextNode match target ""空字符，(root到nextNode全是delete)

                //nextNode match到target各个字符的min distance
                for (int j = 1; j <= n; j++) {
                    if (target.charAt(j - 1) == c) {
                        curDistance[j] = dp[j - 1];
                    } else {
                        curDistance[j] = 1 + Math.min(dp[j - 1], Math.min(curDistance[j - 1], dp[j]));
                        //dp[j-1] means replace tmp.charAt(j-1) with target.charAt(j-1)
                        //dp[j] means delete tmp.charAt(j-1)
                        //curdistance[j-1] means add target.charAt(j-1) after tmp.charAt(j-1)
                    }
                }

                find(nextNode, result, k, target, curDistance, curStr + c);
            }
        }
    }

    public void addWord(TrieNode root, String word) {
        TrieNode now = root;
        for (int i = 0; i < word.length(); i++) {
            Character c = word.charAt(i);
            if (now.children[c - 'a'] == null) {
                now.children[c - 'a'] = new TrieNode();
            }
            now = now.children[c - 'a'];
        }
        now.str = word;
        now.hasWord = true;
    }

    class TrieNode {
        public TrieNode[] children;
        public boolean hasWord;
        public String str;

        public TrieNode() {
            children = new TrieNode[26];
            for (int i = 0; i < 26; ++i) {
                children[i] = null;
            }
            hasWord = false;
        }
    }

    public static void main(String[] args) {
        KEditDistance obj = new KEditDistance();
        String[] words = {"cs", "ct", "cby"};
        List<String> result = obj.kDistance(words, "cat", 1);
        System.out.println(result.toString());
    }

}
