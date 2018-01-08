package backtracking;

import java.util.HashMap;
import java.util.Map;

public class WordPattern {

    public boolean wordPattern(String pattern, String str) {
        String[] arr = str.split(" ");
        if (arr.length != pattern.length()) return false;

        Map<Character, String> map = new HashMap<>();

        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            String s = arr[i];
            if (map.containsKey(c)) {
                if (!map.get(c).equals(s)) {
                    return false;
                }
            } else {
                if (map.values().contains(s)) {
                    return false;
                }
                map.put(c, s);
            }

        }

        return true;
    }

    public boolean wordPatternMatch(String pattern, String str) {
        return isMatch(str, 0, pattern, 0, new HashMap<>());
    }

    boolean isMatch(String str, int si, String pat, int pi, Map<Character, String> map) {
        // base case
        if (si == str.length() && pi == pat.length()) return true;
        if (si == str.length() || pi == pat.length()) return false;

        // get current pattern character
        char c = pat.charAt(pi);

        // if the pattern character exists
        if (map.containsKey(c)) {
            String sub = map.get(c);

            // then check if we can use it to match str[i...i+s.length()]
            if (!str.startsWith(sub, si)) {
                return false;
            }

            // if it can match, great, continue to match the rest
            return isMatch(str, si + sub.length(), pat, pi + 1, map);
        } else {

            // pattern character does not exist in the map
            for (int k = si; k < str.length(); k++) {
                String sub = str.substring(si, k + 1);

                if (!map.values().contains(sub)) {
                    // create or update it
                    map.put(c, sub);

                    // continue to match the rest
                    if (isMatch(str, si + sub.length(), pat, pi + 1, map)) {
                        return true;
                    }

                    // backtracking
                    map.remove(c);
                }

            }

            // we've tried our best but still no luck
            return false;
        }

    }
}
