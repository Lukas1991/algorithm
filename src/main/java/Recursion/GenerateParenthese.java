package Recursion;

import java.util.ArrayList;
import java.util.List;

public class GenerateParenthese {

	// Given n pairs of parentheses, write a function to generate all
	// combinations of well-formed parentheses.
	public static List<String> generateParenthesis(int n) {
		List<String> res = new ArrayList<>();
		helper("(", 1, 0, res, n);
		return res;
	}

	private static void helper(String tmp, int left, int right, List<String> res, int n) {
		if (tmp.length() == n * 2) {
			res.add(tmp);
		} else {

			// add left
			if (left < n) {
				helper(tmp + "(", left + 1, right, res, n);
			}

			// add right
			if (right < left) {
				helper(tmp + ")", left, right + 1, res, n);
			}
		}
	}

	public static void main(String[] args) {
		List<String> res = generateParenthesis(3);
		System.err.println(res.size());
		System.err.println(res.toString());
	}

}
