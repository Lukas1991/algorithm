package heap;

import java.util.Arrays;
import java.util.PriorityQueue;

import tools.Interval;

public class MeetingRooms {

	/**
	 * 252. Meeting Rooms. 
	 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), 
	 * determine if a person could attend all meetings.
	 * 
	 * For example, Given [[0, 30],[5, 10],[15, 20]], return false.
	 */
	public boolean canAttendMeetings(Interval[] intervals) {
		int n = intervals.length;
		if (n < 2)
			return true;
		Arrays.sort(intervals, (o1, o2) -> o1.start - o2.start);
		
		for (int i = 1; i < n; i++) {
			Interval pre = intervals[i - 1];
			Interval curr = intervals[i];
			if (pre.end > curr.start) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 253. Meeting Rooms II. 
	 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), 
	 * find the minimum number of conference rooms required.
	 * 
	 * For example, Given [[0, 30],[5, 10],[15, 20]], return 2.
	 */
	public int minMeetingRooms(Interval[] intervals) {
		int n = intervals.length;
		if (n < 2)
			return n;
		Arrays.sort(intervals, (o1, o2) -> o1.start - o2.start);

		// min heap to save end times
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		pq.offer(intervals[0].end);
		int maxSize = 1;

		for (int i = 1; i < n; i++) {
			Interval curr = intervals[i];

			// remove the ends which already finish
			while (!pq.isEmpty() && pq.peek() <= curr.start) {
				pq.poll();
			}

			pq.offer(curr.end);
			maxSize = Math.max(maxSize, pq.size());
		}

		return maxSize;
	}
}
