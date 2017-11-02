package Interview.amzn.oa;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import Interview.amzn.oa.model.Record;

/**
 * 
 * https://yeqiuquan.blogspot.com/2017/03/lintcode-613-high-five.html
 * 
 * @author dacai
 *
 */
public class HighFive {

	public Map<Integer, Double> highFive(Record[] results) {

		Map<Integer, Double> result = new HashMap<Integer, Double>();
		HashMap<Integer, PriorityQueue<Record>> map = new HashMap<Integer, PriorityQueue<Record>>();

		int k = 5;
		for (Record r : results) {
			map.put(r.id, map.getOrDefault(r.id, new PriorityQueue<>((a, b) -> a.score - b.score)));
			map.get(r.id).add(r);
			if (map.get(r.id).size() > k) {
				map.get(r.id).poll();
			}
		}

		for (Map.Entry<Integer, PriorityQueue<Record>> entry : map.entrySet()) {
			int id = entry.getKey();
			PriorityQueue<Record> pq = entry.getValue();
			double average = 0;
			int num = pq.size();
			while (!pq.isEmpty()) {
				average += pq.poll().score;
			}
			average /= num;
			result.put(id, average);
		}

		return result;

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
