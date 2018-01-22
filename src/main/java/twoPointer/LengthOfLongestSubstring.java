package twoPointer;

/**
 * 3. Longest Substring Without Repeating Characters
 * Given a string, find the length of the longest substring without repeating characters.
 *
 * For example,
 * Given "abcabcbb", the answer is "abc", which the length is 3.
 * For "bbbbb" the longest substring is "b", with the length of 1.
 */
public class LengthOfLongestSubstring {

    public int lengthOfLongestSubstring(String s) {
        boolean[] hash = new boolean[256];

        int max = 0;
        int j = 0;
        for (int i = 0; i < s.length(); i++) {
            while (j < s.length() && !hash[s.charAt(j)]) {
                hash[s.charAt(j)] = true;
                j++;
            }

            max = Math.max(max, j - i);
            hash[s.charAt(i)] = false;
        }

        return max;
    }

}