package Interview.amzn.oa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Interview.amzn.oa.model.Connection;

/**
 * 
 * http://blog.csdn.net/qq_27647073/article/details/77920564
 * 
 * @author dacai
 *
 */

public class MinimumSpanningTree {
	/**
	 * @param connections
	 *            given a list of connections include two cities and cost
	 * @return a list of connections from results
	 */
	public List<Connection> lowestCost(List<Connection> connections) {
		
		Collections.sort(connections, comp);
		Map<String, Integer> hash = new HashMap<String, Integer>();
		int n = 0;
		for (Connection connection : connections) {
			if (!hash.containsKey(connection.city1)) {
				hash.put(connection.city1, ++n);
			}
			if (!hash.containsKey(connection.city2)) {
				hash.put(connection.city2, ++n);
			}
		}

		int[] father = new int[n + 1];

		List<Connection> results = new ArrayList<Connection>();
		for (Connection connection : connections) {
			int num1 = hash.get(connection.city1);
			int num2 = hash.get(connection.city2);

			int root1 = find(num1, father);
			int root2 = find(num2, father);
			if (root1 != root2) {
				father[root1] = root2;
				results.add(connection);
			}
		}

		if (results.size() != n - 1) {
			return new ArrayList<Connection>();
		}
		return results;
	}
	
	public int find(int num, int[] father) {
		if (father[num] == 0) {
			return num;
		}

		return father[num] = find(father[num], father);
	}

	static Comparator<Connection> comp = new Comparator<Connection>() {
		public int compare(Connection a, Connection b) {
			if (a.cost != b.cost) {
				return a.cost - b.cost;
			}
			if (a.city1.equals(b.city1)) {
				return a.city2.compareTo(b.city2);
			}
			return a.city1.compareTo(b.city1);
		}
	};


}
