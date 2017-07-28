package Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
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

    /**
     * Course Schedule 3, sort courses by end time, iterate courses
     * If we meet a course we cannot take (time + duration > end time),
     * Find a course from the previous taken courses, which duration time is the longest/max, AND the duration is bigger than this course duration
     * So that we can replace that one to take this course instead, to save us some time for furture courses.
     *
     * To find max from taken courses --> max Heap
     *
     * Time complexity is O(nlogn)
     * Space complexity is O(n)
     */
    public int scheduleCourse(int[][] courses) {
        //sort by end time
        Arrays.sort(courses, (a, b) -> a[1] - b[1]);

        //max heap, entry is the taken course's duration - course[0]
        PriorityQueue<Integer> taken = new PriorityQueue<>((a,b) -> b-a);

        int time = 0;
        for (int i=0; i < courses.length; i++) {
            int[] thisCourse = courses[i];
            int thisDuration = thisCourse[0];

            if (thisDuration + time <= thisCourse[1]) {
                taken.offer(thisDuration);
                time = time + thisDuration;
            } else if (!taken.isEmpty() && taken.peek() > thisDuration) {
                //remove the max duration which is taken before
                //and take this course instead, so that we can save some time for course in the furture

                int toRemove = taken.poll();
                time = time + thisDuration - toRemove;
                taken.offer(thisDuration);
            }

        }

        return taken.size();
    }

    /**
     * Course Schedule 3, sort courses by end time, iterate courses
     * If cannot use extra space, use the front part of courses array as the courses taken
     *
     * To find max from taken courses --> Another iteration, from 0 to lastTakenIndex
     *
     * Time complexity is O(n* count of taken)
     * Space complexity is O(1)
     */
    public static int scheduleCourse2(int[][] courses) {
        //sort by end time
        Arrays.sort(courses, (a, b) -> a[1] - b[1]);

        int lastTakenIndex = -1;

        int time = 0;
        for (int i=0; i < courses.length; i++) {
            int[] thisCourse = courses[i];
            int thisDuration = thisCourse[0];

            if (thisDuration + time <= thisCourse[1]) {
                //move this course to front, don't need to swap, just replace the lastTakenIndex+1
                lastTakenIndex++;
                courses[lastTakenIndex] = thisCourse;

                time = time + thisDuration;
            } else {
                //find max duration which is also > this duration
                int maxDurationIndex = i;
                for (int j = 0; j <= lastTakenIndex; j++) {
                    if (courses[j][0] > courses[maxDurationIndex][0]) {
                        maxDurationIndex = j;
                    }
                }

                int maxDuration = courses[maxDurationIndex][0];
                if (maxDuration > thisDuration) {
                    courses[maxDurationIndex] = thisCourse;
                    time = time + thisDuration - maxDuration;
                }
            }
        }

        return lastTakenIndex + 1;
    }

	public static void main(String[] args) {
        int[][] courses = {
            {100, 200}, {200, 1300}, {1000, 1250}, {2000,3200}
        };

        CourseSchedule.scheduleCourse2(courses);
	}

}
