package dp;

import java.util.*;

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
     * Use a cache. 记忆划搜索
     * Time complexity : O(n^3). Size of recursion tree can go up to n^2. The creation of list takes n time.
     * Space complexity : O(n^3). size of map can go up to n, and each value can contains a string list of size n.
     *
     * e.g. "aaa", ["a", "aa", "aaa"]
     * map: 2 - a
     *      1 - a a, aa,
     *      0 - "a a a", "a aa", "aa a", "aaa"
     */
    public List<String> wordBreak2(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);
        List<String> empty = new ArrayList<>();
        empty.add("");
        cache.put("", empty);
        return helper(s, dict);
    }

    //key - string, value -  word break of string
    HashMap<String, List<String>> cache = new HashMap<>();

    public List<String> helper(String s, Set<String> wordDict) {
        if (cache.containsKey(s)) {
            return cache.get(s);
        }
        List<String> res = new ArrayList<>();

        for (int i = 1; i <= s.length(); i++) {
            String left = s.substring(0, i);
            String right = s.substring(i);

            if (wordDict.contains(left)) {
                List<String> list = helper(right, wordDict);
                for (String str : list) {
                    res.add(left + " " + str);
                }
            }
        }

        cache.put(s, res);
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
