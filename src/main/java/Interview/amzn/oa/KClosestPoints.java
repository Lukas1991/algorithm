package Interview.amzn.oa;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 
 * https://yeqiuquan.blogspot.com/2017/03/lintcode-612-k-closest-points.html
 * 
 * @author dacai
 *
 */
public class KClosestPoints {

	List<List<Integer>> closestLocations(int totalCrates, List<List<Integer>> allLocations, int truckCapacity) {
		
		List<Point> points = new ArrayList<>();
		for (List<Integer> alloc : allLocations) {
			points.add(new Point(alloc.get(0), alloc.get(1)));
		}
		
		PriorityQueue<Point> maxHeap = new PriorityQueue<Point>(new Comparator<Point>() {
			public int compare(Point a, Point b) {
				int ret = b.x * b.x + b.y * b.y - (a.x * a.x + a.y * a.y);
				if (ret == 0) {
					ret = b.x - a.x;
				}
				if (ret == 0) {
					ret = b.y - a.y;
				}
				return ret;
			}
		});
		
		for (Point p : points) {
			
			maxHeap.add(p);
			if (maxHeap.size() > truckCapacity) {
				maxHeap.poll();
			}
		}
		
		List<List<Integer>> ret = new ArrayList<>();
		
		for (int i = 0; i < truckCapacity; i++) {
			ret.add(new ArrayList<>());
		}
		
		int count = 0;
		while (!maxHeap.isEmpty()) {
			Point p = maxHeap.poll();
			ret.get(count).add(p.x);
			ret.get(count).add(p.y);
			count++;
		}
		
		return ret;
	}
	
	class Point {
		
		public int x;
		public int y;
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	

	public Point[] kClosest(Point[] points, Point origin, int k) {
		// Write your code here

		PriorityQueue<Point> pq = new PriorityQueue<Point>(k, new Comparator<Point>() {
			public int compare(Point a, Point b) {
				int diff = getDistance(b, origin) - getDistance(a, origin);
				if (diff == 0) {
					diff = b.x - a.x;
				}
				if (diff == 0) {
					diff = b.y - a.y;
				}
				return diff;
			}
		});

		for (Point pt : points) {
			pq.add(pt);
			if (pq.size() > k)
				pq.poll();
		}

		Point[] result = new Point[k];
		while (k - 1 >= 0) {
			result[--k] = pq.poll();
		}

		return result;
	}

	public int getDistance(Point a, Point b) {
		return (a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
