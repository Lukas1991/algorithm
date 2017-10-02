package unionFind;

import java.util.Arrays;

public class CountComponents {

    /**
     * 323. Number of Connected Components in an Undirected Graph
     *
     * Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to find the number of connected components in an undirected graph. 找有几组
     * Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], return 2.
     * Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]], return 1.
     * Note:
     * You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.
     */
    public int countComponents(int n, int[][] edges) {
        int count = n;
        int[] root = new int[n];
        Arrays.fill(root, -1);

        for (int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];
            int r1 = findRoot(root, a);
            int r2 = findRoot(root, b);

            if (r1 == -1 && r2 == -1) {
                root[a] = a;
                root[b] = a;
                count--;
            } else if (r1 != -1 && r2 == -1) {
                root[b] = r1;
                count--;
            } else if (r1 == -1 && r2 != -1) {
                root[a] = r2;
                count--;
            } else {
                //not in same group, union, merge
                if (r1 != r2) {
                    root[r2] = r1;
                    count--;
                }
            }
        }

        return count;
    }

    private int findRoot(int[] root, int a) {
        if (root[a] == -1) {
            return -1;
        } else {
            while (root[a] != a) {
                root[a] = root[root[a]];//path compression, same as numbers of island 2
                a = root[a];
            }

            return a;
        }
    }
}
