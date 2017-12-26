package Recursion;

import java.util.ArrayList;
import java.util.List;

public class LetterCombinationsOfPhoneNumber {

	/**
	 * Construct a dictionary to hold allowed characters for each digit
	 */
	public List<String> letterCombinations(String digits) {
		if(digits == null || digits.length()==0) return new ArrayList<>();

		String[] dic = {"","","abc","def","ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

		List<String> res = new ArrayList<>();
		res.add("");

		for (int i = 0; i < digits.length(); i++) {
			char c = digits.charAt(i);
			int number = c - '0';
			String string = dic[number];

			List<String> res2 = new ArrayList<>();
			for (int j = 0; j < string.length(); j++) {
				for (String pre : res) {
					res2.add(pre + string.charAt(j));
				}
			}
			res = res2;
		}

		return res;
	}

	public List<String> letterCombinationsDFS(String digits) {
		if(digits == null || digits.length()==0) return new ArrayList<>();

		String[] dic = {"","","abc","def","ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

		List<String> res = new ArrayList<>();

		helper(dic, digits, 0, "", res);
		return res;
	}

	private void helper(String[] dic, String digits, int start, String tmp, List<String> res) {
		if (start == digits.length()) {
			res.add(tmp);
		} else {
			char c = digits.charAt(start);
			int number = c - '0';
			String string = dic[number];

			for (int j = 0; j < string.length(); j++) {
				helper(dic, digits, start+1, tmp + string.charAt(j), res);
			}
		}
	}
	
	public static void main(String[] args) {
		String digits = "23";
		LetterCombinationsOfPhoneNumber obj = new LetterCombinationsOfPhoneNumber();
		List<String> res = obj.letterCombinations(digits);
		System.err.println(res.toString());
	}

}
