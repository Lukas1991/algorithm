package string;

public class LongestPalindromeSubstring {

	/**
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

}
