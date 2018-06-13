package Interview.amzn.oa;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Find substrings of size K with K distinct characters
 * Two pointers
 */
public class SubStringsKDist {

    public List<String> subStringsKDist(String inputStr, int num) {
        if (inputStr == null || inputStr.isEmpty()) {
            return new ArrayList<>();
        }
        List<String> result = new ArrayList<>();
        Set<String> set = new HashSet<>();
        Set<Character> charSet = new HashSet<>();
        int end = 0;
        for (int start = 0; start < inputStr.length(); start++) {
            while (end < inputStr.length() && isValid(inputStr.charAt(end), charSet, num)) {
                charSet.add(inputStr.charAt(end));
                end++;
            }

            if (charSet.size() == num) {
                String sub = inputStr.substring(start, end);
                if (!set.contains(sub)) {
                    set.add(sub);
                    result.add(sub);
                }

            }

            charSet.remove(inputStr.charAt(start));
        }

        return result;
    }

    private boolean isValid(char c, Set<Character> charSet, int num) {
        return !charSet.contains(c) && charSet.size() < num;
    }

    public static void main(String[] args) {
        SubStringsKDist obj = new SubStringsKDist();

        String inputStr = "awaglknagawunagwkwagl";
        List<String> res = obj.subStringsKDist(inputStr, 4);
        //[wagl, aglk, glkn, lkna, knag, gawu, awun, wuna, unag, nagw, agwk, kwag]
        System.err.println(res.toString());

    }
}
