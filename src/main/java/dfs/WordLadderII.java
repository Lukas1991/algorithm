package dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

public class WordLadderII {

	public static void main(String[] args) {

		String[] arr = { "hot", "dot", "dog" };
		List<String> wordList = Arrays.stream(arr).collect(Collectors.toList());

		System.out.println(new WordLadderII().findLadders("hot", "dog", wordList));

	}

	public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {

		List<List<String>> ret = new ArrayList<>();

		Map<String, List<String>> oneLadderMap = buildMap(beginWord, new ArrayList<>(wordList));
		Map<String, Integer> deepMap = new HashMap<>();

		Set<String> dict = new HashSet<>(wordList);
		int min = this.minLadders(beginWord, endWord, wordList, deepMap);

		List<String> currentList = new ArrayList<>();
		Set<String> used = new HashSet<>();
		currentList.add(endWord);
		used.add(endWord);

		dfs(beginWord, currentList, used, oneLadderMap, deepMap, ret, min, 1, dict);

		return ret;
	}

	private void dfs(String beginWord, List<String> currentList, Set<String> used, Map<String, List<String>> map,
			Map<String, Integer> deepMap, List<List<String>> ret, int min, int currentDeep, Set<String> dict) {

		String curWord = currentList.get(currentList.size() - 1);

		if (curWord.equals(beginWord)) {
			List<String> r = new ArrayList<>(currentList);
			Collections.reverse(r);
			ret.add(r);
			return;
		}

		if (currentList.size() > min)
			return;

		List<String> next = map.get(curWord);
		for (String w : next) {

			if (deepMap.containsKey(w) && !used.contains(w) && deepMap.get(w) + currentDeep < min) {

				currentList.add(w);
				used.add(w);
				dfs(beginWord, currentList, used, map, deepMap, ret, min, currentDeep + 1, dict);
				currentList.remove(currentList.size() - 1);
				used.remove(w);
			}
		}
	}

	private int minLadders(String beginWord, String endWord, List<String> wordList, Map<String, Integer> deepMap) {

		Set<String> dict = new HashSet<>(wordList);
		Queue<String> q = new LinkedList<>();
		q.add(beginWord);
		int deep = 0;
		deepMap.put(beginWord, deep);

		int step = 1;
		while (!q.isEmpty()) {

			int size = q.size();
			step++;
			deep++;
			for (int i = 0; i < size; i++) {

				String cur = q.poll();

				for (int j = 0; j < cur.length(); j++) {

					char[] cc = cur.toCharArray();
					for (char c = 'a'; c <= 'z'; c++) {

						if (cc[j] != c) {
							cc[j] = c;
							String newWord = String.valueOf(cc);
							if (dict.contains(newWord) && endWord.equals(newWord)) {
								return step;
							}

							if (dict.contains(newWord)) {
								if (!deepMap.containsKey(newWord)) {
									deepMap.put(newWord, deep);
								}

								q.add(newWord);
								dict.remove(newWord);
							}
						}
					}
				}
			}
		}

		return 0;
	}

	private Map<String, List<String>> buildMap(String startWord, List<String> words) {

		words.add(startWord);
		Set<String> dict = new HashSet<>(words);
		Map<String, List<String>> map = new HashMap<>();

		for (String word : words) {

			for (int i = 0; i < word.length(); i++) {
				char[] cc = word.toCharArray();
				for (char c = 'a'; c <= 'z'; c++) {
					if (c != cc[i]) {
						cc[i] = c;
						String newWord = String.valueOf(cc);
						if (dict.contains(newWord) && !newWord.equals(word)) {

							map.putIfAbsent(word, new ArrayList<>());
							map.get(word).add(newWord);
						}
					}
				}
			}
		}

		return map;
	}
}
