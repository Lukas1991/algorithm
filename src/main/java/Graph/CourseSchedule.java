package Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class CourseSchedule {

	/**
	 * BFS using Graph as Adjacency Lists
	 * Time Complexity - O(V + E)， Space Complexity - O(V)
	 */
	public boolean canFinish(int numCourses, int[][] prerequisites) {
		//array to track how many prerequisites a course has
        int[] count = new int[numCourses];
        
        //value is which courses the key course can unlock -> can use List<List<>> to represent Adjacency list
        Map<Integer, List<Integer>> unlockMap = new HashMap<>();
        
        for(int[] pair : prerequisites) {
            int current = pair[0];
            int need = pair[1];
            count[current]++;
            if (!unlockMap.containsKey(need)) {
                unlockMap.put(need, new ArrayList<>());
            }
            unlockMap.get(need).add(current);
        }
        
        //all unlocked courses are added to queue
        Queue<Integer> unlockedQ = new LinkedList<>();
        for (int i=0; i<count.length; i++) {
            if (count[i] == 0) {
            	unlockedQ.offer(i);
            }
        }
        
        //count how many courses can take
        int canTake = 0;
        while (!unlockedQ.isEmpty()) {
            int course = unlockedQ.poll();
            canTake++;
            
            if (unlockMap.containsKey(course)) {
                List<Integer> unlockList = unlockMap.get(course);
                for (int c : unlockList) {
                    count[c]--;
                    if (count[c] == 0) {
                    	unlockedQ.offer(c);
                    }
                }
            }
            
        }
        
        return canTake == numCourses;
    }
	/**
	 * Topological sort in BFS
	 */
	public int[] findOrder(int numCourses, int[][] prerequisites) {
		int[] count = new int[numCourses];

		// value is which courses the key course can unlock
		Map<Integer, List<Integer>> unlockMap = new HashMap<>();

		for (int[] pair : prerequisites) {
			int current = pair[0];
			int need = pair[1];
			count[current]++;
			if (!unlockMap.containsKey(need)) {
				unlockMap.put(need, new ArrayList<>());
			}
			unlockMap.get(need).add(current);
		}

		// all unlocked courses are added to queue
		Queue<Integer> unlockedQ = new LinkedList<>();
		for (int i = 0; i < count.length; i++) {
			if (count[i] == 0) {
				unlockedQ.offer(i);
			}
		}

		List<Integer> taken = new ArrayList<>();

		while (!unlockedQ.isEmpty()) {
			int course = unlockedQ.poll();
			taken.add(course);

			if (unlockMap.containsKey(course)) {
				List<Integer> unlockList = unlockMap.get(course);
				for (int c : unlockList) {
					count[c]--;
					if (count[c] == 0) {
						unlockedQ.offer(c);
					}
				}
			}

		}
		if (taken.size() < numCourses) {
			return new int[0];
		} else {
			int[] res = new int[numCourses];
			for (int i = 0; i < taken.size(); i++) {
				res[i] = taken.get(i);
			}
			return res;
		}

	}
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>();
		
	}

}
