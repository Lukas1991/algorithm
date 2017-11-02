package dp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LongestChain {

	public int findLongestChain(String[] words) {
		if (words == null || words.length == 0) {
			return 0;
		}
		
		//sort by length ASC
		Arrays.sort(words, (a1, a2) -> a1.length() - a2.length());
		
		//key is word, value is max chain
		Map<String, Integer> map = new HashMap<>();
		
		int max = 0;
		for (String word : words) {
			if (!map.containsKey(word)) {
				map.put(word, 1);
				
				char[] arr = word.toCharArray();
				for (int i = 0; i < arr.length; i++) {
					
					StringBuilder sb = new StringBuilder(word);
					sb.deleteCharAt(i);
					String after = sb.toString();
					
					if (map.containsKey(after) && map.get(after) + 1 > map.get(word)) {
						map.put(word, map.get(after) + 1);
					}					
				}
				
				max = Math.max(max, map.get(word));				
			}
		}
		
		return max;
	}
	
	
	public static void main(String[] args) {
		LongestChain obj = new LongestChain();
		System.err.println(obj.findLongestChain(new String[]{"6", "a", "b", "ba", "bca", "bda", "bdca"}));
		System.err.println(obj.findLongestChain(new String[]{"a", "a", "bc", "exf", "abc"}));
		System.err.println(obj.findLongestChain(new String[]{"bc", "abc"}));
	}

}
