package twoPointer;

import java.util.HashMap;
import java.util.Map;

/**
 * 159. Longest Substring with At Most Two Distinct Characters
 * 340. Longest Substring with At Most K Distinct Characters
 */
public class LongestSubstringKDistinct {

    //two pointers + hashMap, O(n)
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int max = 0;
        int j = 0;
        for (int i = 0; i < s.length(); i++) {
            //add j char to window
            while (j < s.length()) {
                char c = s.charAt(j);
                if (map.containsKey(c)) {
                    map.put(c, map.get(c) + 1);
                } else {
                    if (map.size() == 2) {
                        break;
                    } else {
                        map.put(c, 1);
                    }
                }

                j++;
            }

            max = Math.max(max, j - i);

            //remove i char from window
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                int count = map.get(c);
                if (count == 1) {
                    map.remove(c);
                } else {
                    map.put(c, count - 1);
                }
            }
        }

        return max;
    }

    //int数组标记count
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        //也可以用HashMap
        int[] map = new int[256];
        int j = 0;
        int max = 0;

        for (int i = 0; i < s.length(); i++) {
            while (j < s.length() && isValid(map, s.charAt(j), k)) {
                map[s.charAt(j)]++;
                j++;
            }

            max = Math.max(max, j - i);
            map[s.charAt(i)]--;
        }

        return max;
    }

    //加上c后是不是还count <= k
    private boolean isValid(int[] map, char c, int k) {
        map[c]++;
        int count = 0;
        for (int a : map) {
            if (a > 0) {
                count++;
            }
        }
        map[c]--;
        return count <= k;
    }
}
