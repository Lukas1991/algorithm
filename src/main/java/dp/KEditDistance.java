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

        find(root, result, k, target, dp);
        return result;
    }

    public void find(TrieNode node, List<String> result, int k, String target, int[] dp) {
        int n = target.length();
        // dp[i] 表示从Trie的root节点走到当前node节点，形成的Prefix
        // 和 target的前i个字符的最小编辑距离
        if (node.hasWord && dp[n] <= k) {
            result.add(node.str);
        }

        int[] curDistance = new int[n + 1]; //all values 0

        for (int i = 0; i < 26; ++i) {
            if (node.children[i] != null) {
                char c = (char) ('a' + i);

                curDistance[0] = dp[0] + 1;

                for (int j = 1; j <= n; j++) {
                    if (target.charAt(j - 1) == c) {
                        curDistance[j] = Math.min(dp[j - 1], Math.min(curDistance[j - 1] + 1, dp[j] + 1));
                    } else {
                        curDistance[j] = 1 + Math.min(dp[j - 1], Math.min(curDistance[j - 1], dp[j]));
                    }
                }

                find(node.children[i], result, k, target, curDistance);
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

}
