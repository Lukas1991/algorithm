package dp;

/**
 * '?' Matches any single character.
 * '*' Matches any sequence of characters (including the empty sequence).
 *
 */
public class WildcardMatching {

    /**
     * DFS backtracking
     * The complexity of the algorithm is O(p*s),  An example of such a worst-case input is:
     input: bbbbbbbbbbbb
     pattern: *bbbb
     * The basic idea is to have one pointer for the string and one pointer for the pattern.
     * for each iteration, at least one pointer advance one step.
     */
    public boolean isMatch(String s, String p) {
        int i = 0;  //s index
        int j = 0;  //p index
        int starInP = -1;
        int matchEndInS = 0; //chars before matchEndInS in s are matched

        while (i < s.length()) {
            if (j < p.length() && (p.charAt(j) == s.charAt(i) || p.charAt(j) == '?')) {
                i++;
                j++;
            } else if (j < p.length() && p.charAt(j) == '*') {
                //keep i, j
                starInP = j;
                matchEndInS = i;
                j++;
            } else if (starInP != -1) {//j == p.length OR find a mismatch:  p.charAt(j) != s.charAt(i)
                j = starInP + 1;

                //mark s.charAt(matchEndInS) as matched with *, matchEndInS++, then move i to the new matchEndInS, to examine the new i, j
                matchEndInS++;
                i = matchEndInS;
            } else {
                //for the case don't has * before
                return false;
            }
        }

        //check for remaining characters in pattern
        while (j < p.length() && p.charAt(j) == '*') {
            j++;
        }

        return j == p.length();
    }

    public boolean isMatchDPOptimize(String s, String p) {
        int ls = s.length();
        int lp = p.length();
        
        boolean[] pre = new boolean[lp+1];
        pre[lp] = true;

        //if p ends with *
        for (int j = lp - 1; j >= 0; j--) {
            if (p.charAt(j) == '*') {
                pre[j] = true;
            } else {
                break;
            }
        }

        for (int i = ls - 1; i>=0; i--) {
        		boolean[] cur = new boolean[lp+1];
        		
            for (int j = lp - 1; j>=0; j--) {
                if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?') {
                		cur[j] = pre[j+1];
                } else if (p.charAt(j) == '*') {
                    //match * as 1 char || match * as empty
                		cur[j] = pre[j] || cur[j+1];
                } else {
                		cur[j] = false;
                }
            }
            
            pre = cur;
        }

        return pre[0];
    }
    
    //DP
    public boolean isMatchDP(String s, String p) {
        int ls = s.length();
        int lp = p.length();
        boolean[][] dp = new boolean[ls+1][lp+1];
        dp[ls][lp] = true;

        //if p ends with *
        for (int j = lp - 1; j >= 0; j--) {
            if (p.charAt(j) == '*') {
                dp[ls][j] = true;
            } else {
                break;
            }
        }

        for (int i = ls - 1; i>=0; i--) {
            for (int j = lp - 1; j>=0; j--) {
                if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?') {
                    dp[i][j] = dp[i+1][j+1];
                } else if (p.charAt(j) == '*') {
                    //match * as 1 char || match * as empty
                    dp[i][j] = dp[i+1][j] || dp[i][j+1];
                } else {
                    dp[i][j] = false;
                }
            }
        }

        return dp[0][0];
    }

    public static void main(String[] args) {
//        System.err.println(isMatch("c", "*?*")); //true
//        System.err.println(isMatch("aaaa", "***a")); //true
//        System.err.println(isMatch("abed", "?b*d**"));  //true
        //System.err.println(isMatch("abcyc", "?b*c"));  //true
        //System.err.println(isMatch("geeksforgeeks", "g*for*eks"));
    }
}
