package Interview.amzn.oa;

import java.util.HashSet;
import java.util.Set;

/**
 * https://yeqiuquan.blogspot.com/2017/03/lintcode-627-longest-palindrome.html
 * 
 * @author dacai
 *
 */
public class LongestPalindrome {

	public int longestPalindrome(String s) {

		Set<Character> set = new HashSet<>();

		int len = 0;
		for (char c : s.toCharArray()) {

			if (set.contains(c)) {
				len += 2;
				set.remove(c);
			} else {
				set.add(c);
			}
		}

		if (set.isEmpty())
			return len;
		return len + 1;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
