package Recursion;

import java.util.ArrayList;
import java.util.List;

public class RemoveInvalidParentheses {

	/**
	 * Remove the minimum number of invalid parentheses in order to make the
	 * input string valid. Return all possible results.
	 * 
	 * Note: The input string may contain letters other than the parentheses (and ).
	 * 
	 * Examples: "()())()" -> ["()()()", "(())()"] 
	 * 			 "(a)())()" -> ["(a)()()", "(a())()"] 
	 * 			 ")(" -> [""]
	 *
	 * Step 1: Scan from left to right for ')', when we meet an invalid ')', we remove the ')'s we meet before, to form a new string,
	 * and use the new string in step 2.
	 * 
	 * Step 2: Scan the new string from right to left for '(' 
	 * 		   -> Same as if we reverse the string, and scan from left to right.
	 * 
	 * After we scan from both sides, add string to result list.
	 */
	public List<String> removeInvalidParentheses(String s) {
		List<String> ans = new ArrayList<>();
		remove(ans, s, 0, 0, new char[] { '(', ')' });
		return ans;
	}

	private void remove(List<String> res, String s, int lasti, int lastj, char[] par) {
		int count = 0;
		for (int i = lasti; i < s.length(); i++) {
			if (s.charAt(i) == par[0])
				count++;
			if (s.charAt(i) == par[1])
				count--;
			if (count < 0) {
				// remove par[1] from previous substring
				for (int j = lastj; j <= i; j++) { // j == i is for when i is the only invalid par ")" or "n)"
					if (s.charAt(j) == par[1]) {
						if (j == lastj || s.charAt(j - 1) != par[1]) { 
							// the second is for the last ')' in "())"
							String newString = s.substring(0, j) + s.substring(j + 1, s.length());
							remove(res, newString, i, j, par);
						}
					}
				}
				return;
			}
		}

		if (par[0] == '(') {
			// it's from left to right
			String reverse = new StringBuilder(s).reverse().toString();
			remove(res, reverse, 0, 0, new char[] { ')', '(' });
		} else {
			String reverse = new StringBuilder(s).reverse().toString();
			res.add(reverse);
		}
	}

	public static void main(String[] args) {
		RemoveInvalidParentheses obj = new RemoveInvalidParentheses();
		//List<String> res = obj.removeInvalidParentheses(")(f");
		List<String> res = obj.removeInvalidParentheses("()())()");
		System.err.println(res.toString());
		System.err.println(res.size());
	}

}
