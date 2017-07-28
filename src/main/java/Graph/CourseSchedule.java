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
        
        //value is which courses the key course can unlock
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
	
	
	public static void main(String[] args) {
	
	}

}
