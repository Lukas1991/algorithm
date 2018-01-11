package Graph;

import java.util.*;
import java.util.stream.Collectors;

public class WordLadderII {

	/**
	 * 双向BFS，startSet, endSet
	 * build map, 如果是从start to end 方向，key is word in startSet, value is transformed word
	 * 如果是从end to start方向，key is transformed word
	 *
	 * 从map 再generate result list, DFS.
	 */
	public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
		Set<String> dict = new HashSet<>(wordList);
		List<List<String>> res = new ArrayList<>();
		if (!dict.contains(endWord)) {
			return res;
		}

		// hash set for both ends
		Set<String> set1 = new HashSet<>();
		Set<String> set2 = new HashSet<>();

		// initial words in both ends
		set1.add(beginWord);
		set2.add(endWord);

		// we use a map to help construct the final result
		//key is start word, value is its next level word, always from start level to end level
		Map<String, List<String>> map = new HashMap<>();

		// build the map
		helper(dict, set1, set2, map, false);

		List<String> tmp = new ArrayList<>();
		tmp.add(beginWord);

		//recursively/backtracking build the final result
		generateList(beginWord, endWord, map, tmp, res);

		return res;
	}

	/**
	 * if from start to end, flip = false, key is word in set1, value is transformed
	 *
	 * if from end to start, flip = true, value is word in set1, key is tranformed
	 */
	boolean helper(Set<String> dict, Set<String> set1, Set<String> set2, Map<String, List<String>> map, boolean flip) {
		if (set1.isEmpty()) {
			return false;
		}

		if (set1.size() > set2.size()) {
			return helper(dict, set2, set1, map, !flip);
		}

		// remove words on current both ends from the dict
		dict.removeAll(set1);
		dict.removeAll(set2);

		// as we only need the shortest paths
		// we use a boolean value help early termination
		boolean done = false;

		// set for the next level
		Set<String> nextSet = new HashSet<>();

		// for each string in end 1
		for (String str : set1) {
			for (int i = 0; i < str.length(); i++) {
				char[] chars = str.toCharArray();

				// change one character for every position
				for (char ch = 'a'; ch <= 'z'; ch++) {
					chars[i] = ch;

					String word = new String(chars);

					// make sure we construct the tree in the correct direction
					String key = flip ? word : str;
					String val = flip ? str : word;

					if (!map.containsKey(key)) {
						map.put(key, new ArrayList<>());
					}

					List<String> list = map.get(key);

					if (set2.contains(word)) {
						done = true;
						list.add(val);
					}

					if (!done && dict.contains(word)) {
						nextSet.add(word);
						list.add(val);
					}
				}
			}
		}

		// early terminate if done is true
		return done || helper(dict, set2, nextSet, map, !flip);
	}

	void generateList(String start, String end, Map<String, List<String>> map, List<String> tmp, List<List<String>> res) {
		if (start.equals(end)) {
			res.add(new ArrayList<>(tmp));
			return;
		}

		// need this check in case the diff between start and end happens to be one
		// e.g "a", "c", {"a", "b", "c"}
		if (!map.containsKey(start)) {
			return;
		}

		for (String word : map.get(start)) {
			tmp.add(word);
			generateList(word, end, map, tmp, res);
			tmp.remove(tmp.size() - 1);
		}
	}

	public static void main(String[] args) {
		String[] arr = {"a", "b", "c"};
		List<String> wordList = Arrays.stream(arr).collect(Collectors.toList());

		System.out.println(new WordLadderII().findLadders("a", "c", wordList));
	}
}
