package Interview.amzn.oa;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://yeqiuquan.blogspot.com/2017/03/lintcode-616-course-schedule-ii.html
 * 
 * @author dacai
 *
 */
public class CourseScheduleII {

	public int[] findOrder(int numCourses, int[][] prerequisites) {

		List<List<Integer>> adj = new ArrayList<>();
		int[] numOfInComings = new int[numCourses];
		int[] result = new int[numCourses];

		for (int i = 0; i < numCourses; i++) {
			adj.add(new ArrayList<Integer>());
		}

		for (int i = 0; i < prerequisites.length; i++) {
			adj.get(prerequisites[i][1]).add(prerequisites[i][0]);
			numOfInComings[prerequisites[i][0]]++;
		}

		Queue<Integer> queue = new LinkedList<>();
		for (int i = 0; i < numOfInComings.length; i++) {
			if (numOfInComings[i] == 0) {
				queue.offer(i);
			}
		}

		int count = 0;
		while (!queue.isEmpty()) {
			int course = queue.poll();
			result[count++] = course;
			for (int w : adj.get(course)) {
				numOfInComings[w]--;
				if (numOfInComings[w] == 0) {
					queue.offer(w);
				}
			}
		}

		if (numCourses == count) {
			return result;
		}

		return new int[0];
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
