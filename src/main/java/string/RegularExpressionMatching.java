package string;

public class RegularExpressionMatching {


    /**
     * If dp[i][j] is comparing s[i] with p[j]
     * 1, If p.charAt(j) == s.charAt(i) :  dp[i][j] = dp[i-1][j-1];
     * 2, If p.charAt(j) == '.'         : dp[i][j] = dp[i-1][j-1];
     * 3, If p.charAt(j) == '*':
     *      here are two sub conditions:
     *      1   if p.charAt(j-1) != s.charAt(i) && p.charAt(i-1) != '.' : dp[i][j] = dp[i][j-2]  //in this case, "a*" only counts as empty
     *      2   if p.charAt(j-1) == s.charAt(i) or p.charAt(i-1) == '.':
     *                  dp[i][j] = dp[i-1][j]    //in this case, a* counts as multiple a
     *                  or dp[i][j] = dp[i][j-1]   // in this case, a* counts as single a
     *                  or dp[i][j] = dp[i][j-2]   // in this case, a* counts as empty
     *
     * Since we use dp[i+1][j+1] as comparing s[i] with p[j], all the indexex above will + 1
     */
    public boolean isMatchDP(String s, String p) {
        int m = s.length();
        int n = p.length();

        boolean[][] dp = new boolean[m+1][n+1];
        dp[0][0] = true;
        //dp[i+1][j+1] is comparing s[i] with p[j]
        //dp[i+1][j-1] is comparing s[i] with p[j-2]
        for (int j = 0; j < n; j++) {
            if (p.charAt(j) == '*' && dp[0][j-1]) {
                dp[0][j+1] = true;
            }
        }

        //dp[i][0] are all false
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.') {
                    dp[i+1][j+1] = dp[i][j];
                } else if (p.charAt(j) == '*') {

                    char pre = p.charAt(j-1);
                    if (s.charAt(i) != pre && pre != '.') {
                        //empty match for p[j-1 ~ j]
                        dp[i+1][j+1] = dp[i+1][j-1];
                    } else {
                        //empty match || 1 item match || multiple items
                        dp[i+1][j+1] = dp[i+1][j-1] || dp[i+1][j] || dp[i][j+1];
                    }
                }
            }
        }

        return dp[m][n];
    }

    public boolean isMatch(String s, String p) {
        return isMatch(s, 0, p, 0);
    }

    public boolean isMatch(String s, int i, String p, int j) {
        if (j == p.length()) {
            return i == s.length();
        }

        // arrive the end of pattern, and it must not be *, cuz line 16 already catched it in last call
        if (j == p.length() - 1) {
            return i == s.length() - 1 && charMatch(s, i, p, j);
        }

        // if j+1 is not *
        // then both of current chars should be matched, and continue to check both of next char
        if (p.charAt(j + 1) != '*') {
            return charMatch(s, i, p, j) && isMatch(s, i + 1, p, j + 1);
        }

        // j+1 is *. try check s current chars match with the p char behind * , which * refers to 0 in s
        if (isMatch(s, i, p, j + 2)) {
            return true;
        }

        //j+1 is *, * refers to one or more in s
        while (charMatch(s, i, p, j)) {
            if (isMatch(s, i + 1, p, j + 2)) {
                return true;
            }
            i++;
        }

        return false;
    }

    public boolean charMatch(String s, int i, String p, int j) {
        if (i >= s.length() || j >= p.length()) {
            return false;
        }

        return s.charAt(i) == p.charAt(j) || p.charAt(j) == '.';
    }


    public static void main(String[] args) {

    }

}
