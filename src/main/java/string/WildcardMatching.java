package string;

public class WildcardMatching {

    public static boolean isMatch(String s, String p) {
        int i = 0;  //s index
        int j = 0;  //p index
        int previousStar = -1;
        int preMatchInS = 0;

        while (i < s.length()) {
            if (j < p.length() && (p.charAt(j) == s.charAt(i) || p.charAt(j) == '?')) {
                i++;
                j++;
            } else if (j < p.length() && p.charAt(j) == '*') {
                previousStar = j;
                preMatchInS = i;
                j++;
            }
            //find a mismatch: j == p.length || p.charAt(j) != s.charAt(i)
            else if (previousStar != -1) {
                j = previousStar + 1;

                //s[preMatch] is matched with *, move i to preMatch + 1, to examine the new i, j
                preMatchInS++;
                i = preMatchInS;
            } else {
                return false;
            }
        }

        //check for remaining characters in pattern
        while (j < p.length() && p.charAt(j) == '*') {
            j++;
        }

        return j == p.length();
    }

    public static void main(String[] args) {
        System.err.println(isMatch("c", "*?*")); //true
        System.err.println(isMatch("aaaa", "***a")); //true
        System.err.println(isMatch("abed", "?b*d**"));  //true
    }
}
