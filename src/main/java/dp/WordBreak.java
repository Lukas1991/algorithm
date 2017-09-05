package dp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WordBreak {

    /**
     * Use a cache. map.get(k) indicates start at k.
     * Time complexity : O(n^2).
     * Space complexity : O(n).
     */
    public boolean wordBreak1Best(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);
        booleanMap.put(s.length(), true);
        return helper(s, 0, dict);
    }

    //key - start index, value - s.substring(start) can break?
    Map<Integer, Boolean> booleanMap = new HashMap<>();

    private boolean helper(String s, int start, Set<String> dict) {
        if (booleanMap.containsKey(start)) {
            return booleanMap.get(start);
        }

        for (int i = start; i < s.length(); i++) {
            String sub = s.substring(start, i + 1);
            if (dict.contains(sub)) {
                if (helper(s, i + 1, dict)) {
                    booleanMap.put(start, true);
                    return true;
                }
            }
        }
        booleanMap.put(start, false);
        return false;
    }

    /**
     * Use dp[], dp[k]==true indicates ends at k, s.substring(0,k) can be segmented using dictionary.
     * Initial state dp[0] == true, return dp[s.length()].
     * Time complexity : O(n^2).
     * Space complexity : O(n).
     */
    public boolean wordBreak1DP(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);
        int n = s.length();

        boolean dp[] = new boolean[n + 1];
        dp[0] = true; //set initial state to be true

        for (int i = 1; i <= n; i++) {
            for (int k = 0; k < i; k++) {
                if (dp[k] && dict.contains(s.substring(k, i))) {
                    dp[i] = true;
                    break;
                }

            }
        }

        return dp[n];
    }


    /**
     * Use a cache.
     * Time complexity : O(n^3).
     * Space complexity : O(n^3).
     */
    public List<String> wordBreak2(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);
        List<String> empty = new ArrayList<>();
        empty.add("");
        map.put(s.length(), empty);
        return word_Break(s, dict, 0);
    }

    //key - start index, value -  word break of s.substring(start)
    HashMap<Integer, List<String>> map = new HashMap<>();

    public List<String> word_Break(String s, Set<String> wordDict, int start) {
        if (map.containsKey(start)) {
            return map.get(start);
        }
        List<String> res = new ArrayList<>();

        for (int end = start + 1; end <= s.length(); end++) {
            String sub = s.substring(start, end);
            if (wordDict.contains(sub)) {
                List<String> list = word_Break(s, wordDict, end);

                for (String l : list) {
                    if (l.equals("")) {
                        res.add(sub);
                    } else {
                        res.add(sub + " " + l);
                    }
                }
            }
        }

        map.put(start, res);
        return res;
    }

    public static void main(String[] args) {
        WordBreak obj = new WordBreak();

        List<String> dict = new ArrayList<>();
        dict.add("cat");
        dict.add("cats");
        dict.add("and");
        dict.add("sand");
        dict.add("dog");

        String str = "catsanddog";

        System.err.println(obj.wordBreak1Best(str, dict));
        List<String> res = obj.wordBreak2(str, dict);

        for (String s : res) {
            System.err.println(s);
        }
    }

}
