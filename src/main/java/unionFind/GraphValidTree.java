package unionFind;

import java.util.Arrays;

public class GraphValidTree {

	/**
	 * 261. Graph Valid Tree.
	 * 
	 * Given n nodes labeled from 0 to n - 1 and a list of undirected edges
	 * (each edge is a pair of nodes), write a function to check whether these
	 * edges make up a valid tree.
	 * 
	 * For example:
	 * 
	 * Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.
	 * 
	 * Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return false.
	 * 
	 * Note: you can assume that no duplicate edges will appear in edges. Since
	 * all edges are undirected, [0, 1] is the same as [1, 0] and thus will not
	 * appear together in edges.
	 * 
	 */
	/**
	 * To tell whether a graph is a valid tree, we have to:
	 * 1. Make sure there is no cycle in the graph - this has to be a none-cyclic graph;
	 * 	use an array p , and store the parent of the node (as array index). 
	 * 2. Make sure every node is reached - this has to be a connected graph; (return edges.size() == n-1;)
	 */
	public boolean validTree(int n, int[][] edges) {
		int[] p = new int[n];
		Arrays.fill(p, -1);
		
		for (int[] arr : edges) {
			int root1 = find(p, arr[0]);
			int root2 = find(p, arr[1]);
			
			if (root1 == root2) {
				return false;
			} else {
				//merge two root
				p[root1] = root2;
			}
		}

		//can also check the edges
		return edges.length == n-1;
	}

	// find root
	private int find(int[] p, int i) {
		if (p[i] == -1) {
			return i;
		} else {
			return find(p, p[i]);
		}
	}
}
