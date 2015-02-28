package string;

import java.util.regex.Pattern;

public class checkNumeric {

	public boolean isNumber(String s) {
		s = s.trim();
		if (s.length() == 0 || s.equals("."))
			return false;
		if (s.matches("[+-]\\.")) {
			return false;
		}

		// check without e
		if (s.matches("[+-]?\\d*(\\.\\d*)?")) {
			return true;

		} else if (s.matches("[+-]?\\d+(\\.\\d*)?([eE][+-]?\\d+)?|[+-]?(\\.\\d+)([eE][+-]?\\d+)?")) {
			// check with e
			return true;
		}
		else {
			return false;
		}
	}

	public boolean isNumber2(String s) {
		s = s.trim();
		// match any except 0-9 or +-. or eE
		if (Pattern.compile("[^\\d\\.\\+\\-eE]").matcher(s).find())
			return false;

		try {
			double d = Double.parseDouble(s);
		} catch (NumberFormatException e) {
			return false;
		}

		return true;
	}

	public static void main(String[] args) {
		String s = " 0.1 ";
		// checkNumeric cn=new checkNumeric();
		// System.out.println(cn.isNumber(s));

		// difference between string matches and Pattern mathcer
		String t = "959440.94f";
		System.out.println(t.matches("[^0-9|\\+|\\-|\\.|e|E]"));
		// Attempts to match the entire region against the pattern.
		System.out.println(Pattern.compile("[^0-9|\\.|\\-|\\+|e]").matcher(t).find());
		// to find the next subsequence

		// The backslash \ is an escape character in Java Strings. You have to
		// use double backslash \\ to define a single backslash \\d,\\s

	}
}
