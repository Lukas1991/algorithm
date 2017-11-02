package Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class WordLadder {

	/**
	 * Word Ladder 1
	 * BFS, Level by level, for each level use List<String> currLevel
	 * To avoid Time Limit Exceeded, convert wordList to a set, wordSet.contains(newStr) will take O(1)
	 * To avoid infinite loop, once we find a transformed word, remove it from wordSet
	 */
	public int ladderLength(String beginWord, String endWord, List<String> wordList) {
		if (beginWord.equals(endWord))  return 0;

		Set<String> wordSet = new HashSet<>();
		wordList.forEach(w -> wordSet.add(w));

		List<String> currLevel = new ArrayList<>();
		currLevel.add(beginWord);

		int level = 0;
		int minStep = Integer.MAX_VALUE;
		boolean find = false;

		while (!currLevel.isEmpty()) {
			level++;
			//System.err.println(currLevel.toString());

			List<String> nextLevel = new ArrayList<>();
			for (String begin : currLevel) {
				if (begin.equals(endWord)) {
					minStep = Math.min(minStep, level);
					find = true;
				} else {
					char[] arr = begin.toCharArray();
					for (int i = 0; i<arr.length; i++) {
						char before = arr[i];
						for (char c = 'a'; c<='z'; c++) {
							if (c != before) {
								arr[i] = c;
								String newStr = new String(arr);
								if (wordSet.contains(newStr)) {
									nextLevel.add(newStr);
									wordSet.remove(newStr);
								}
							}
						}
						arr[i] = before;
					}
				}
			}

			currLevel = nextLevel;
		}

		return find ? minStep : 0;
	}

	//Word Ladder 1, Use two queues, one for words and one for distance
	public int ladderLengthQueue(String beginWord, String endWord, List<String> wordList) {
		if (beginWord.equals(endWord)) return 0;

		Set<String> wordDic = new HashSet<>();
		wordList.forEach(w -> wordDic.add(w));

		Queue<String> wordQueue = new LinkedList<>();
		Queue<Integer> distanceQueue = new LinkedList<>();

		wordQueue.add(beginWord);
		distanceQueue.add(1);

		int minStep = Integer.MAX_VALUE;
		while (!wordQueue.isEmpty()) {
			String word = wordQueue.poll();
			int currDistance = distanceQueue.poll();

			if (word.equals(endWord)) {
				minStep = Math.min(minStep, currDistance);
				continue;
			}

			for (int i=0; i<word.length(); i++) {
				char[] begins = word.toCharArray();

				for (char c = 'a'; c<='z'; c++) {
					if (c != begins[i]) {
						char tmp = begins[i];
						begins[i] = c;
						String newStr = new String(begins);
						if (wordDic.contains(newStr)) {
							wordDic.remove(newStr);

							wordQueue.add(newStr);
							distanceQueue.add(currDistance + 1);
						}
						begins[i] = tmp;
					}
				}
			}
		}

		if (minStep == Integer.MAX_VALUE) return 0;
		return minStep;
	}

	class WordNode {
		String word;
		int steps;
		WordNode pre;

		public WordNode(String word, int steps, WordNode pre) {
			this.word = word;
			this.steps = steps;
			this.pre = pre;
		}
	}

	public List<List<String>> findLadders(String beginWord, String endWord, Set<String> wordList) {
		List<List<String>> res = new ArrayList<>();
		LinkedList<WordNode> queue = new LinkedList<>();
		queue.add(new WordNode(beginWord, 1, null));

		wordList.add(endWord);
		int minStep = 0;

		Set<String> visited = new HashSet<>();
		Set<String> unVisited = new HashSet<>();
		unVisited.addAll(wordList);

		int preNumSteps = 0;

		while (!queue.isEmpty()) {
			WordNode node = queue.pop();
			int currentSteps = node.steps;
			String word = node.word;

			if (word.equals(endWord)) {
				if (minStep == 0) {
					minStep = currentSteps; //is 1
				}

				if (currentSteps == minStep) {
					List<String> list = new ArrayList<>();
					list.add(word);
					while (node.pre != null) {
						node = node.pre;
						list.add(0, node.word); //add to first
					}
					res.add(list);
					continue;
				}
			}

			if (preNumSteps < currentSteps) {
				unVisited.removeAll(visited);
			}
			preNumSteps = currentSteps;

			char[] arr = word.toCharArray();
			for (int i=0; i<word.length(); i++) {
				for (char c = 'a'; c <= 'z'; c++) {
					char tmp = arr[i];
					if (arr[i] != c) {
						arr[i] = c;
					}
					String newWord = new String(arr);
					if (unVisited.contains(newWord)) {
						queue.add(new WordNode(newWord, currentSteps + 1, node));
						visited.add(word);
					}
					arr[i] = tmp;
				}

			}

		}

		return res;
	}


	public static void main(String[] args) {
		WordLadder wordLadder = new WordLadder();

		String[] arr = {"hot","dot","dog","lot","log","cog"};
		List<String> wordList = Arrays.asList(arr);

		int minStep = wordLadder.ladderLength("hit", "cog", wordList);
		//int minStep = wordLadder.ladderLengthQueue("hit", "cog", wordList);
		System.err.println(minStep);
	}

}
