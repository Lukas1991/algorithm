package array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShortestDistance {

	// Shortest Distance 1, word1 != word2
	public int shortestDistance(String[] words, String word1, String word2) {
		int min = Integer.MAX_VALUE;
		int index1 = -1;
		int index2 = -1;
		for (int i = 0; i < words.length; i++) {
			if (words[i].equals(word1)) {
				index1 = i;
				if (index2 > -1) {
					min = Math.min(min, Math.abs(index1 - index2));
				}

			} else if (words[i].equals(word2)) {
				index2 = i;
				if (index1 > -1) {
					min = Math.min(min, Math.abs(index1 - index2));
				}
			}
		}

		return min;
	}

	// Shortest Distance 3, word1 can be the same as word2
	public int shortestWordDistance(String[] words, String word1, String word2) {
		int min = Integer.MAX_VALUE;
		int index1 = -1;
		int index2 = -1;

		for (int i = 0; i < words.length; i++) {
			boolean find = false;
			if (words[i].equals(word1)) {
				index1 = i;
				find = true;
			}
			if (words[i].equals(word2)) {
				if (word1.equals(word2)) {
					index1 = index2;
				}
				index2 = i;
				find = true;
			}

			if (find && index1 >= 0 && index2 >= 0) {
				min = Math.min(min, Math.abs(index1 - index2));
			}
		}

		return min;
	}
	
	//Shortest Word Distance II
	//Design a class which receives a list of words in the constructor, and implements a method that 
	//takes two words word1 and word2 and return the shortest distance between these two words in the list.
	private Map<String, List<Integer>> map;

	public void WordDistance(String[] words) {
	    map = new HashMap<String, List<Integer>>();
	    for(int i = 0; i < words.length; i++) {
	        String w = words[i];
	        if(map.containsKey(w)) {
	            map.get(w).add(i);
	        } else {
	            List<Integer> list = new ArrayList<Integer>();
	            list.add(i);
	            map.put(w, list);
	        }
	    }
	}

	public int shortest(String word1, String word2) {
	    List<Integer> list1 = map.get(word1);
	    List<Integer> list2 = map.get(word2);
	    int ret = Integer.MAX_VALUE;
	    for(int i = 0, j = 0; i < list1.size() && j < list2.size(); ) {
	        int index1 = list1.get(i);
	        int	index2 = list2.get(j);
	        if(index1 < index2) {
	            ret = Math.min(ret, index2 - index1);
	            i++;
	        } else {
	            ret = Math.min(ret, index1 - index2);
	            j++;
	        }
	    }
	    return ret;
	}

	public static void main(String[] args) {

	}

}
