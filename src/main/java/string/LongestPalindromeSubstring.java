package string;

public class LongestPalindromeSubstring {

	/**
	 * 5. Longest Palindromic Substring
	 * Iterate the String, get longest palindrome with center of i
	 * get longest palindrome with center of i, i+1
	 * time O(n^2), space O(1)	
	 */
	public String longestPalindrome(String s) {
		if (s == null) {
			return null;
		}
		if (s.length() <= 1) {
			return s;
		}

		String longest = "";
		for (int i = 0; i < s.length() - 1; i++) {
			// get longest palindrome with center of i
			String tmp = getPalindrome(s, i, i);
			if (tmp.length() > longest.length()) {
				longest = tmp;
			}
	 
			// get longest palindrome with center of i, i+1
			tmp = getPalindrome(s, i, i + 1);
			if (tmp.length() > longest.length()) {
				longest = tmp;
			}
		}
	 
		return longest;
	}

	private String getPalindrome(String s, int begin, int end) {
		while (begin >= 0 && end < s.length() && s.charAt(begin) == s.charAt(end)) {
			begin--;
			end++;
		}
		return s.substring(begin + 1, end);
	}

	/**
	 * 516. Longest Palindromic Subsequence
	 * dp[i][j]: the longest palindromic subsequence's length of substring(i, j)
	 * State transition:
	 * dp[i][j] = dp[i+1][j-1] + 2 if s.charAt(i) == s.charAt(j)
	 * otherwise, dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1])
	 * Initialization: dp[i][i] = 1
	 */
	public int longestPalindromeSubseq(String s) {
		int[][] dp = new int[s.length()][s.length()];

		for (int i = s.length() - 1; i >= 0; i--) {
			dp[i][i] = 1;
			for (int j = i+1; j < s.length(); j++) {
				if (s.charAt(i) == s.charAt(j)) {
					dp[i][j] = dp[i+1][j-1] + 2;
				} else {
					dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
				}
			}
		}
		return dp[0][s.length()-1];
	}

}
