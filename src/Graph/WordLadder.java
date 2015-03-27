package Graph;

import java.util.*;

public class WordLadder {

	/**Use breath-first or depth-first search to solve problems
	 * Use two queues, one for words and another for counting
	 * 
	 */
	
	public static int ladderLength(String start, String end, HashSet<String> dict) {
		if (dict.size() == 0)
			return 0;
	 
		dict.add(end);
	 
		LinkedList<String> wordQueue = new LinkedList<String>();
		LinkedList<Integer> distanceQueue = new LinkedList<Integer>();
	 
		wordQueue.add(start);
		distanceQueue.add(1);
	 
		//track the shortest path
		int result = Integer.MAX_VALUE;
		while (!wordQueue.isEmpty()) {
			String currWord = wordQueue.pop();
			System.out.println("----de Q: "+currWord);
			
			Integer currDistance = distanceQueue.pop();
	 
			if (currWord.equals(end)) {
				result = Math.min(result, currDistance);
			}
	 
			for (int i = 0; i < currWord.length(); i++) {
				char[] currCharArr = currWord.toCharArray();
				for (char c = 'a'; c <= 'z'; c++) {
					currCharArr[i] = c;
	 
					String newWord = new String(currCharArr);
					if (dict.contains(newWord)) {
						wordQueue.add(newWord);
						System.out.println("In Q: "+newWord);
						distanceQueue.add(currDistance + 1);
						dict.remove(newWord);
					}
				}
			}
		}
	 
		if (result < Integer.MAX_VALUE)
			return result;
		else
			return 0;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashSet<String> dict = new HashSet<String>();
		dict.add("hot");dict.add("dot");dict.add("dog");dict.add("lot");dict.add("log");
		ladderLength("hit", "cog", dict);
		
		
	}

}
