package Interview.twosigma;

/**
 * '?' Matches single character 0-9 or A-Z.
 * '*' Matches at least 1 sequence of characters (do not include the empty sequence).
 *
 */
public class WildcardMatching2 {
	
	//Time m*n worst case, Space O(1)
	public boolean isMatch(String s, String p) {
		if (!validate(s, p)) {
			return false;
		}
		
        int i = 0;  //s index
        int j = 0;  //p index
        int starInP = -1;
        int matchEndInS = 0; //chars before matchEndInS in s are matched

        while (i < s.length()) {
            if (j < p.length() && (p.charAt(j) == s.charAt(i) || (Character.isDigit(s.charAt(i)) && p.charAt(j) == '?'))) {
                i++;
                j++;
            } else if (j < p.length() && p.charAt(j) == '*') {
                //keep i, j
                starInP = j;
                matchEndInS = i;
                
                i++; //!!! the difference
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

        return j == p.length();
    }
	
	//Time m*n, Space m
	public boolean isMatchDPOptimize(String s, String p) {
		if (!validate(s, p)) {
			return false;
		}
		
		int ls = s.length();
        int lp = p.length();
        
        boolean[] pre = new boolean[lp+1];
        
        pre[lp] = true;

        for (int i = ls - 1; i>=0; i--) {
        		char cs = s.charAt(i);
        		boolean[] cur = new boolean[lp+1];
   
            for (int j = lp - 1; j>=0; j--) {
            		char cp = p.charAt(j);           
            		
                if (cs == cp || (cp == '?' && Character.isDigit(cs))) {
                		cur[j] = pre[j+1];
                } else if (cp == '*') {
                    //match * as 1 char || match * as many chars
                		//match 1 char is = dp[i+1][j+1]
                		//match many char is = dp[i+2][j+1] || dp[i+3][j+1] || ... || dp[sEnd][j+1], all these can be converted to dp[i+1][j]  (both index - 1)
                		cur[j] = pre[j+1] || pre[j];
                } else {
                		cur[j] = false;
                }
            }
            
            pre = cur;
        }

        return pre[0];
	}
	
	//Time m*n, Space m*n
	public boolean isMatchDP(String s, String p) {
		if (!validate(s, p)) {
			return false;
		}
		int ls = s.length();
        int lp = p.length();
        boolean[][] dp = new boolean[ls+1][lp+1];
        dp[ls][lp] = true;

        for (int i = ls - 1; i>=0; i--) {
        		char cs = s.charAt(i);
        		
            for (int j = lp - 1; j>=0; j--) {
            		char cp = p.charAt(j);           
            		
                if (cs == cp || (cp == '?' && Character.isDigit(cs))) {
                    dp[i][j] = dp[i+1][j+1];
                } else if (cp == '*') {
                    //match * as 1 char || match * as many chars
                		//match 1 char is = dp[i+1][j+1]
                		//match many char is = dp[i+2][j+1] || dp[i+3][j+1] || ... || dp[sEnd][j+1], all these can be converted to dp[i+1][j]  (both index - 1)
                	
                    dp[i][j] = dp[i+1][j+1] || dp[i+1][j]; 
                } else {
                    dp[i][j] = false;
                }
            }
        }

        return dp[0][0];		
	}
	
	private boolean validate(String s, String p) {
		char[] sArray = s.toCharArray();
		char[] pArray = p.toCharArray();
		
		//validate input
		for (char c : sArray) {
			if (!isValidInS(c)) {
    				return false;
    			}
		}
		
		for (char c : pArray) {
			if (!isValidInP(c)) {
    				return false;
    			}
		}
		
		return true;
	}
	
	private boolean isValidInS(char c) {
		return Character.isDigit(c) || Character.isUpperCase(c);
	}
	
	private boolean isValidInP(char c) {
		return Character.isDigit(c) || Character.isUpperCase(c) || c == '?' || c == '*';
	}
	
	public static void main(String[] args) {
		WildcardMatching2 matching = new WildcardMatching2();
		
		System.out.println(matching.isMatchDP("A212B", "A?1*B"));		
		System.out.println(matching.isMatchDP("A2CB", "A*B"));		
	}

}
