package string;

public class RemoveDuplicateLetters {

    /**
     * 316. Remove Duplicate Letters
     * Given a string which contains only lowercase letters, remove duplicate letters so that every letter appear once and only once.
     * You must make sure your result is the smallest in lexicographical order among all possible results.
     *
     * Example:
     * Given "bcabc"
     * Return "abc"
     *
     * Given "cbacdcbc"
     * Return "acdb"
     *
     * The greedy choice (i.e., the leftmost letter in the answer) is the smallest s[i]
     * the suffix s[i .. ] contains all the unique letters.
     * The runtime is O(26 * n) = O(n).
     */
    public String removeDuplicateLetters(String s) {
        if (s.length() == 0) {
            return "";
        }

        int[] cnt = new int[26];
        int pos = 0; // the position for the smallest s[i]

        //count each char
        for (int i = 0; i < s.length(); i++) {
            cnt[s.charAt(i) - 'a']++;
        }

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) < s.charAt(pos)) {
                pos = i;
            }

            cnt[s.charAt(i) - 'a']--;

            if (cnt[s.charAt(i) - 'a'] == 0) {
                break;
            }
        }

        char c = s.charAt(pos);
        String rest = s.substring(pos + 1);
        rest = rest.replaceAll(c + "", "");

        return c + removeDuplicateLetters(rest);
    }

    public static void main(String[] args) {
        RemoveDuplicateLetters obj = new RemoveDuplicateLetters();
        String res = obj.removeDuplicateLetters("cbacdcbc");
        System.err.println(res);
    }
}
