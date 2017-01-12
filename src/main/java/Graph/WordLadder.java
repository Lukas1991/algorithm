package Graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class WordLadder {

	/**Use breath-first or depth-first search to solve problems
	 * Use two queues, one for words and another for counting
	 * 
	 */
	public int ladderLength(String beginWord, String endWord, Set<String> wordList) {
		if (beginWord.equals(endWord)) return 1;

		LinkedList<String> wordQueue = new LinkedList<>();
		LinkedList<Integer> distanceQueue = new LinkedList<>();

		wordQueue.add(beginWord);
		distanceQueue.add(1);
		wordList.remove(beginWord);

		int minStep = Integer.MAX_VALUE;
		while (!wordQueue.isEmpty()) {
			String word = wordQueue.pop();
			int currDistance = distanceQueue.pop();

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
						if (wordList.contains(newStr)) {
							wordList.remove(newStr);

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

		HashSet<String> dict = new HashSet<String>();
		System.err.println(dict.toString());
		dict.add("hot");dict.add("dot");dict.add("dog");dict.add("lot");dict.add("log");
		wordLadder.ladderLength("hit", "cog", dict);

	}

}
