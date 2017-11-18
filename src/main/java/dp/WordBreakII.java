package dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * s = "catsanddog", dict = ["cat", "cats", "and", "sand", "dog"].
 * 
 * @author dacai
 *
 */
public class WordBreakII {

	public static void main(String[] args) {
		WordBreakII run = new WordBreakII();

		String[] ss = { "cat", "cats", "and", "sand", "dog" };

		System.out.println(run.wordBreak("catsanddog", Arrays.asList(ss)));

	}

	public List<String> wordBreak(String s, List<String> dict) {
		Set<String> wordDict = new HashSet<>(dict);
		
		if (!helper(s, wordDict)) {
			return new ArrayList<String>();
		}

		HashMap<Integer, List<String>> map = new HashMap<Integer, List<String>>();
		map.put(0, new ArrayList<>());
		map.get(0).add("");

		for (int i = 1; i <= s.length(); i++) {
			for (int j = 0; j < i; j++) {
				if (map.containsKey(j) && wordDict.contains(s.substring(j, i))) {
					map.putIfAbsent(i, new ArrayList<>());
					for (String str : map.get(j)) {
						map.get(i).add(str + (str.equals("") ? "" : " ") + s.substring(j, i));
					}
				}
			}
		}

		return map.get(s.length());
	}

	private boolean helper(String s, Set<String> wordDict) {
		boolean dp[] = new boolean[s.length() + 1];
		dp[0] = true;
		for (int i = 1; i <= s.length(); i++) {
			for (int j = 0; j < i; j++) {
				if (dp[j] && wordDict.contains(s.substring(j, i))) {
					dp[i] = true;
				}
			}
		}
		return dp[s.length()];
	}
}
